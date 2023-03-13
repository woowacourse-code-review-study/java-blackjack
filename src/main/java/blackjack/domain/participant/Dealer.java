package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    private static final String NAME = "딜러";
    private static final int MAXIMUM_DRAWABLE_SCORE = 16;

    public String getName() {
        return NAME;
    }

    public Hand getHiddenHand() {
        final List<Card> cards = getHand().getCards();
        return new Hand(cards.subList(0, cards.size() - 1));
    }

    public int getMaximumDrawableScore() {
        return MAXIMUM_DRAWABLE_SCORE;
    }

    public boolean isAdditionalDrawn() {
        return hand.count() > BLACK_JACK_CARD_COUNT;
    }

    @Override
    public boolean isDrawable() {
        return isDrawableCardCount() && isDrawableScore();
    }

    private boolean isDrawableCardCount() {
        return hand.count() <= BLACK_JACK_CARD_COUNT;
    }

    private boolean isDrawableScore() {
        return hand.calculateTotalScore() <= MAXIMUM_DRAWABLE_SCORE;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
