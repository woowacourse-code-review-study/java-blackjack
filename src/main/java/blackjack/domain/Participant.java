package blackjack.domain;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final PlayerCards playerCards;
    private final Name name;

    Participant(Name name) {
        this.name = name;
        this.playerCards = new PlayerCards();
    }

    void addCard(Card card) {
        playerCards.add(card);
    }

    void addCards(List<Card> cards) {
        playerCards.addAll(cards);
    }

    ScoreState getState() {
        return ScoreState.of(playerCards.getScore());
    }

    public List<Card> getCards() {
        return playerCards.toList();
    }

    public int getScore() {
        return playerCards.getScore();
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
