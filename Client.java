package Socket_CN2020_Kirtik_Singh;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket; 

public class Client{

    public static void main(String args[]) throws IOException{

        // private Socket socket = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // private BufferedReader inputFromServer = null;
        // private DataOutputStream outputStreamToServer = null;

        try{
            // Make a new client side connection
            Socket clientSocket = new Socket("localhost", 9000);

            // Create an output stream  to server
            DataOutputStream dataOutput = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader dataInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Taking user input for the data to be sent
            int size = 5;
            System.out.println("Enter the matrix to be sent");

            // Send the number of nodes and the matrix to the server
            dataOutput.writeInt(size);
            for(int i = 0; i < size; i++){
                String matrixLine = reader.readLine();
                String[] numbers = matrixLine.split(" ");
                for(String num: numbers){
                    dataOutput.writeInt(Integer.parseInt(num));
                }
            }
            System.out.println("Enter the length of path, or n:");
            dataOutput.writeChar(Integer.parseInt(reader.readLine()));

            System.out.println("Enter the first node in Letters:");
            dataOutput.writeChar(reader.readLine().charAt(0));

            System.out.println("Enter the second node in Letters:");
            dataOutput.writeChar(reader.readLine().charAt(0));
            reader.close();
            dataOutput.close();
            clientSocket.close();
            

            // int nodes=5;
            // int[][] adjMatrix = {
            //     {0, 1, 0},
            //     {1, 0, 1},
            //     {0, 0, 0}
            }
        catch(Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }

        // dataOutput.writeInt(nodes);
        // dataOutput.flush();
        // for (int i = 0; i < nodes; i++)
        //     for (int j = 0; j < nodes; j++)
        //         dataOutput.writeInt(adjMatrix[i][j]);
        //         dataOutput.flush();    
}