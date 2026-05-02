import java.io.*;
import java.util.*;

class DistanceQueries {
    static final int LOG = 20;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt(), q = fs.nextInt();

        int[] head = new int[n + 1];
        Arrays.fill(head, -1);
        int[] to = new int[2 * n];
        int[] next = new int[2 * n];
        int idx = 0;

        for (int i = 0; i < n - 1; i++) {
            int a = fs.nextInt(), b = fs.nextInt();
            to[idx] = b; next[idx] = head[a]; head[a] = idx++;
            to[idx] = a; next[idx] = head[b]; head[b] = idx++;
        }

        int[][] up = new int[LOG][n + 1];
        int[] depth = new int[n + 1];

        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 1;
        up[0][1] = 0;
        depth[1] = 0;

        while (top > 0) {
            int v = stack[--top];
            for (int e = head[v]; e != -1; e = next[e]) {
                int u = to[e];
                if (u == up[0][v]) continue;
                up[0][u] = v;
                depth[u] = depth[v] + 1;
                stack[top++] = u;
            }
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

            int u = a, v = b;

            if (depth[u] < depth[v]) {
                int t = u; u = v; v = t;
            }

            int diff = depth[u] - depth[v];
            for (int j = 0; j < LOG; j++) {
                if (((diff >> j) & 1) == 1) {
                    u = up[j][u];
                }
            }

            if (u != v) {
                for (int j = LOG - 1; j >= 0; j--) {
                    if (up[j][u] != up[j][v]) {
                        u = up[j][u];
                        v = up[j][v];
                    }
                }
                u = up[0][u];
            }

            int lca = u;
            int dist = depth[a] + depth[b] - 2 * depth[lca];
            sb.append(dist).append('\n');
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