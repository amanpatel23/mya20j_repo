import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static FastScanner fs;
    static FastWriter fw;
    static boolean checkOnlineJudge = /* System.getProperty("ONLINE_JUDGE") == null; */ false;

    private static final int mod = (int) (1e9 + 7);

    private static int n, m;
    private static List<ArrayList<Integer>> adjList;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        fs = new FastScanner();
        fw = new FastWriter();

        int t = 1;
        //t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }

        fw.out.close();
    }

    static void solve() {

        n = fs.nextInt();
        m = fs.nextInt();
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());

        for (int i = 0; i < m; i++)
            addEdge(fs.nextInt() - 1, fs.nextInt() - 1);

        visited = new boolean[n];
        Arrays.fill(visited, false);

        boolean result = isCycle();
        fw.out.println(result ? "NO" : "YES");
    }

    private static boolean isCycle() {

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (isCycleUtil(-1, i))
                    return true;
            }
        }

        return false;
    }

    private static boolean isCycleUtil(int parent, int node) {

        visited[node] = true;
        for (int x : adjList.get(node)) {
            if (!visited[x]) {
                if (isCycleUtil(node, x))
                    return true;
            } else if (x != parent)
                return true;
        }

        return false;
    }

    private static void addEdge(int a, int b) {
        adjList.get(a).add(b);
        adjList.get(b).add(a);
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
