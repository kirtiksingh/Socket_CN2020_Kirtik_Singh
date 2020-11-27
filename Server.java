package Socket_CN2020_Kirtik_Singh;

/**
 * Name: Kirtik Singh
 * Roll Number: 1810110109
 */

import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
// import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server{

    // Global adjmatrix
    static int[][] globalAdjMatrix = new int[5][5];

    public static boolean DFS(int adj[][],int s,int d,boolean vis[],int n){  
        vis[s]=true;
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

    public static void main(String args[]) throws IOException{

        // ArrayList<Integer>[] adjList;
        // ArrayList<Integer>[] adjList2;
        List<List<Integer>> answerPathList = new ArrayList<>();
        Socket socket = null;
        PrintStream sendClient = null;
        DataOutputStream dataOutput = null;
        DataInputStream dataInput = null;
        

        try{
            // create a server socket and bind it to the port number 
            var serverSocket = new ServerSocket(9999); 
            System.out.println("Server has started: Connected");
            
            // // Mapping Letters to Respective Node Numbers
            // HashMap<Character, Integer> characterMappings = new HashMap<>();
            // characterMappings.put('A', 0);
            // characterMappings.put('B', 1);
            // characterMappings.put('C', 2);
            // characterMappings.put('D', 3);
            // characterMappings.put('E', 4);

            socket = serverSocket.accept();
            System.out.println("Client has been successfully accepted!");

            // Receiving Input fromt the client
            dataInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            // For writing the output to the client
            sendClient = new PrintStream(socket.getOutputStream());

            // Stream to output the image to the client
            dataOutput = new DataOutputStream(socket.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }

        try {
            // Receive the dimension of the matrix 
            int length = dataInput.readInt();

            System.out.println("Receiving length");

            // // Receiving the matrix sent by the client and creating an adjacency list
            // adjList = new ArrayList[length];
            // adjList2 = new ArrayList[length];

            // for (int i = 0; i < length; i++) {
            //     adjList[i] = new ArrayList<>();
            //     adjList2[i] = new ArrayList<>();
            // }
            // for (int i = 0; i < length; i++) {
            //     for (int j = 0; j < length; j++) {
            //         int next = dataInput.readInt();
            //         adjList2[i].add(next);
            //         if (next == 1) {
            //             adjList[i].add(j);
            //         }
            //     }
            // }

            // Reading the values of the Path Length as well as the nodes to be followed.
            int pathLength = dataInput.readInt();
            int node1 = dataInput.readInt();
            int node2 = dataInput.readInt();

            System.out.println("Received other values");

            System.out.println("Otther values of length node 1 and node 2");
            System.out.println(pathLength);
            System.out.println(node1);
            System.out.println(node2);

            // // Mapping letters to corresponding integral values
            // HashMap<Character, Integer> map = new HashMap<>();
            // map.put('A', 0);
            // map.put('B', 1);
            // map.put('C', 2);
            // map.put('D', 3);
            // map.put('E', 4);

            System.out.println("Made char map");
            // System.out.println(map.get(node1));
            // System.out.println(map.get(node2));


            // Collect the nodes and the matrix through the data
            for (int i = 0; i < length; i++)
                for (int j = 0; j < length; j++)
                    globalAdjMatrix[i][j] = dataInput.readInt();
            
            System.out.println("Made matrix");
            System.out.println(globalAdjMatrix);

            boolean[] vis = new boolean[3];            
            // DFS(globalAdjMatrix, map.get(node1), map.get(node2), vis, pathLength);

            boolean pathExists = DFS(globalAdjMatrix, node1, node2, vis, pathLength);
            System.out.print(pathExists);

            int response;
            response = pathExists ? 0 : 1;            

            // Send the response
            dataOutput.writeInt(response);


            // DFS(map.get(node1), map.get(node2), length, pathLength)

            // // Function which runs the algorithm
            // getAllPaths(map.get(node1), map.get(node2), length, pathLength);

            // // return the String to client {YES/NO}
            // if (answerPathList.size() > 0) {
            //     sendClient.println("Yes, there exists a path of length " + pathLength + " from node " + node1 + " to node " + node2 + ".");
            // } else {
            //     sendClient.println("No, there is no path of length " + pathLength + " from node " + node1 + " to node " + node2 + ".");
            // }

            // // open the JavaFrame for cutom painting
            // GraphFrame gf = new GraphFrame(adjList2, outputStream);

        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Closing the connection and socket");
        try {
            socket.close();
            dataInput.close();
            sendClient.close();
        } catch (IOException e) {
            System.out.println(e);
        }
            
        }
}