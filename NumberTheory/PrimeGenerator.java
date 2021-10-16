import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrimeGenerator {

    static FastScanner fs;
    static FastWriter fw;
    static boolean checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null;

    private static final int mod = (int) (1e9 + 7);
    private static final int N = (int) (1e5);
    private static ArrayList<Integer> primes;

    public static void main(String[] args) throws IOException {

        fs = new FastScanner();
        fw = new FastWriter();

        primes = sieve(N);

        int t = 1;
        t = fs.nextInt();
        while (t-- > 0) {
            solve();
        }

        fw.out.close();
    }

    static void solve() {

        int l = fs.nextInt(), r = fs.nextInt();
        segSieve(l, r);
        fw.out.println();
    }

    private static void segSieve(int l, int r) {

        if (l == 1)
            l++;

        int _size = r - l + 1;
        Boolean[] segPrimes = new Boolean[_size];
        Arrays.fill(segPrimes, true);

        for (int x: primes) {
            int firstMultiple = x * ((l + x - 1) / x);
            if (firstMultiple > r)
                break;

            if (firstMultiple == x)
                firstMultiple += x;

            for (int i = firstMultiple; i <= r; i += x) {
                segPrimes[i - l] = false;
            }
        }

        for (int i = 0; i < _size; i++) {
            if ( segPrimes[i])
                fw.out.println(i + l);
        }
    }

    private static ArrayList<Integer> sieve(int N) {

        Boolean[] primes = new Boolean[N + 5];
        Arrays.fill(primes, true);

        for (int i = 2; (i * i) <= N; i++) {
            if (primes[i]) {
                for (int j = i * i; j <= N; j += i)
                    primes[j] = false;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (primes[i])
                result.add(i);
        }

        return result;
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
