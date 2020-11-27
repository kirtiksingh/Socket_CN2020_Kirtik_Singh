package Socket_CN2020_Kirtik_Singh;

/**
 * Name: Kirtik Singh
 * Roll Number: 1810110109
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
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
            // BufferedReader dataInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Make a new inputstream object
            DataInputStream dataInput = new DataInputStream(clientSocket.getInputStream());

            // Taking user input for the data to be sent
            int size = 5;
            dataOutput.writeInt(size);
            dataOutput.flush();
            // System.out.println("Enter the matrix to be sent");           

            // for(int i = 0; i < size; i++){
            //     String matrixLine = reader.readLine();
            //     String[] numbers = matrixLine.split(" ");
            //     for(String num: numbers){
            //         dataOutput.writeInt(Integer.parseInt(num));
            //     }
            // }

            int adjMatrix[][] = new int[size][size];

            // Read the matrix values
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

            System.out.println("");
            System.out.println("Enter the length of path, or n:");
            int pathLength = input.nextInt();
            dataOutput.writeInt(pathLength);
            dataOutput.flush();

            System.out.println("");
            System.out.println("Enter the first node in Letters:");
            // int start = (int)Character.toUpperCase(input.next().charAt(0)) - (int)'A';
            int initialNode = Character.toUpperCase(input.next().charAt(0)) - (int)'A';
            dataOutput.writeInt(initialNode);
            dataOutput.flush();

            System.out.println("");
            System.out.println("Enter the second node in Letters:");
            int finalNode = Character.toUpperCase(input.next().charAt(0)) - (int)'A';
            dataOutput.writeInt(finalNode);
            // dataOutput.writeInt(Character.toUpperCase(input.next().charAt(0)) - (int)'A');
            dataOutput.flush();

            // // console log the value (YES/NO)
            // System.out.println(bufferedReaderReadFromServer.readLine());

            // Sending the matrix to the Server
            System.out.println("");
            System.out.println("This is the matrix that was entered\n");
            System.out.print(s.toString());
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    dataOutput.writeInt(adjMatrix[i][j]);
                    dataOutput.flush();

            
            // Read the response input from the server
            int response = dataInput.readInt();

            // Convert start and end node to alphabets
            char startNode = (char)((int)initialNode + (int)'A');
            char endNode = (char)((int)finalNode + (int)'A');
            String statement = "";

            // Check response from the server
            if(response == 1)
                statement = "Yes, there exists a path of length " + pathLength + " from node " + startNode + " to node " + endNode;
            else if(response == 0)
                statement = "No, there is no path of length " + pathLength + " from node " + startNode+ " to node " + endNode;

            // Print the statement
            System.out.println(statement);            

            reader.close();
            dataOutput.close();
            clientSocket.close();
            
            }
        catch(Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }  
}