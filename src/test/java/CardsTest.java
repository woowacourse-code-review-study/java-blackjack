import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.shuffler.CardsShuffler;
import domain.card.shuffler.FixedCardsShuffler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class CardsTest {

    private CardsShuffler fixedShuffler = new FixedCardsShuffler();
    private Cards cards = new Cards(fixedShuffler);

    @DisplayName("52개의 카드를 생성할 수 있다.")
    @ParameterizedTest
    @CsvSource({"A,스페이드", "2,하트", "3,다이아몬드", "J,하트", "K,클로버"})
    void createCardsTest(String value, String shape) {
        Card card = new Card(value, shape);
        assertThat(cards.contains(card)).isTrue();
    }

    @DisplayName("카드를 한 장씩 반환받을 수 있다.")
    @Test
    void receiveCardTest() {
        assertThat(cards.getCard()).isEqualTo(new Card("K", "하트"));
        assertThat(cards.getCard()).isEqualTo(new Card("K", "다이아몬드"));
    }
}
