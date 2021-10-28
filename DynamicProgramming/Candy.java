import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
 
    static FastScanner fs;
    static FastWriter fw;
    static boolean checkOnlineJudge = /* System.getProperty("ONLINE_JUDGE") == null; */ false;
 
    private static final int mod = (int) (1e9 + 7);
 
    private static int m, n;
    private static int[][] grid;
 
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
 
        while (true) {
            m = fs.nextInt();
            n = fs.nextInt();
            if (m == 0 && n == 0)
                break;
 
            grid = new int[m][n];
            for (int i = 0; i < m; i++) {
                grid[i] = readIntArr(n);
            }
 
            int[] dp = new int[m + 5];
            Arrays.fill(dp, -1);
            int result = maxCandies(0, dp);
 
            fw.out.println(result);
        }
    }
 
    static int maxCandies(int i, int[] dp) {
 
        if (i >= m)
            return 0;
 
        if (dp[i] != -1)
            return dp[i];
 
        int[] util_dp = new int[n + 5];
        Arrays.fill(util_dp, -1);
        return dp[i] = Math.max(util(grid[i], util_dp, 0) + maxCandies(i + 2, dp), maxCandies(i + 1, dp));
    }
 
    static int util(int[] row, int[] util_dp, int j) {
 
        if (j >= n)
            return 0;
 
        if (util_dp[j] != -1)
            return util_dp[j];
 
        return util_dp[j] = Math.max(row[j] + util(row, util_dp, j + 2), util(row, util_dp, j + 1));
    }
 
    static int[] readIntArr(int _size) {
        int[] arr = new int[_size];
        for (int i = 0; i < _size; i++)
            arr[i] = fs.nextInt();
        return arr;
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