package ia.problemes;

import java.util.Arrays;
import java.util.ArrayList;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.common.Misc;
import ia.framework.jeux.GameState;

/**
 * Représente un état d'un jeu générique m,n,k Game 
 */

public class MnkGameState extends AbstractMnkGameState {

   
    /**
     * Construire une grille vide de la bonne taille
     *
     * @param r nombre de lignes
     * @param c nombre de colonnes 
     */
    public MnkGameState(int r, int c, int s) {
        super(r,c,s);
    }

    public MnkGameState cloneState() {
        MnkGameState new_s = new MnkGameState(this.rows, this.cols, this.streak);
        new_s.board = this.board.clone();
        new_s.player_to_move = player_to_move;
        new_s.game_value = game_value;
        if(this.last_action != null)
            new_s.last_action = this.last_action.clone();
        for (Pair p: this.winning_move)
            new_s.winning_move.add(p.clone());
        return new_s;
	}
    /**
     * Un fonction d'évaluation pour cet état du jeu. 
     * Permet de comparer différents états dans le cas ou on ne  
     * peut pas développer tout l'arbre. Le joueur 1 (X) choisira les
     * actions qui mènent au état de valeur maximal, Le joueur 2 (O)
     * choisira les valeurs minimal.
     * 
     * Cette fonction dépend du jeu.
     * 
     * @return la valeur du jeux
     **/
    protected double evaluationFunction() {
        int maxScore = evaluatePlayer(1); // Score du joueur MAX (X)
        int minScore = evaluatePlayer(2); // Score du joueur MIN (O)

        return maxScore - minScore; // Alpha-Beta maximise pour MAX et minimise pour MIN
    }

    /**
     * Évalue les opportunités du joueur donné dans l'état actuel du jeu.
     * On attribue un score basé sur les lignes ouvertes et les menaces.
     */
    private int evaluatePlayer(int player) {
        int score = 0;

        // Vérification des lignes gagnantes potentielles (lignes, colonnes, diagonales)
        for (int[] line : getAllPossibleLines()) {
            int countPlayer = 0;
            int countEmpty = 0;

            for (int cell : line) {
                if (cell == player) countPlayer++;
                else if (cell == 0) countEmpty++;
            }

            if (countPlayer == streak - 1 && countEmpty == 1) {
                score += 100; // Menace immédiate
            } else if (countPlayer == streak - 2 && countEmpty == 2) {
                score += 10; // Bonne position pour l’avenir
            } else if (countPlayer == streak - 3 && countEmpty == 3) {
                score += 3; // Opportunité à long terme
            }
        }

        return score;
    }

    /**
     * Récupère toutes les lignes, colonnes et diagonales du plateau.
     */
    private ArrayList<int[]> getAllPossibleLines() {
        ArrayList<int[]> lines = new ArrayList<>();

        // Parcours lignes et colonnes
        for (int i = 0; i < rows; i++) {
            int[] row = new int[cols];
            int[] col = new int[rows];
            for (int j = 0; j < cols; j++) {
                row[j] = board[i * cols + j]; // Ligne
                col[j] = board[j * cols + i]; // Colonne
            }
            lines.add(row);
            lines.add(col);
        }

        // Parcours diagonales
        lines.addAll(getDiagonals());

        return lines;
    }

    /**
     * Récupère toutes les diagonales du plateau.
     */
    private ArrayList<int[]> getDiagonals() {
        ArrayList<int[]> diagonals = new ArrayList<>();

        for (int i = 0; i <= rows - streak; i++) {
            for (int j = 0; j <= cols - streak; j++) {
                int[] diag1 = new int[streak];
                int[] diag2 = new int[streak];
                for (int k = 0; k < streak; k++) {
                    diag1[k] = board[(i + k) * cols + (j + k)];
                    diag2[k] = board[(i + k) * cols + (j + streak - 1 - k)];
                }
                diagonals.add(diag1);
                diagonals.add(diag2);
            }
        }
        return diagonals;
    }



}
