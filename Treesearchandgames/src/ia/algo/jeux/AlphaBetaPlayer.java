package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

public class AlphaBetaPlayer extends Player {

    public static final int MAX_DEPTH = 10;

    public AlphaBetaPlayer(Game g, boolean p1, int valueOfParam) {
        super(g, p1);
        name = "AlphaBeta";
    }

    @Override
    public Action getMove(GameState state) {
        return game.getMinMaxAlphaBetaMove(state, MAX_DEPTH);
    }

}