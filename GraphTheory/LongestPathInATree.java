import java.io.*;
import java.util.*;
 
public class Main {
 
    static FastScanner fs;
    static FastWriter fw;
    static boolean checkOnlineJudge = /* System.getProperty("ONLINE_JUDGE") == null; */ false;
 
    private static final int mod = (int) (1e9 + 7);
 
    private static List<ArrayList<Integer>> adjList;
    private static boolean[] visited;
    private static int result = 0;
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
 
        int n = fs.nextInt();
        if (n <= 1) {
            fw.out.println(0);
            return;
        }
 
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
 
        visited = new boolean[n];
        Arrays.fill(visited, false);
 
        for (int i = 0; i < (n - 1); i++)
            addEdge(fs.nextInt() - 1, fs.nextInt() - 1);
 
        dfs(0);
        fw.out.println(result);
    }
 
    private static int dfs(int node) {
 
        visited[node] = true;
        List<Integer> paths = new ArrayList<>(List.of(0, 0));
        for (int x: adjList.get(node)) {
            if (!visited[x]) {
                int curr = 1 + dfs(x);
                paths.add(curr);
            }
        }
 
        paths.sort(Collections.reverseOrder());
        result = Math.max(result, paths.get(0) + paths.get(1));
        return paths.get(0);
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