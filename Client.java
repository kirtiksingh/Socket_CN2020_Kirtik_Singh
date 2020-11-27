package Socket_CN2020_Kirtik_Singh;

/**
 * Name: Kirtik Singh
 * Roll Number: 1810110109
 */

// Imports for Required Functions
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
// import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client{

    public static void main(String args[]) throws IOException{

        // private Socket socket = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // private BufferedReader inputFromServer = null;
        // private DataOutputStream outputStreamToServer = null;

        try{
            // Make a new client side connection
            Socket clientSocket = new Socket("localhost", 9999);

            // Create an output stream  to server
            DataOutputStream dataOutput = new DataOutputStream(clientSocket.getOutputStream());
           
            // Making a new inputstream object for sending data
            DataInputStream dataInput = new DataInputStream(clientSocket.getInputStream());

            // Taking user input for the data to be sent
            int size = 5;
            dataOutput.writeInt(size);
            dataOutput.flush();
            
            // Creating the Adjacency matrix
            int adjMatrix[][] = new int[size][size];

            // Read the matrix values from the user
            int value;
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the elements of the matrix");
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++) {
                    value = input.nextInt();
                    if(value >= 1)
                        value = 1;
                    else
                        value = 0;
                    adjMatrix[i][j] = value;
                }

            // Display the entered matrix            
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < size; i++) {
                s.append((char)(i+ (int)'A') + ": ");
                for (int j : adjMatrix[i]) {
                    s.append((j) + " ");
                }
                s.append("\n");
            }

            // User enters path length
            System.out.println("");
            System.out.println("Enter the length of path, or n:");
            int pathLength = input.nextInt();
            dataOutput.writeInt(pathLength);
            dataOutput.flush();
            
            // User enters first node
            System.out.println("");
            System.out.println("Enter the first node in Letters:");
            // int start = (int)Character.toUpperCase(input.next().charAt(0)) - (int)'A';
            int initialNode = Character.toUpperCase(input.next().charAt(0)) - (int)'A';
            dataOutput.writeInt(initialNode);
            dataOutput.flush();

            // User enters second node
            System.out.println("");
            System.out.println("Enter the second node in Letters:");
            int finalNode = Character.toUpperCase(input.next().charAt(0)) - (int)'A';
            dataOutput.writeInt(finalNode);
            // dataOutput.writeInt(Character.toUpperCase(input.next().charAt(0)) - (int)'A');
            dataOutput.flush();

            // Sending the matrix to the Server for calculation
            System.out.println("");
            System.out.println("This is the matrix that was entered\n");
            System.out.print(s.toString());
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    dataOutput.writeInt(adjMatrix[i][j]);
                    dataOutput.flush();

            
            // Reading the the responses sent by the server
            int response = dataInput.readInt();

            // Alphabet conversion for Response statement display
            char startNode = (char)((int)initialNode + (int)'A');
            char endNode = (char)((int)finalNode + (int)'A');
            
            // Check response from the server
            String statement = "";
            if(response == 1)
                statement = "Yes, there exists a path of length " + pathLength + " from node " + startNode + " to node " + endNode;
            else if(response == 0)
                statement = "No, there is no path of length " + pathLength + " from node " + startNode+ " to node " + endNode;

            // Showing user the output
            System.out.println(statement);            

            // Closing various streams to prevent data or resource leak or overuse
            reader.close();
            dataOutput.close();
            clientSocket.close();
            input.close();
            
            }
        catch(Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }  
}