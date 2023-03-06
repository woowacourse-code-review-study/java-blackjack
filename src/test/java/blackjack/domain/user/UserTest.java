package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.cardpack.NoviceShuffleStrategy;
import blackjack.domain.user.name.UserName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserTest {

    @Test
    void 유저는_카드팩에서_카드를_뽑는다() {

        // given
        User user = new User(new UserName("주노"));
        CardPack cardPack = new CardPack();
        cardPack.shuffle(new NoviceShuffleStrategy());

        // when
        user.drawCard(cardPack);

        // then
        List<Card> userCards = user.showCards();
        Assertions.assertThat(userCards.get(0))
                .isEqualTo(new Card(CardNumber.ACE, CardShape.SPADE));
    }
}
