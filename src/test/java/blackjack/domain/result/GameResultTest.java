package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import blackjack.domain.game.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private GameResult resultMap;
    private Players players;

    @BeforeEach
    void setPlayers() {
        players = Players.from(List.of("oing", "ditoo"));

        Card heartKing = new Card(Shape.HEART, Symbol.KING);
        Card heartSeven = new Card(Shape.HEART, Symbol.SEVEN);
        Card heartThree = new Card(Shape.HEART, Symbol.THREE);

        players.getDealer().pickCard(heartSeven);
        players.getChallengers().get(0).pickCard(heartThree);
        players.getChallengers().get(1).pickCard(heartKing);
        resultMap = GameResult.from(players, List.of(Money.bet(10000), Money.bet(20000)));
    }

    @Test
    @DisplayName("도전자들의 승패가 올바르게 계산되는지 확인한다")
    void check_challengers_result() {
        Player oing = players.getChallengers().get(0);
        Player ditoo = players.getChallengers().get(1);

        assertThat(resultMap.getChallengerRevenue(oing)).isEqualTo(Result.LOSE);
        assertThat(resultMap.getChallengerRevenue(ditoo)).isEqualTo(Result.WIN);
    }

//    @Test
//    @DisplayName("딜러의 승패가 올바르게 계산되는지 확인한다")
//    void check_dealer_result() {
//        Map<Result, Integer> dealerResult = resultMap.getDealerRevenue();
//        assertThat(dealerResult)
//                .containsOnly(entry(Result.WIN, 1), entry(Result.LOSE, 1));
//    }
}
