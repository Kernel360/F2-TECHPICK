package kernel360.techpick.feature.domain.pick.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import kernel360.techpick.TechpickApplication;
import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderRepository;
import kernel360.techpick.core.model.link.Link;
import kernel360.techpick.core.model.link.LinkRepository;
import kernel360.techpick.core.model.pick.PickRepository;
import kernel360.techpick.core.model.pick.PickTagRepository;
import kernel360.techpick.core.model.tag.Tag;
import kernel360.techpick.core.model.tag.TagRepository;
import kernel360.techpick.core.model.user.Role;
import kernel360.techpick.core.model.user.SocialType;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.core.model.user.UserRepository;
import kernel360.techpick.feature.domain.link.dto.LinkInfo;
import kernel360.techpick.feature.domain.pick.dto.PickCommand;
import kernel360.techpick.feature.domain.pick.dto.PickResult;
import kernel360.techpick.feature.infrastructure.link.LinkAdaptor;
import kernel360.techpick.feature.infrastructure.pick.PickAdaptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = TechpickApplication.class)
@ActiveProfiles("test")
class PickServiceTest {
    @Autowired PickService pickService;
    @Autowired PickAdaptor pickAdaptor;

    User user;
    Folder root, recycleBin, unclassified, general;
    Tag tag1, tag2, tag3;
    @Autowired
    private PickRepository pickRepository;
    @Autowired
    private PickTagRepository pickTagRepository;
    @Autowired
    private LinkAdaptor linkAdaptor;
    @Autowired
    private LinkRepository linkRepository;

    @BeforeEach // TODO: change to Adaptor
    void setUp(
        @Autowired UserRepository userRepository,
        @Autowired FolderRepository folderRepository,
        @Autowired TagRepository tagRepository
    ) {
        // save test user
        user = userRepository.save(
            User.builder()
                .email("test@test.com")
                .nickname("test")
                .password("test")
                .role(Role.ROLE_USER)
                .socialProvider(SocialType.KAKAO)
                .socialProviderId("1")
                .tagOrderList(new ArrayList<>())
                .build()
        );

        // save test folder
        root = folderRepository.save(Folder.createEmptyRootFolder(user));
        recycleBin = folderRepository.save(Folder.createEmptyRecycleBinFolder(user));
        unclassified = folderRepository.save(Folder.createEmptyUnclassifiedFolder(user));
        general = folderRepository.save(Folder.createEmptyGeneralFolder(user, root, "React.js"));

        // save tag
        tag1 = tagRepository.save(Tag.builder().user(user).name("tag1").colorNumber(1).build());
        tag2 = tagRepository.save(Tag.builder().user(user).name("tag2").colorNumber(1).build());
        tag3 = tagRepository.save(Tag.builder().user(user).name("tag3").colorNumber(1).build());
    }

    @AfterEach // TODO: change to Adaptor (repository 말고!)
    void cleanUp(
        @Autowired UserRepository userRepository,
        @Autowired FolderRepository folderRepository,
        @Autowired TagRepository tagRepository,
        @Autowired PickRepository pickRepository,
        @Autowired PickTagRepository pickTagRepository,
        @Autowired LinkRepository linkRepository
    ) {
        // NOTE: 제거 순서 역시 FK 제약 조건을 신경써야 한다.
        pickTagRepository.deleteAll();
        pickRepository.deleteAll();
        userRepository.deleteAll(); // TODO: soft delete 이라 DB를 직접 비워야 한다.
        folderRepository.deleteAll();
        tagRepository.deleteAll();
        linkRepository.deleteAll();
    }

    @Nested
    @DisplayName("픽 조회")
    class getPick {
        @Test
        @DisplayName("""
            저장한 픽이 정상적으로 조회되어야 한다.
        """)
        void pick_save_and_read_test() {
            // given
            LinkInfo linkInfo = new LinkInfo("linkUrl", "linkTitle", "linkDescription", "imageUrl", null);
            List<Long> tagOrder = List.of(tag1.getId(), tag2.getId(), tag3.getId());
            PickCommand.Create command = new PickCommand.Create(
                user.getId(), "PICK", "MEMO",
                tagOrder, unclassified.getId(), linkInfo
            );

            // when
            PickResult saveResult = pickService.saveNewPick(command);
            PickResult readResult = pickService.getPick(new PickCommand.Read(user.getId(), saveResult.id()));

            // then
            assertThat(readResult).isNotNull();
            assertThat(readResult).isEqualTo(saveResult);
        }

        @Test
        @DisplayName("""
            링크가 비활성화 (Invalidated At)이 되면, Pick 데이터 조회 시
            비활성화 되었다는 정보가 포함 되어 있어야 한다.
        """)
        void recognize_invalidated_pick_info_test() {
            // given
            LinkInfo linkInfo = new LinkInfo("linkUrl", "linkTitle", "linkDescription", "imageUrl", null);
            List<Long> tagOrder = List.of(tag1.getId(), tag2.getId(), tag3.getId());
            PickCommand.Create command = new PickCommand.Create(
                user.getId(), "PICK", "MEMO",
                tagOrder, unclassified.getId(), linkInfo
            );
            PickResult saveResult = pickService.saveNewPick(command);

            // when (link invalidated)
            Link link = linkAdaptor.getLink(linkInfo.url()).markAsInvalid();
            linkRepository.saveAndFlush(link);

            PickResult readResult
                = pickService.getPick(new PickCommand.Read(user.getId(), saveResult.id()));

            // then
            assertThat(readResult).isNotNull();
            assertThat(readResult.linkInfo().invalidatedAt()).isNotNull();
        }
    }

    @Nested
    class savePick {
        @Test
        @DisplayName("""
            동시적으로 Pick 생성 요청이 들어올 경우, 하나만 생성되고 나머지는 실패해야 한다.
        """)
        void create_multiple_pick_concurrent_test() throws InterruptedException {
            // given
            int threadCount = 2;
            ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
            CountDownLatch countDownLatch = new CountDownLatch(threadCount);

            AtomicInteger successCount = new AtomicInteger();
            AtomicInteger failCount = new AtomicInteger();

            LinkInfo linkInfo = new LinkInfo("linkUrl", "linkTitle", "linkDescription", "imageUrl", null);
            List<Long> tagOrder = List.of(tag1.getId(), tag2.getId(), tag3.getId());

            log.info("----------start -------------------");

            // when
            for (int i = 0; i < threadCount; i++) {
                executorService.submit(() -> {
                    try {
                        PickCommand.Create command = new PickCommand.Create(
                            user.getId(), "PICK", "MEMO",
                            tagOrder, unclassified.getId(), linkInfo
                        );
                        // NOTE: linkUrl 필드에 unique 제약 조건이 걸려 있기에, DB 예외 발생
                        //       이 제약 조건이 없다면 같은 픽이 두번 생성 된다.
                        //       왜냐면 pick 저장 시, pick한 대상 url이 이미 존재하는지 체크하는 과정에서
                        //       "없다" 고 통과되기 때문.
                        pickService.saveNewPick(command);
                        successCount.incrementAndGet(); // 성공 카운트
                    } catch (Exception e) {
                        log.info(e.getMessage());
                        failCount.incrementAndGet(); // 실패 카운트
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }

            countDownLatch.await(); // 모든 스레드가 완료될 때까지 대기
            executorService.shutdown();

            // then
            log.info("success : {} ", successCount.get());
            log.info("fail : {} ", failCount.get());

            assertThat(successCount.get()).isEqualTo(1);
            assertThat(failCount.get()).isEqualTo(threadCount - 1);
        }
    }

    @Nested
    class updatePick {
        @Test
        @DisplayName("""
           픽의 제목, 메모는 null 값이 들어오면 예외를 발생시킨다.
        """)
        void update_data_with_null_test() {
            // given
            // when
            // then
        }
    }

    @Nested
    class movePick {
        @Test
        @DisplayName("""
            같은 폴더 내에서 픽의 순서를 이동한 후
            그 이동된 부모 폴더의 자식 리스트 획득을 할 수 있어야 한다.
            그리고 그 자식 리스트는 순서 정보가 올바르게 설정되어야 한다.
        """)
        void move_pick_to_same_folder_test() {
            // given
            // when
            // then
        }

        @Test
        @DisplayName("""
            다른 폴더 내에서 픽의 순서를 이동한 후
            그 이동된 부모 폴더의 자식 리스트 획득을 할 수 있어야 한다.
            그리고 그 자식 리스트는 순서 정보가 올바르게 설정되어야 한다.
        """)
        void move_pick_to_other_folder_test() {
            // given
            // when
            // then
        }

        @Test
        @DisplayName("""
            1. 순서 설정값이 음수가 들어오면 예외를 발생시킨다.
            2. 순서 설정값이 전체 길이보다 큰 값이 들어오면 예외를 발생시킨다.
        """)
        void move_pick_invalid_order_value_test() {
            // given
            // when
            // then
        }
    }

    @Nested
    class deletePick {
        @Test
        @DisplayName("""
            존재하지 않은 픽을 삭제할 순 없으며, 시도시 예외가 발생한다.
        """)
        void remove_not_existing_pick_exception_test() {
            // given
            // when
            // then
        }

        @Test
        @DisplayName("""
            휴지통에 있지 않은 픽은 삭제할 수 없으며, 시도시 예외가 발생한다.
        """)
        void remove_not_in_recycle_bin_folder_exception_test() {
            // given
            // when
            // then
        }

        @Test
        @DisplayName("""
            태그 삭제시, 픽에 설정된 태그 정보와 tagList도 변경되어야 한다.
        """)
        void update_tag_list_in_pick_when_tag_is_removed_test() {
            // given
            // when
            // then
        }
    }
}