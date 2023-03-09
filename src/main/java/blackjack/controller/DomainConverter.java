package blackjack.controller;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Players;
import blackjack.response.CardResponse;
import blackjack.response.ResultTypeResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class DomainConverter {

    private DomainConverter() {
    }

    static CardResponse convertCard(final Card card) {
        return CardResponse.from(card);
    }

    static List<CardResponse> convertCards(final List<Card> cards) {
        return cards.stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    static Map<String, List<CardResponse>> getPlayerCards(final Players players) {
        final List<String> playerNames = players.getPlayerNames();
        final Map<String, List<CardResponse>> playerCards = new HashMap<>();
        for (final String playerName : playerNames) {
            final List<CardResponse> cardResponses = players.findPlayerByName(playerName)
                    .getCards()
                    .stream()
                    .map(CardResponse::from)
                    .collect(Collectors.toList());
            playerCards.put(playerName, cardResponses);
        }
        return playerCards;
    }

    static Map<String, List<CardResponse>> convertPlayersCards(final Map<String, List<Card>> playersCards) {
        return playersCards.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(CardResponse::from)
                                .collect(Collectors.toList()),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    static Map<ResultTypeResponse, Long> convertDealerResults(final Map<String, ResultType> generatePlayersResult) {
        final Map<ResultTypeResponse, Long> dealerResult = new HashMap<>();
        final long dealerWinCount = generatePlayersResult.values().stream()
                .filter(ResultType.WIN::equals)
                .count();
        final long dealerLoseCount = generatePlayersResult.values().stream()
                .filter(ResultType.LOSE::equals)
                .count();
        final long drawCount = generatePlayersResult.values().stream()
                .filter(ResultType.TIE::equals)
                .count();
        dealerResult.put(ResultTypeResponse.from(ResultType.LOSE.getName()), dealerWinCount);
        dealerResult.put(ResultTypeResponse.from(ResultType.WIN.getName()), dealerLoseCount);
        dealerResult.put(ResultTypeResponse.from(ResultType.TIE.getName()), drawCount);
        return dealerResult;
    }

    static Map<String, ResultTypeResponse> convertPlayersResult(final Map<String, ResultType> generatePlayersResult) {
        return generatePlayersResult.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ResultTypeResponse.from(entry.getValue().getName()),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }
}