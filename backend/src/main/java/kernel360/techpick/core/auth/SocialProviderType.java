package kernel360.techpick.core.auth;

public enum SocialProviderType {
    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver"),
    ;

    private final String providerId;

    SocialProviderType(String providerId) {
        this.providerId = providerId;
    }

    public static SocialProviderType providerIdOf(String providerId) throws IllegalArgumentException {
        for (SocialProviderType socialType : SocialProviderType.values()) {
            if (socialType.providerId.equals(providerId)) {
                return socialType;
            }
        }
        throw new IllegalArgumentException();
    }
}
