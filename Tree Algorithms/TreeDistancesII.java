import java.io.*;
import java.util.*;

public class TreeDistancesII {
    static int n;
    static int[] head,to,next;
    static int idx=0;

    static void addEdge(int u,int v){
        to[idx]=v;
        next[idx]=head[u];
        head[u]=idx++;
    }

    public static void main(String[] args) throws Exception{
        FastScanner fs=new FastScanner(System.in);
        n=fs.nextInt();

        head=new int[n+1];
        Arrays.fill(head,-1);
        to=new int[2*n];
        next=new int[2*n];

        for(int i=0;i<n-1;i++){
            int a=fs.nextInt(),b=fs.nextInt();
            addEdge(a,b);
            addEdge(b,a);
        }

        int[] parent=new int[n+1];
        int[] order=new int[n];
        int[] sz=new int[n+1];
        long[] dp=new long[n+1];

        int[] st=new int[n];
        int top=0;
        st[top++]=1;
        parent[1]=0;
        int oi=0;

        while(top>0){
            int v=st[--top];
            order[oi++]=v;
            for(int e=head[v];e!=-1;e=next[e]){
                int u=to[e];
                if(u==parent[v])continue;
                parent[u]=v;
                st[top++]=u;
            }
        }

        for(int i=n-1;i>=0;i--){
            int v=order[i];
            sz[v]=1;
            for(int e=head[v];e!=-1;e=next[e]){
                int u=to[e];
                if(u==parent[v])continue;
                sz[v]+=sz[u];
                dp[v]+=dp[u]+sz[u];
            }
        }

        long[] ans=new long[n+1];
        ans[1]=dp[1];

        for(int i=0;i<n;i++){
            int v=order[i];
            for(int e=head[v];e!=-1;e=next[e]){
                int u=to[e];
                if(u==parent[v])continue;
                ans[u]=ans[v]-sz[u]+(n-sz[u]);
            }
        }

        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++)sb.append(ans[i]).append(' ');
        System.out.println(sb);
    }

    static class FastScanner{
        private final InputStream in;
        private final byte[] buffer=new byte[1<<16];
        private int ptr=0,len=0;

        FastScanner(InputStream is){in=is;}

        private int read() throws IOException{
            if(ptr>=len){
                len=in.read(buffer);
                ptr=0;
                if(len<=0)return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException{
            int c,s=1,x=0;
            while((c=read())<=32);
            if(c=='-'){s=-1;c=read();}
            while(c>32){
                x=x*10+c-'0';
                c=read();
            }
            return x*s;
        }
    }
}