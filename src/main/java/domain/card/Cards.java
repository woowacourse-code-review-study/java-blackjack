package domain.card;

import domain.card.shuffler.CardsShuffler;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public final class Cards {


    private final Deque<Card> cards;

    public Cards(CardsShuffler shuffler) {
        this.cards = shuffler.shuffleCards(initializeCards());
    }

    private static Deque<Card> initializeCards() {
        Deque<Card> cards = new ArrayDeque<>();
        for (Value value : Value.values()) {
            for (Shape shape : Shape.values()) {
                cards.push(new Card(value, shape));
            }
        }
        return cards;
    }

    public List<Card> giveInitialCards() {
        return new ArrayList<>(List.of(getCard(), getCard()));
    }

    public boolean contains(final Card card) {
        return cards.contains(card);
    }

    public Card getCard() {
        try {
            return cards.pop();
        } catch (NoSuchElementException exception) {
            throw new NoSuchElementException();
        }
    }
}