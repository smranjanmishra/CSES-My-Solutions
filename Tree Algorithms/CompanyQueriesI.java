import java.io.*;
import java.util.*;

class CompanyQueriesI {
    static final int LOG = 20;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt(), q = fs.nextInt();

        int[][] up = new int[LOG][n + 1];

        for (int i = 2; i <= n; i++) {
            up[0][i] = fs.nextInt();
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                int p = up[j - 1][i];
                up[j][i] = (p == 0) ? 0 : up[j - 1][p];
            }
        }

        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            int x = fs.nextInt(), k = fs.nextInt();

            for (int j = 0; j < LOG; j++) {
                if (((k >> j) & 1) == 1) {
                    x = up[j][x];
                    if (x == 0) break;
                }
            }

            sb.append(x == 0 ? -1 : x).append('\n');
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