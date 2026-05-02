import java.io.*;
import java.util.*;

class PathQueriesII {
    static int n,q;
    static int[] head,to,next;
    static int idx=0;

    static void addEdge(int u,int v){
        to[idx]=v;
        next[idx]=head[u];
        head[u]=idx++;
    }

    static int[] parent,depth,heavy,headChain,pos,sz;
    static int curPos=0;

    static int[] seg;

    public static void main(String[] args) throws Exception{
        FastScanner fs=new FastScanner(System.in);
        n=fs.nextInt();
        q=fs.nextInt();

        int[] val=new int[n+1];
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

        parent=new int[n+1];
        depth=new int[n+1];
        heavy=new int[n+1];
        headChain=new int[n+1];
        pos=new int[n+1];
        sz=new int[n+1];

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
                depth[u]=depth[v]+1;
                stack[top++]=u;
            }
        }

        for(int i=n-1;i>=0;i--){
            int v=order[i];
            sz[v]=1;
            int max=0;
            for(int e=head[v];e!=-1;e=next[e]){
                int u=to[e];
                if(u==parent[v])continue;
                sz[v]+=sz[u];
                if(sz[u]>max){
                    max=sz[u];
                    heavy[v]=u;
                }
            }
        }

        for(int i=1;i<=n;i++){
            if(parent[i]==0||heavy[parent[i]]!=i){
                for(int j=i;j!=0;j=heavy[j]){
                    headChain[j]=i;
                    pos[j]=++curPos;
                }
            }
        }

        int size=1;
        while(size<n)size<<=1;
        seg=new int[2*size];

        for(int i=1;i<=n;i++){
            seg[size+pos[i]-1]=val[i];
        }

        for(int i=size-1;i>0;i--){
            seg[i]=Math.max(seg[2*i],seg[2*i+1]);
        }

        StringBuilder sb=new StringBuilder();

        while(q-->0){
            int t=fs.nextInt();
            if(t==1){
                int s=fs.nextInt();
                int x=fs.nextInt();
                int p=size+pos[s]-1;
                seg[p]=x;
                for(p>>=1;p>0;p>>=1){
                    seg[p]=Math.max(seg[2*p],seg[2*p+1]);
                }
            }else{
                int a=fs.nextInt(),b=fs.nextInt();
                int res=0;

                while(headChain[a]!=headChain[b]){
                    if(depth[headChain[a]]<depth[headChain[b]]){
                        int t2=a;a=b;b=t2;
                    }
                    res=Math.max(res,query(pos[headChain[a]],pos[a],seg,size));
                    a=parent[headChain[a]];
                }

                if(depth[a]>depth[b]){
                    int t2=a;a=b;b=t2;
                }
                res=Math.max(res,query(pos[a],pos[b],seg,size));

                sb.append(res).append('\n');
            }
        }

        System.out.print(sb);
    }

    static int query(int l,int r,int[] seg,int size){
        l=l+size-1;
        r=r+size-1;
        int res=0;
        while(l<=r){
            if((l&1)==1)res=Math.max(res,seg[l++]);
            if((r&1)==0)res=Math.max(res,seg[r--]);
            l>>=1;
            r>>=1;
        }
        return res;
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