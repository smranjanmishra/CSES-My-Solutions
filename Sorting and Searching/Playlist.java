import java.io.*;
import java.util.*;

public class Playlist {

    static void solve(FastScanner fs, PrintWriter out) throws Exception {
        int n = fs.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.nextInt();
        }

        Map<Integer, Integer> mp = new HashMap<>();
        int ans = 1;
        int i = 0, j = 0;

        while (j < n) {
            mp.put(arr[j], mp.getOrDefault(arr[j], 0) + 1);

            while (mp.get(arr[j]) > 1) {
                int cnt = mp.get(arr[i]) - 1;
                if (cnt == 0) {
                    mp.remove(arr[i]);
                } else {
                    mp.put(arr[i], cnt);
                }
                i++;
            }

            ans = Math.max(ans, mp.size());
            j++;
        }

        out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = 1;
        while (t-- > 0) {
            solve(fs, out);
        }
        out.flush();
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
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }
}