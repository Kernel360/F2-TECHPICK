package kernel360.techpick.feature.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.service.FolderService;
import kernel360.techpick.feature.structure.service.StructureService;
import kernel360.techpick.feature.user.model.UserMapper;
import kernel360.techpick.feature.user.model.UserProvider;
import kernel360.techpick.feature.user.service.dto.SocialUserCreateDto;
import kernel360.techpick.feature.user.util.NameGenerator;
import kernel360.techpick.feature.user.util.SimpleNameGenerator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final FolderService folderService;
    private final StructureService structureService;

    private final UserProvider userProvider;

    // private final BCryptPasswordEncoder passwordEncoder; // TODO: encode later
    private final NameGenerator nameGenerator = new SimpleNameGenerator(); // TODO: implement later

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userProvider.findUserById((Long)auth.getPrincipal());
    }

    @Transactional(readOnly = true)
    public boolean isUserExistsBySocialProviderId(String socialProviderId) {
        return userProvider.existsBySocialProviderId(socialProviderId);
    }

    @Transactional
    public User createNewSocialUser(SocialUserCreateDto dto) {

        User newUser = userProvider.saveUser(
            UserMapper.toUserEntity(dto, nameGenerator.generateName())
        );

        folderService.createBasicFolders(newUser);
        structureService.saveInitialStructure(newUser);

        return newUser;
    }
}
