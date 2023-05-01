package variant.controller;
import variant.view.ChessBoardView;

public class GameController {
    private ChessBoardView chessBoardView;

    public GameController(ChessBoardView chessBoardView) {
        this.chessBoardView = chessBoardView;
    }

    public void startGame() {
        // afficher plateau jeu
        chessBoardView.displayBoard();

    }

}