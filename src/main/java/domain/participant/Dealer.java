package domain.participant;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final int FILL_BOUNDARY_INCLUSIVE = 16;
    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer(final List<Card> cards) {
        super(new Name(DEFAULT_DEALER_NAME), cards);
    }

    public boolean isFill() {
        return calculateScore() <= FILL_BOUNDARY_INCLUSIVE;
    }
}