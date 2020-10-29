import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class Graph {

    LinkedList<state> states;
    String[] hValueArray;
    String[] pathArray;
    String[] startEnd;
    int maxHeight = 0;
    char start;
    char end;

    public Graph() {
        states = new LinkedList<>();
    }

    public void generateGraph(String fileName) {
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains("heuristic")) {
                    data = myReader.nextLine();
                    hValueArray = data.split(" ");
                    //System.out.println(Arrays.toString(hValueArray));
                    createAddStates(hValueArray);
                }
                if (data.contains("matrix Path")) {
                    StringBuilder list = new StringBuilder();
                    data = myReader.nextLine();
                    while (!data.contains("goal")) {
                        list.append(data).append(",");
                        data = myReader.nextLine();
                    }
                    pathArray = list.toString().split(",");
                    addVertex(pathArray);
                }
                if (data.contains("goal")) {
                    startEnd = myReader.nextLine().split(" ");
                    start = startEnd[0].charAt(0);
                    end = startEnd[1].charAt(0);
                }
            }
//            System.out.println(Arrays.toString(hValueArray));
//            System.out.println(Arrays.toString(pathArray));
//            System.out.println(start);
//            System.out.println(end);
            myReader.close();
        } catch (FileNotFoundException | CloneNotSupportedException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void createAddStates(String[] hValueArray) {
        char name = 'A';
        for (int i = 0; i < hValueArray.length; i++) {
            int hValue = Integer.parseInt(hValueArray[i]);
            state newState = new state(name, hValue);
            states.add(newState);
            name++;
        }
    }

    private void addVertex(String[] pathArray) throws CloneNotSupportedException {
        for (int i = 0; i < pathArray.length; i++) {
            char stateName = pathArray[i].charAt(0);
            char neighborName = pathArray[i].charAt(2);
            int pathLength = Character.getNumericValue(pathArray[i].charAt(4));
            state State = findState(stateName);
            state neighborState = findState(neighborName);
            if (neighborState.previous == null) {
                State.addNeighbour(neighborState, neighborName, pathLength);
            } else {
                state newState = new state(neighborName, neighborState.hValue);
                this.states.add(newState);
                State.addNeighbour(newState, neighborName, pathLength);
            }
        }
    }

    public state findState(char name) {
        Iterator<state> i = states.iterator();
        state State = null;
        while (i.hasNext()) {
            state currentState = i.next();
            if (currentState.getName() == name) {
                State = currentState;
                break;
            }
        }
        return State;
    }

}

