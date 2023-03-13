package blackjack.controller.dto;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.PlayerName;
import java.util.ArrayList;
import java.util.List;

public class ParticipantResponse {

    private static final int HIDDEN_SCORE = -1;

    private final String name;
    private final CardsResponse cardsResponse;

    private ParticipantResponse(final String name, final CardsResponse cardsResponse) {
        this.name = name;
        this.cardsResponse = cardsResponse;
    }

    public static ParticipantResponse ofPlayer(final PlayerName playerName, final BlackJackGame blackJackGame) {
        final int score = blackJackGame.getPlayerScore(playerName);
        final Hand hand = blackJackGame.getPlayerHand(playerName);
        return new ParticipantResponse(playerName.getValue(), CardsResponse.of(score, hand));
    }

    public static ParticipantResponse ofDealer(final BlackJackGame blackJackGame) {
        final int score = blackJackGame.getDealerScore();
        final Hand hand = blackJackGame.getDealerHand();
        return new ParticipantResponse(blackJackGame.getDealerName(), CardsResponse.of(score, hand));
    }

    public static ParticipantResponse ofDealerWithHidden(final BlackJackGame blackJackGame) {
        final Hand hand = blackJackGame.getDealerHiddenHand();
        return new ParticipantResponse(blackJackGame.getDealerName(), CardsResponse.of(HIDDEN_SCORE, hand));
    }

    public static List<ParticipantResponse> listOfPlayer(final BlackJackGame blackJackGame) {
        final List<ParticipantResponse> players = new ArrayList<>();
        final List<PlayerName> playerNames = blackJackGame.getPlayerNames();

        playerNames.forEach(playerName -> players.add(ParticipantResponse.ofPlayer(playerName, blackJackGame)));
        return players;
    }

    public String getName() {
        return name;
    }

    public CardsResponse getCardsResponse() {
        return cardsResponse;
    }
}
