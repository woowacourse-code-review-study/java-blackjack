package blackjack.domain.participant;

public class Name {

    private static final int MAX_NAME_LENGTH = 100;

    private final String value;

    Name(final String value) {
        validateName(value);
        this.value = value;
    }

    private void validateName(final String name) {
        validateEmptyName(name);
        validateLength(name);
    }

    private void validateEmptyName(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름을 입력하지 않았습니다");
        }
    }

    private void validateLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름이 " + MAX_NAME_LENGTH + "글자를 초과했습니다");
        }
    }

    public String getValue() {
        return value;
    }
}
