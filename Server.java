package Socket_CN2020_Kirtik_Singh;

/**
 * Name: Kirtik Singh
 * Roll Number: 1810110109
 */

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
// import java.io.OutputStream;
// import java.io.DataOutputStream;
// import java.io.DataInput;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;

public class Server{

    // Declaration of a universal adjacency matrix
    static int[][] globalAdjMatrix = new int[5][5];

    public static boolean PathExistsDFS(int adj[][],int s,int d,boolean visited[],int n){  
        visited[s]=true;
        if(adj[s][d]!=0 && n==adj[s][d])
            return true;
        for(int i=0;i<adj.length;i++){
            if(adj[s][i]!=0 && !visited[i]){
                if(PathExistsDFS(adj,i,d,visited,n-adj[s][i]))
                    return true;
            }
        }
        visited[s]=false;
        return false;
    }

    public static void main(String args[]) throws IOException{

        // ArrayList<Integer>[] adjList;
        // ArrayList<Integer>[] adjList2;
        // List<List<Integer>> answerPathList = new ArrayList<>();
        Socket socket = null;
        PrintStream sendClient = null;
        DataOutputStream dataOutput = null;
        DataInputStream dataInput = null;
        ServerSocket serverSocket = null;
        

        try{
            // Create a server socket and bind it to the port number 
            serverSocket = new ServerSocket(9999); 
            System.out.println("Server has started: Connected");
            
            
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

            // System.out.println("Receiving length");

            // Reading the values of the Path Length as well as the nodes to be followed.
            int pathLength = dataInput.readInt();
            int node1 = dataInput.readInt();
            int node2 = dataInput.readInt();

            // System.out.println("Received other values");
            // System.out.println("Otther values of length node 1 and node 2");
            // System.out.println(pathLength);
            // System.out.println(node1);
            // System.out.println(node2);

            // Mapping letters to corresponding integral values
            // HashMap<Character, Integer> map = new HashMap<>();
            // map.put('A', 0);
            // map.put('B', 1);
            // map.put('C', 2);
            // map.put('D', 3);
            // map.put('E', 4);

            // Matrix creeation using input streams
            for (int i = 0; i < length; i++)
                for (int j = 0; j < length; j++)
                    globalAdjMatrix[i][j] = dataInput.readInt();
            
            System.out.println("Created matrix");
            // System.out.println(globalAdjMatrix);

            // Finding out the existence of path between the given inputs
            boolean[] visited = new boolean[length];
            boolean pathExists = PathExistsDFS(globalAdjMatrix, node1, node2, visited, pathLength);
            System.out.print(pathExists);

            // Sending out the response to the Client
            int response;
            response = pathExists ? 1 : 0;
            dataOutput.writeInt(response);

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