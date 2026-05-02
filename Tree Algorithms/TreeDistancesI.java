import java.io.*;
import java.util.*;

public class TreeDistancesI {
    static int[] head, to, next;
    static int edgeIdx = 0;
    static int n;

    static void addEdge(int u, int v) {
        to[edgeIdx] = v;
        next[edgeIdx] = head[u];
        head[u] = edgeIdx++;
    }

    static int bfs(int src, int[] dist) {
        Arrays.fill(dist, -1);
        int[] q = new int[n + 5];
        int l = 0, r = 0;

        q[r++] = src;
        dist[src] = 0;
        int far = src;

        while (l < r) {
            int v = q[l++];
            for (int e = head[v]; e != -1; e = next[e]) {
                int u = to[e];
                if (dist[u] == -1) {
                    dist[u] = dist[v] + 1;
                    q[r++] = u;
                    if (dist[u] > dist[far]) far = u;
                }
            }
        }
        return far;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        n = fs.nextInt();

        head = new int[n + 1];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];

        for (int i = 0; i < n - 1; i++) {
            int a = fs.nextInt(), b = fs.nextInt();
            addEdge(a, b);
            addEdge(b, a);
        }

        int[] d1 = new int[n + 1];
        int[] d2 = new int[n + 1];

        int a = bfs(1, d1);
        int b = bfs(a, d1);
        bfs(b, d2);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(Math.max(d1[i], d2[i])).append(' ');
        }
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
            while ((c = read()) <= ' ') ;
            if (c == '-') {
                s = -1;
                c = read();
            }
            while (c > ' ') {
                x = x * 10 + c - '0';
                c = read();
            }
            return x * s;
        }
    }
}