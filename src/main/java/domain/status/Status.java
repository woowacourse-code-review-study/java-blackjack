package domain.status;

import domain.card.Card;
import domain.card.Cards;
import domain.participant.Score;
import java.math.BigDecimal;

public interface Status {
    Status initialDraw(Cards cards);

    Status draw(Card card);

    Status selectStand();

    BigDecimal profitWeight();
    Cards cards();

    default boolean isBust(){
        return false;
    }
}
