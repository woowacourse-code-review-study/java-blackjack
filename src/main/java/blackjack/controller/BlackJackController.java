package blackjack.controller;

import static blackjack.util.Repeater.repeatUntilNoException;

import blackjack.domain.BlackJackGame;
import blackjack.domain.DeckFactory;
import blackjack.view.DrawCommand;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play(final DeckFactory deckFactory) {
        final BlackJackGame blackJackGame = generateBlackJackGame(deckFactory);
        distributeInitialCard(blackJackGame);
        printInitialCards(blackJackGame);
        drawPlayersCards(blackJackGame);
        drawDealerCards(blackJackGame);
        printFinalResult(blackJackGame);
    }


    private BlackJackGame generateBlackJackGame(final DeckFactory deckFactory) {
        return repeatUntilNoException(
                () -> BlackJackGame.of(
                        inputView.inputPlayerNames(),
                        deckFactory),
                outputView::printError);
    }


    private void distributeInitialCard(final BlackJackGame blackJackGame) {
        blackJackGame.distributeInitialCard();
    }

    private void printInitialCards(final BlackJackGame blackJackGame) {
        outputView.printInitialCards(blackJackGame.getInitialCardResponse());
    }

    private void drawPlayersCards(final BlackJackGame blackJackGame) {
        for (final String playerName : blackJackGame.getPlayerNames()) {
            drawPlayerCard(playerName, blackJackGame);
        }
    }

    private void drawPlayerCard(final String playerName, final BlackJackGame blackJackGame) {
        DrawCommand playerInput = DrawCommand.DRAW;
        while (blackJackGame.isPlayerDrawable(playerName) && playerInput != DrawCommand.STAY) {
            playerInput = repeatUntilNoException(
                    () -> inputView.inputCommand(playerName), outputView::printError);
            drawCard(playerName, blackJackGame, playerInput);
            printPlayerResult(playerName, blackJackGame);
        }
    }

    private void drawCard(final String playerName, final BlackJackGame blackJackGame,
            final DrawCommand playerInput) {
        if (playerInput == DrawCommand.DRAW) {
            blackJackGame.drawPlayerCard(playerName);
        }
    }

    private void printPlayerResult(final String playerName, final BlackJackGame blackJackGame) {
        outputView.printCardStatusOfPlayer(blackJackGame.getPlayerCardsResponse(playerName));
    }

    private void drawDealerCards(final BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerDrawable()) {
            blackJackGame.drawDealerCard();
            outputView.printDealerCardDrawMessage();
        }
    }

    private void printFinalResult(final BlackJackGame blackJackGame) {
        outputView.printFinalStatusOfDealer(blackJackGame.getDealerScoreResponse());
        outputView.printFinalStatusOfPlayers(blackJackGame.getPlayersCardsResponse());
        outputView.printFinalResult(blackJackGame.createFinalResultResponse());
    }
}
