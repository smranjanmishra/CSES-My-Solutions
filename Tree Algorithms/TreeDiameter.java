import java.io.*;
import java.util.*;

public class TreeDiameter {
    static List<Integer>[] g;
    static int n;

    static int bfs(int src,int[] dist){
        Arrays.fill(dist,-1);
        Queue<Integer> q=new ArrayDeque<>();
        q.add(src);
        dist[src]=0;
        int far=src;
        while(!q.isEmpty()){
            int v=q.poll();
            for(int u:g[v]){
                if(dist[u]==-1){
                    dist[u]=dist[v]+1;
                    q.add(u);
                    if(dist[u]>dist[far])far=u;
                }
            }
        }
        return far;
    }

    public static void main(String[] args) throws Exception{
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

        int[] dist=new int[n+1];
        int a=bfs(1,dist);
        int b=bfs(a,dist);

        System.out.println(dist[b]);
    }
}