import java.util.Comparator;
import java.util.LinkedList;

public class state implements Cloneable {
    char name;
    state previous;
    int height;
    LinkedList<state> stateList;
    int hValue;
    int aValue;
    boolean visited;
    int distFromPrev;
    int distFromStart;

    public state(char name, int hValue) {
        this.name = name;
        this.hValue = hValue;
        stateList = new LinkedList<>();
        previous = null;
    }

    state createState() throws CloneNotSupportedException {
        state State = (state) this.clone();
        return State;
    }

    void addNeighbour(state neighbour, char name, int pathLength) {
        stateList.add(neighbour);
        neighbour.stateList.add(this);
        neighbour.distFromPrev = pathLength;
        neighbour.previous = this;
    }

    char getName() {
        return name;
    }

    static class UCSComparator implements Comparator<state> {
        @Override
        public int compare(state s1, state s2) {
            if (s1.distFromStart > s2.distFromStart)
                return 1;
            else if (s1.distFromStart < s2.distFromStart)
                return -1;
            return 0;
        }
    }

    class BFSComparator implements Comparator<state> {
        @Override
        public int compare(state s1, state s2) {
            if (s1.hValue < s2.hValue)
                return 1;
            else if (s1.hValue > s2.hValue)
                return -1;
            return 0;
        }
    }

    class AComparator implements Comparator<state> {
        @Override
        public int compare(state s1, state s2) {
            if (s1.aValue < s2.aValue)
                return 1;
            else if (s1.aValue > s2.aValue)
                return -1;
            return 0;
        }
    }


    public Object clone() throws CloneNotSupportedException {
        // Assign the shallow copy to new reference variable t
        state newState = (state) super.clone();
        return newState;
    }

}




