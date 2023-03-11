package blackjack.domain.card;

import blackjack.domain.game.ScoreState;
import blackjack.domain.vo.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public Score getScore() {
        Score handleAceScore = getTotalScore().add(Score.of(10));

        if (hasAce() && ScoreState.of(handleAceScore).isNotBust()) {
            return handleAceScore;
        }
        return getTotalScore();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private Score getTotalScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score::add)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerCards that = (PlayerCards) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    public List<Card> toList() {
        return List.copyOf(cards);
    }
}
