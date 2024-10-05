package kernel360.techpick.core.auth.service;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kernel360.techpick.core.auth.model.OAuth2UserInfo;
import kernel360.techpick.core.config.OAuth2AttributeConfigProvider;
import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.folder.FolderType;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.repository.FolderRepository;
import kernel360.techpick.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2Service extends DefaultOAuth2UserService {

	private final UserRepository userRepository;
	private final FolderRepository folderRepository;
	private final OAuth2AttributeConfigProvider configProvider;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		String provider = userRequest.getClientRegistration().getRegistrationId();
		var oAuth2User = super.loadUser(userRequest);
		Map<String, Object> attributes = getAttributes(oAuth2User, provider);
		var oAuth2UserInfo = new OAuth2UserInfo(provider, attributes);

		// TODO: 김민규 - 리팩토링 제안
		//       사용자 생성 및 기본 폴더 세팅 로직이 OAuth쪽에 있어서 헷갈립니다.
		//       새로운 사용자 생성 로직(DB저장 + 폴더 생성)은 userService 쪽에서 해주는게 좋을 것 같아요.
		//       Ex) User user = userService.createNewUser(...);
		if (!userRepository.existsBySocialProviderId(oAuth2UserInfo.getName())) {
			User user = saveOAuth2UserInfo(oAuth2UserInfo);
			createBasicFolder(user);
		}
		return oAuth2UserInfo;
	}

	private Map<String, Object> getAttributes(OAuth2User oAuth2User, String provider) {
		Map<String, String> config = configProvider.getAttributeConfig(provider);
		Map<String, Object> attributes = new HashMap<>();
		for (String key : config.keySet()) {
			Object value = searchAttribute(config.get(key), oAuth2User.getAttributes());
			attributes.put(key, value);
		}
		return attributes;
	}

	// TODO: 응답 body 에서 직접 값을 받아오는 형식으로 리팩토링 필요
	// BFS 로 nested map 구조를 탐색
	private Object searchAttribute(String targetKey, Map<String, Object> map) {
		Queue<Map<String, Object>> queue = new ArrayDeque<>();
		queue.add(map);
		while (!queue.isEmpty()) {
			var curMap = queue.poll();
			for (String key : curMap.keySet()) {
				Object value = curMap.get(key);
				if (key.equals(targetKey)) {
					return value;
				} else if (value instanceof Map<?, ?>) {
					queue.add((Map<String, Object>)value);
				}
			}
		}
		// TODO: ApiUserException 으로 리팩토링 예정
		throw new IllegalArgumentException("Attribute of " + targetKey + " is not found");
	}

	private User saveOAuth2UserInfo(OAuth2UserInfo oAuth2UserInfo) {
		User user = User.create(
			oAuth2UserInfo.getProvider(),
			oAuth2UserInfo.getName(),
			oAuth2UserInfo.getEmail()
		);
		return userRepository.save(user);
	}

	private void createBasicFolder(User user) {

		folderRepository.save(Folder.create("미분류폴더", null, FolderType.UNCLASSIFIED, user));
		folderRepository.save(Folder.create("휴지통", null, FolderType.RECYCLE_BIN, user));
		folderRepository.save(Folder.create("최상위폴더", null, FolderType.ROOT, user));
	}
}
