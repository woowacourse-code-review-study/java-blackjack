package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import java.util.Objects;

public class Player extends Participant {

    private final PlayerName name;

    public Player(final String name) {
        this(name, new Cards());
    }

    public Player(final String name, final Cards cards) {
        super(cards);
        this.name = new PlayerName(name);
    }

    @Override
    public boolean isDrawable() {
        return cards.calculateTotalScore() < BLACK_JACK_SCORE;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
