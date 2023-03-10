package domain.player;

import java.util.Collection;
import java.util.Objects;

public class Revenue {

    private final int amount;

    public Revenue(final int amount) {
        this.amount = amount;
    }

    public static Revenue lose(final BettingMoney money) {
        return new Revenue(money.amount() * -1);
    }

    public static Revenue defaultWin(final BettingMoney money) {
        return new Revenue(money.amount());
    }

    public static Revenue draw(final BettingMoney money) {
        return new Revenue(0);
    }

    public static Revenue blackJackWin(final BettingMoney money) {
        return new Revenue((int) (money.amount() * 1.5));
    }

    public static Revenue total(final Collection<Revenue> revenues) {
        return new Revenue(revenues.stream()
                .mapToInt(Revenue::amount)
                .sum());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Revenue)) return false;
        final Revenue revenue = (Revenue) o;
        return amount == revenue.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public int amount() {
        return amount;
    }

    public Revenue reverse() {
        return new Revenue(-amount);
    }
}
