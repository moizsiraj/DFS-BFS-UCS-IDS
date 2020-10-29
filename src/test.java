public class test {
    public static void main(String[] args) {
        Graph graphOne = new Graph();
        Graph graphTwo = new Graph();
        Graph graphThree = new Graph();
        graphOne.generateGraph("G1.txt");
        graphTwo.generateGraph("G2.txt");
        graphThree.generateGraph("G3.txt");
        DFS dfs = new DFS();
        BFS bfs = new BFS();
        IDS ids = new IDS();
        UCS ucs = new UCS();
        System.out.println("----------------DFS----------------");
        dfs.runDFS(graphOne);
        dfs.runDFS(graphTwo);
        dfs.runDFS(graphThree);
        System.out.println("----------------BFS----------------");
        bfs.runBFS(graphOne);
        bfs.runBFS(graphTwo);
        bfs.runBFS(graphThree);
        System.out.println("----------------IDS----------------");
        ids.runIDS(graphOne, 'F');
        ids.runIDS(graphTwo, 'M');
        ids.runIDS(graphThree, 'B');
        System.out.println("----------------UCS----------------");
        ucs.runUCS(graphOne);
        ucs.runUCS(graphTwo);
        ucs.runUCS(graphThree);
    }
}
