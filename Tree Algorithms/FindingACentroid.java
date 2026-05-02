import java.io.*;
import java.util.*;

class FindingACentroid  {
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
        int[] stack=new int[n];
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

        int[] sz=new int[n+1];

        for(int i=n-1;i>=0;i--){
            int v=order[i];
            sz[v]=1;
            for(int e=head[v];e!=-1;e=next[e]){
                int u=to[e];
                if(u==parent[v])continue;
                sz[v]+=sz[u];
            }
        }

        int v=1;
        while(true){
            boolean moved=false;
            for(int e=head[v];e!=-1;e=next[e]){
                int u=to[e];
                if(u==parent[v])continue;
                if(sz[u]>n/2){
                    v=u;
                    moved=true;
                    break;
                }
            }
            if(!moved){
                if(parent[v]!=0 && (n - sz[v]) > n/2){
                    v=parent[v];
                }else{
                    break;
                }
            }
        }

        System.out.println(v);
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