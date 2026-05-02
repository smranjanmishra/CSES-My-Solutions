import java.io.*;
import java.util.*;

class CompanyQueriesII {
    static final int LOG = 20;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt(), q = fs.nextInt();

        int[][] up = new int[LOG][n + 1];
        int[] depth = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            int p = fs.nextInt();
            up[0][i] = p;
            depth[i] = depth[p] + 1;
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                int p = up[j - 1][i];
                up[j][i] = (p == 0) ? 0 : up[j - 1][p];
            }
        }

        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            int a = fs.nextInt(), b = fs.nextInt();

            if (depth[a] < depth[b]) {
                int t = a; a = b; b = t;
            }

            int d = depth[a] - depth[b];
            for (int j = 0; j < LOG; j++) {
                if (((d >> j) & 1) == 1) {
                    a = up[j][a];
                }
            }

            if (a == b) {
                sb.append(a).append('\n');
                continue;
            }

            for (int j = LOG - 1; j >= 0; j--) {
                if (up[j][a] != up[j][b]) {
                    a = up[j][a];
                    b = up[j][b];
                }
            }

            sb.append(up[0][a]).append('\n');
        }

        System.out.print(sb);
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, s = 1, x = 0;
            while ((c = read()) <= 32) ;
            if (c == '-') {
                s = -1;
                c = read();
            }
            while (c > 32) {
                x = x * 10 + c - '0';
                c = read();
            }
            return x * s;
        }
    }
}