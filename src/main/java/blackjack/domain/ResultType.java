package blackjack.domain;

public enum ResultType {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String name;

    ResultType(final String name) {
        this.name = name;
    }

    public ResultType getOppositeResult() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == TIE) {
            return TIE;
        }
        return WIN;
    }

    public String getName() {
        return name;
    }
}
