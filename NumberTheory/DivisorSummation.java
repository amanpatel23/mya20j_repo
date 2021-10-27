import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static FastScanner fs;
    static FastWriter fw;
    static boolean checkOnlineJudge = /* System.getProperty("ONLINE_JUDGE") == null; */ false;

    private static final int mod = (int) (1e9 + 7);
    private static final int maxN = (int) (5e5);
    private static final long[] arr = new long[maxN + 5];

    public static void main(String[] args) throws IOException {

        fs = new FastScanner();
        fw = new FastWriter();

        precompute();
        int t = 1;
        t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }

        fw.out.close();
    }

    static void precompute() {

        Arrays.fill(arr, 1);
        arr[1] = 0;
        for (int i = 2; (i * i) <= maxN; i++) {
            for (int j = (2 * i); j <= maxN; j += i) {
                arr[j] += i;
                int k = j / i;
                if (((long) k * k) > maxN)
                    arr[j] += k;
            }
        }
    }

    static void solve() {

        int n = fs.nextInt();
        long result = arr[n];
        fw.out.println(result);
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
