import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ABugsLife {

    static FastScanner fs;
    static FastWriter fw;
    static boolean checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null;

    private static final int mod = (int) (1e9 + 7);

    private static List<ArrayList<Integer>> adjList;
    private static boolean[] visited;
    private static int[] colors;

    public static void main(String[] args) throws IOException {

        fs = new FastScanner();
        fw = new FastWriter();

        int t = 1;
        t = fs.nextInt();
        int scenario = 1;
        while (scenario <= t) {
            solve(scenario);
            scenario++;
        }

        fw.out.close();
    }

    static void solve(int scenario) {

        int n = fs.nextInt();
        int m = fs.nextInt();

        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        for (int i = 0; i < m; i++)
            addEdge(fs.nextInt() - 1, fs.nextInt() - 1);

        visited = new boolean[n];
        colors = new int[n];
        Arrays.fill(visited, false);

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(i, 0)) {
                    printAnswer(scenario, true);
                    return;
                }
            }
        }

        printAnswer(scenario, false);
    }

    private static boolean dfs(int node, int color) {

        visited[node] = true;
        colors[node] = color;

        for (int x : adjList.get(node)) {
            if (!visited[x]) {
                if (dfs(x, (color ^ 1)))
                    return true;
            } else if (color == colors[x])
                return true;
        }

        return false;
    }

    private static void addEdge(int a, int b) {
        adjList.get(a).add(b);
        adjList.get(b).add(a);
    }

    private static void printAnswer(int scenario, boolean suspicious) {
        fw.out.println("Scenario #" + scenario + ":");
        fw.out.print(suspicious ? "Suspicious" : "No suspicious");
        fw.out.println(" bugs found!");
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() throws IOException {
            if (checkOnlineJudge)
                this.br = new BufferedReader(new FileReader("src/input.txt"));
            else
                this.br = new BufferedReader(new InputStreamReader(System.in));

            this.st = new StringTokenizer("");
        }

        public String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public String nextLine() {
            if (st.hasMoreTokens()) {
                return st.nextToken("").trim();
            }
            try {
                return br.readLine().trim();
            } catch (IOException err) {
                err.printStackTrace();
            }
            return "";
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    private static class FastWriter {
        PrintWriter out;

        FastWriter() throws IOException {
            if (checkOnlineJudge)
                out = new PrintWriter(new FileWriter("src/output.txt"));
            else
                out = new PrintWriter(System.out);
        }
    }
}
