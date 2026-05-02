import java.io.*;
import java.util.*;

class SubtreeQueries {
    static int n,q;
    static int[] head,to,next;
    static int idx=0;

    static void addEdge(int u,int v){
        to[idx]=v;
        next[idx]=head[u];
        head[u]=idx++;
    }

    static long[] bit;
    static int[] tin,tout;
    static int timer=0;

    static void update(int i,long v){
        for(;i<=n;i+=i&-i)bit[i]+=v;
    }

    static long query(int i){
        long s=0;
        for(;i>0;i-=i&-i)s+=bit[i];
        return s;
    }

    static long range(int l,int r){
        return query(r)-query(l-1);
    }

    public static void main(String[] args) throws Exception{
        FastScanner fs=new FastScanner(System.in);
        n=fs.nextInt();
        q=fs.nextInt();

        long[] val=new long[n+1];
        for(int i=1;i<=n;i++)val[i]=fs.nextInt();

        head=new int[n+1];
        Arrays.fill(head,-1);
        to=new int[2*n];
        next=new int[2*n];

        for(int i=0;i<n-1;i++){
            int a=fs.nextInt(),b=fs.nextInt();
            addEdge(a,b);
            addEdge(b,a);
        }

        tin=new int[n+1];
        tout=new int[n+1];

        int[] parent=new int[n+1];
        int[] stack=new int[n];
        int[] order=new int[n];
        int top=0,oi=0;

        stack[top++]=1;
        parent[1]=0;

        while(top>0){
            int v=stack[--top];
            order[oi++]=v;
            for(int e=head[v];e!=-1;e=next[e]){
                int u=to[e];
                if(u==parent[v])continue;
                parent[u]=v;
                stack[top++]=u;
            }
        }

        for(int i=0;i<n;i++){
            tin[order[i]]=i+1;
        }

        for(int i=n-1;i>=0;i--){
            int v=order[i];
            tout[v]=tin[v];
            for(int e=head[v];e!=-1;e=next[e]){
                int u=to[e];
                if(u==parent[v])continue;
                tout[v]=Math.max(tout[v],tout[u]);
            }
        }

        bit=new long[n+1];
        for(int i=1;i<=n;i++){
            update(tin[i],val[i]);
        }

        StringBuilder sb=new StringBuilder();

        while(q-->0){
            int t=fs.nextInt();
            if(t==1){
                int s=fs.nextInt();
                long x=fs.nextInt();
                update(tin[s],x-val[s]);
                val[s]=x;
            }else{
                int s=fs.nextInt();
                sb.append(range(tin[s],tout[s])).append('\n');
            }
        }

        System.out.print(sb);
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