package domain.participant;

import domain.card.Card;
import java.util.List;

public class Participant {

    private static final int BUST_BOUNDARY_EXCLUSIVE = 21;
    private final Name name;
    protected final List<Card> cards;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScore(totalScore);
        }
        return totalScore;
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBust() {
        return calculateScore() > BUST_BOUNDARY_EXCLUSIVE;
    }
}