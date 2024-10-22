package kernel360.techpick.core.auth;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import kernel360.techpick.core.model.user.Role;
import kernel360.techpick.core.model.user.SocialType;
import lombok.Getter;

public class TechpickOAuth2UserInfo implements OAuth2User {

    @Getter
    private final SocialType provider;
    private final Map<String, Object> attributes;

    public TechpickOAuth2UserInfo(String provider, Map<String, Object> attributes) {
        try {
            this.provider = SocialType.providerIdOf(provider);
            this.attributes = attributes;
        } catch (IllegalArgumentException e) {
            // TODO: change to service api exception later
            // throw ApiOAuth2Exception.SOCIAL_TYPE_INVALID();
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.ROLE_USER.toString()));
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

    public String getEmail() {
        return attributes.get("email").toString();
    }

}
