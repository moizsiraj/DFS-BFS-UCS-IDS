import java.util.Iterator;
import java.util.Stack;


public class DFS {
    Stack<state> stack;

    public DFS() {
        this.stack = new Stack<>();
    }

    void runDFS(Graph graph) {
        stack.removeAllElements();
        for (int i = 0; i < graph.states.size(); i++) {
            graph.states.get(i).visited = false;
        }
        state startState = findState(graph, graph.start);
        String path = graph.start + "-->";
        startState.visited = true;
        for (int i = 0; i < startState.stateList.size(); i++) {
            state temp = startState.stateList.get(i);
            stack.push(temp);
        }
        boolean goalReached = false;
        while (!stack.empty() && !goalReached) {
            state currentState = stack.pop();
            if (!currentState.visited) {
                currentState.visited = true;
                path = path + "-->" + currentState.getName();
                if (currentState.getName() == graph.end) {
                    System.out.println(path(currentState));
                    //System.out.println(path);
                    System.out.println("Goal Reached");
                    goalReached = true;
                } else {
                    for (int i = 0; i < currentState.stateList.size(); i++) {
                        state temp = currentState.stateList.get(i);
                        stack.push(temp);
                    }
                }
            }
        }
        if (!goalReached) {
            System.out.println("Path doesn't exists");
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