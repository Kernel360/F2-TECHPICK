package kernel360.techpick.core.model.user;

// 로그인 타입
public enum SocialType {
	GOOGLE("google"),
	KAKAO("kakao"),
	NAVER("naver"),

	NONE("none"); // 자체 가입 회원
	;

	private final String providerId;

	SocialType(String providerId) {
		this.providerId = providerId;
	}

	public static SocialType providerIdOf(String providerId) throws IllegalArgumentException {
		for (SocialType socialType : SocialType.values()) {
			if (socialType.providerId.equals(providerId)) {
				return socialType;
			}
		}
		throw new IllegalArgumentException();
	}
}
