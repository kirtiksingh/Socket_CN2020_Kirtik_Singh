public class Distance{
    public static boolean DFS(int adj[][],int s,int d,boolean vis[],int n)
    {  vis[s]=true;
        if(adj[s][d]!=0 && n==adj[s][d])
            return true;
        for(int i=0;i<adj.length;i++){
            if(adj[s][i]!=0 && !vis[i]){
                if(DFS(adj,i,d,vis,n-adj[s][i]))
                    return true;
            }
        }
        vis[s]=false;
        return false;
    }

    public static void main(String args[]){
        // int[][] array = new int[3][3];
        int[][] array = {
            {0, 1, 0},
            {1, 0, 1},
            {0, 0, 0}
        };
        boolean[] vis = new boolean[3];
        
        // for (int i = 0; i < 3; i++) 
        //     for (int j = 0; j < 3; j++) 
        //         System.out.println("array[" + i + "][" + j + "] = "
        //                            + array[i][j]); 

        System.out.println(DFS(array, 0, 2, vis, 2));
    }
}
