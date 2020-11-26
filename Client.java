import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket; 

public class Client{

    public static void main(String args[]) throws IOException{
        
        // Make a new client side connection
        Socket clientSocket = new Socket("localhost", 9000);

        // Create an output stream
        DataOutputStream dataOutput = new DataOutputStream(clientSocket.getOutputStream());

        // Send data to the server

        // Send the number of nodes and the matrix

        // int nodes=3;
        // int[][] adjMatrix = {
        //     {0, 1, 0},
        //     {1, 0, 1},
        //     {0, 0, 0}
        // };

        dataOutput.writeInt(nodes);
        dataOutput.flush();
        for (int i = 0; i < nodes; i++)
            for (int j = 0; j < nodes; j++)
                dataOutput.writeInt(adjMatrix[i][j]);
                dataOutput.flush();
    }
}