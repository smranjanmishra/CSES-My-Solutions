import java.io.*;

public class Subordinates {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in));
        StringBuilder sb = new StringBuilder();

        int n = nextInt(in);
        int[] boss = new int[n + 1];
        int[] childCount = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            boss[i] = nextInt(in);
            childCount[boss[i]]++;
        }

        // Build CSR-style children adjacency: head[v] points into 'children' array.
        int[] head = new int[n + 2];
        for (int i = 1; i <= n; i++) head[i + 1] = head[i] + childCount[i];
        int[] children = new int[n]; // total edges = n-1, but size n is safe
        int[] idx = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int p = boss[i];
            children[head[p] + idx[p]++] = i;
        }

        int[] subs = new int[n + 1];

        // Iterative post-order using an explicit stack of (node, nextChildIndex).
        int[] stackNode = new int[n + 1];
        int[] stackIter = new int[n + 1];
        int top = 0;
        stackNode[top] = 1;
        stackIter[top] = 0;

        while (top >= 0) {
            int u = stackNode[top];
            int it = stackIter[top];
            if (it < childCount[u]) {
                stackIter[top]++;
                int c = children[head[u] + it];
                top++;
                stackNode[top] = c;
                stackIter[top] = 0;
            } else {
                // All children processed; aggregate into parent.
                int total = 0;
                int start = head[u];
                int end = start + childCount[u];
                for (int k = start; k < end; k++) {
                    total += subs[children[k]] + 1;
                }
                subs[u] = total;
                top--;
            }
        }

        for (int i = 1; i <= n; i++) {
            sb.append(subs[i]);
            if (i < n) sb.append(' ');
        }
        sb.append('\n');

        System.out.print(sb);
    }

    private static int nextInt(DataInputStream in) throws IOException {
        int c, n = 0;
        do { c = in.read(); } while (c != -1 && c <= ' ');
        boolean neg = false;
        if (c == '-') { neg = true; c = in.read(); }
        while (c > ' ') {
            n = n * 10 + c - '0';
            c = in.read();
        }
        return neg ? -n : n;
    }
}