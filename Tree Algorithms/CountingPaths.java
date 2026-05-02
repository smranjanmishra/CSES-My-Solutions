import java.io.*;
import java.util.*;

class CountingPaths {
    static final int LOG = 20;

    static int n, m;
    static int[] head, to, next;
    static int idx = 0;

    static void addEdge(int u, int v) {
        to[idx] = v;
        next[idx] = head[u];
        head[u] = idx++;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        n = fs.nextInt();
        m = fs.nextInt();

        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        for (int i = 0; i < n - 1; i++) {
            int a = fs.nextInt(), b = fs.nextInt();
            addEdge(a, b);
            addEdge(b, a);
        }

        int[][] up = new int[LOG][n + 1];
        int[] depth = new int[n + 1];

        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 1;

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

        int[] cnt = new int[n + 1];

        for (int i = 0; i < m; i++) {
            int a = fs.nextInt(), b = fs.nextInt();

            int u = a, v = b;

            if (depth[u] < depth[v]) {
                int t = u; u = v; v = t;
            }

            int diff = depth[u] - depth[v];
            for (int j = 0; j < LOG; j++) {
                if (((diff >> j) & 1) == 1) u = up[j][u];
            }

            int lca;
            if (u == v) {
                lca = u;
            } else {
                for (int j = LOG - 1; j >= 0; j--) {
                    if (up[j][u] != up[j][v]) {
                        u = up[j][u];
                        v = up[j][v];
                    }
                }
                lca = up[0][u];
            }

            cnt[a]++;
            cnt[b]++;
            cnt[lca]--;
            if (up[0][lca] != 0) cnt[up[0][lca]]--;
        }

        int[] order = new int[n];
        int[] parent = new int[n + 1];
        int oi = 0;

        int[] st = new int[n];
        int top2 = 0;
        st[top2++] = 1;

        while (top2 > 0) {
            int v = st[--top2];
            order[oi++] = v;
            for (int e = head[v]; e != -1; e = next[e]) {
                int u = to[e];
                if (u == parent[v]) continue;
                parent[u] = v;
                st[top2++] = u;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int v = order[i];
            for (int e = head[v]; e != -1; e = next[e]) {
                int u = to[e];
                if (u == parent[v]) continue;
                cnt[v] += cnt[u];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) sb.append(cnt[i]).append(' ');
        System.out.println(sb);
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
            while ((c = read()) <= 32);
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