import java.io.*;
import java.util.*;

public class TreeMatching {
    static List<Integer>[] g;
    static int[][] dp;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        g=new ArrayList[n+1];
        for(int i=1;i<=n;i++)g[i]=new ArrayList<>();
        for(int i=0;i<n-1;i++){
            String[] s=br.readLine().split(" ");
            int a=Integer.parseInt(s[0]),b=Integer.parseInt(s[1]);
            g[a].add(b);
            g[b].add(a);
        }

        dp=new int[n+1][2];
        int[] parent=new int[n+1];
        int[] order=new int[n];
        int idx=0;

        Stack<Integer> st=new Stack<>();
        st.push(1);
        parent[1]=0;

        while(!st.isEmpty()){
            int v=st.pop();
            order[idx++]=v;
            for(int u:g[v]){
                if(u==parent[v])continue;
                parent[u]=v;
                st.push(u);
            }
        }

        for(int i=n-1;i>=0;i--){
            int v=order[i];
            int sum=0;
            for(int u:g[v]){
                if(u==parent[v])continue;
                sum+=Math.max(dp[u][0],dp[u][1]);
            }
            dp[v][0]=sum;

            for(int u:g[v]){
                if(u==parent[v])continue;
                int cur=1+dp[u][0]+(sum-Math.max(dp[u][0],dp[u][1]));
                dp[v][1]=Math.max(dp[v][1],cur);
            }
        }

        System.out.println(Math.max(dp[1][0],dp[1][1]));
    }
}