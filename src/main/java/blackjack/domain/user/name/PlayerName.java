package blackjack.domain.user.name;

public class PlayerName extends UserName {

    public PlayerName(final String name) {
        super(name);
        validationName(name);
    }

    private void validationName(final String name) {

        final String candidateName = name.trim();

        validateBlank(candidateName);
        validateLength(candidateName);
        validateReservedWord(candidateName);
    }

    private static void validateReservedWord(final String name) {
        if ("딜러".equals(name)) {
            throw new IllegalArgumentException("사용자 이름은 딜러일 수 없습니다.");
        }
    }

    private static void validateLength(final String name) {
        if (name.length() > 10) {
            throw new IllegalArgumentException("사용자 이름은 10자 이하여야 합니다.");
        }
    }

    private static void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("사용자 이름은 한글자 이상이여야 합니다.");
        }
    }
}
