import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    Queue<state> queue;

    public BFS() {
        queue = new LinkedList<>();
    }

    public void runBFS(Graph graph) {
        queue.clear();
        for (int i = 0; i < graph.states.size(); i++) {
            graph.states.get(i).visited = false;
        }
        state startState = findState(graph, graph.start);
        startState.visited = true;
        String path = startState.getName() + "";
        queue.addAll(startState.stateList);
        boolean goalReached = false;
        while (!queue.isEmpty() && !goalReached) {
            state currentState = queue.remove();
            path = path + "-->" + currentState.getName();
            if (!currentState.visited) {
                currentState.visited = true;
                if (currentState.getName() == graph.end) {
                    System.out.println(path(currentState));
                    //System.out.println(path);
                    System.out.println("Goal Reached");
                    goalReached = true;
                } else {
                    queue.addAll(currentState.stateList);
                }

            }
        }
        if (!goalReached) {
            System.out.println("Path doesn't exists");
        }
    }

    public void findHeight(Graph graph, char deepestState) {
        queue.clear();
        for (int i = 0; i < graph.states.size(); i++) {
            graph.states.get(i).visited = false;
        }
        state startState = findState(graph, graph.start);
        startState.height = 0;
        startState.visited = true;
        queue.addAll(startState.stateList);
        boolean goalReached = false;
        while (!queue.isEmpty() && !goalReached) {
            state currentState = queue.remove();
            if (!currentState.visited) {
                currentState.visited = true;
                currentState.height = currentState.previous.height + 1;
                if (currentState.getName() == deepestState) {
                    //System.out.println(path(currentState));
                    //System.out.println("Goal Reached");
                    goalReached = true;
                } else {
                    queue.addAll(currentState.stateList);
                }
            }
        }
        for (int i = 0; i < graph.states.size(); i++) {
            if (graph.states.get(i).height > graph.maxHeight) {
                graph.maxHeight = graph.states.get(i).height;
            }
        }
    }


    state findState(Graph graph, char name) {
        Iterator<state> i = graph.states.iterator();
        state found = null;
        while (i.hasNext()) {
            state temp = i.next();
            if (temp.getName() == name) {
                found = temp;
            }
        }
        return found;
    }

    String path(state end) {
        StringBuilder print = new StringBuilder();
        state currentState = end;
        while (currentState != null) {
            if (currentState.visited) {
                print.insert(0, " --> " + currentState.getName());
            }
            currentState = currentState.previous;
        }
        return print.toString();
    }
}
