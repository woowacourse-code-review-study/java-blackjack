package blackjack.domain.card;

import static blackjack.domain.card.Number.ACE;
import static blackjack.domain.card.Number.JACK;
import static blackjack.domain.card.Number.QUEEN;
import static blackjack.domain.card.Number.TEN;
import static blackjack.domain.card.Number.TWO;
import static blackjack.domain.card.Suit.CLOVER;
import static blackjack.domain.card.Suit.DIAMOND;
import static blackjack.domain.card.Suit.HEART;
import static blackjack.domain.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardsTest {

    private static Stream<Arguments> generateCardsWithoutAce() {
        return Stream.of(
                Arguments.of(List.of(new Card(TWO, SPADE), new Card(JACK, HEART)), 12),
                Arguments.of(List.of(), 0)
        );
    }

    private static Stream<Arguments> generateCardsWithAce() {
        return Stream.of(
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(JACK, HEART)), 21),
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(ACE, HEART), new Card(TEN, CLOVER)), 12),
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(QUEEN, HEART), new Card(TEN, CLOVER)), 21),
                Arguments.of(List.of(new Card(ACE, SPADE), new Card(ACE, HEART)), 12)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCardsWithoutAce")
    void 점수를_계산한다(final List<Card> cardPack, final int totalScore) {
        final Cards cards = new Cards(cardPack);

        assertThat(cards.calculateTotalScore()).isEqualTo(totalScore);
    }

    @ParameterizedTest
    @MethodSource("generateCardsWithAce")
    void 에이스가_포함된_경우_최적의_점수를_계산한다(final List<Card> cardPack, final int totalScore) {
        final Cards cards = new Cards(cardPack);

        assertThat(cards.calculateTotalScore()).isEqualTo(totalScore);
    }

    @Test
    void 카드를_추가한다() {
        final Cards cards = new Cards(new ArrayList<>());

        final Card card = new Card(ACE, DIAMOND);
        cards.addCard(card);

        assertThat(cards.count()).isEqualTo(1);
    }
}
