package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.LinkedList;

public class DFS extends TreeSearch {

    public DFS(SearchProblem prob, State initial_state) {
        super(prob, initial_state);
        this.frontier = new LinkedList<>(); // Initialisation de la frontier
    }

    @Override
    public boolean solve() {
        // Ajout du noeud initial dans la frontier
        ((LinkedList<SearchNode>) frontier).addFirst(SearchNode.makeRootSearchNode(initial_state));
        while (!frontier.isEmpty()) {
            SearchNode node = ((LinkedList<SearchNode>) frontier).removeFirst();
            if (problem.isGoalState(node.getState())) {
                end_node = node;
                return true;
            }
            explored.add(node.getState());
            for (Action action : problem.getActions(node.getState())) {
                SearchNode child = SearchNode.makeChildSearchNode(problem, node, action);
                if (!explored.contains(child.getState()) && !frontier.contains(child)) {
                    ((LinkedList<SearchNode>) frontier).addFirst(child);
                }
            }
        }
        return false;
    }
}
