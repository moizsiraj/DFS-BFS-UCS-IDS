import java.util.Iterator;
import java.util.PriorityQueue;

public class UCS {
    int totalCost;
    PriorityQueue<state> openStates;
    state currentState;

    public UCS() {
        this.totalCost = 0;
        this.openStates = new PriorityQueue<state>(5, new state.UCSComparator());
    }

    public void runUCS(Graph graph) {
        boolean found = false;
        boolean lowPrevious = false;
        totalCost = 0;
        openStates.clear();
        for (int i = 0; i < graph.states.size(); i++) {
            graph.states.get(i).visited = false;
        }
        currentState = graph.findState(graph.start);
        Iterator<state> i = graph.states.iterator();
        while (i.hasNext()) {
            state temp = i.next();
            if (temp.name == currentState.name && temp.distFromPrev < currentState.distFromPrev) {
                currentState = temp;
            }
        }
        openStates.add(currentState);
        while (!found && totalCost <= openStates.peek().distFromStart) {
            currentState = openStates.poll();
            totalCost = currentState.distFromStart;
            currentState.visited = true;
            if (currentState.name == graph.end) {
                found = true;
                System.out.println(totalCost);
                System.out.println("Found");
            } else {
                Iterator<state> I = currentState.stateList.iterator();
                while (I.hasNext()) {
                    state temp = I.next();
                    if (!temp.visited) {
                        temp.distFromStart = temp.distFromPrev + currentState.distFromStart;
                        openStates.add(temp);
                    }
                }
            }
        }

    }
}
