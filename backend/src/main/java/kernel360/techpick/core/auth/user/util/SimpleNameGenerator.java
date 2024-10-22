package kernel360.techpick.core.auth.user.util;

public class SimpleNameGenerator implements NameGenerator {
    @Override
    public String generateName() {
        return "RANDOM_NICKNAME";
    }
}
