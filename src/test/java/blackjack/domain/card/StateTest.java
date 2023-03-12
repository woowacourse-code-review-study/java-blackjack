package blackjack.domain.card;

import static blackjack.domain.card.State.BLACKJACK;
import static blackjack.domain.card.State.BUST;
import static blackjack.domain.card.State.PLAY;
import static blackjack.domain.card.State.STOP;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.util.CardsFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class StateTest {

    static Stream<Arguments> calculateStateSource() {
        return Stream.of(
                Arguments.of(CardsFixture.BLACKJACK, BLACKJACK),
                Arguments.of(CardsFixture.BUST, BUST),
                Arguments.of(CardsFixture.valueOf(21), STOP),
                Arguments.of(CardsFixture.valueOf(20), PLAY)
        );
    }

    @ParameterizedTest(name = "Cards를 받아 상태를 반환한다. 입력: {0}, 상태: {1}")
    @MethodSource("calculateStateSource")
    void Cards를_받아_상태를_반환한다(final List<Card> cards, final State state) {
        final Cards sut = new Cards(cards);

        assertThat(State.calculateState(sut)).isEqualTo(state);
    }
}
