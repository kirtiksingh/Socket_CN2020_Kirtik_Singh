import java.io.DataInputStream;
// import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket; 

public class Server{
    public static void main(String args[]) throws IOException{
        // create a server socket and bind it to the port number 
        var serverSocket = new ServerSocket(9000); 
        System.out.println("Server has been started");
        while(true){
                
            // Create a new socket to establish a virtual pipe 
            // with the client side (LISTEN)
            Socket socket = serverSocket.accept(); 
                
            // Create a datainput stream object to communicate with the client (Connect)
            DataInputStream input = new DataInputStream(socket.getInputStream()); 
    
            
            // Collect the nodes and the matrix through the data
            int nodes = input.readInt();
            int adjMatrix[][] = new int[nodes][nodes]; // Create the matrix
            for (int i = 0; i < nodes; i++)
                for (int j = 0; j < nodes; j++)
                    adjMatrix[i][j] = input.readInt();
        }
    }
}