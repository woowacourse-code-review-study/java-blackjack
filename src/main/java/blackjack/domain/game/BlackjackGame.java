package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;

public final class BlackjackGame {
    private final Players players;
    private final Bets bets;

    public BlackjackGame() {
        this.players = Players.create();
        this.bets = new Bets();
    }

    public void addPlayers(final List<String> names) {
        players.addPlayers(names);
    }

    public void addBet(final Name name, final int amount) {
        bets.addBet(name, amount);
    }

    public void initialDraw(final Deck deck) {
        players.initialDraw(deck);
    }

    public void drawToDealer(final Deck deck) {
        players.drawToDealer(deck);
    }

    public void drawTo(final Name name, final Deck deck) {
        players.drawTo(name, deck);
    }

    public void stay(final Name name) {
        players.stay(name);
    }

    public boolean isDrawable() {
        return players.isDrawable();
    }

    public Player findDrawablePlayer() {
        return players.findDrawablePlayer();
    }

    public Bets play() {
        bets.calculateProfit(players.play());
        return bets;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public List<Name> getGamblerNames() {
        return players.getGamblerNames();
    }
}