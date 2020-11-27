package Socket_CN2020_Kirtik_Singh;

import java.awt.Color;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
import java.awt.geom.*;


public class Server{

    // Declaration of a universal adjacency matrix
    static int[][] globalAdjMatrix = new int[5][5];

    // Function that finds if a path exists between the given nodes in the matrix or not.
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

            // System.out.println("Received length DEBUG Call");

            // Reading the values of the Path Length as well as the nodes to be followed.
            int pathLength = dataInput.readInt();
            int node1 = dataInput.readInt();
            int node2 = dataInput.readInt();

            // System.out.println("Received other values");
            // System.out.println("Otther values of length node 1 and node 2");
            // System.out.println(pathLength);
            // System.out.println(node1);
            // System.out.println(node2);

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

            // START OF PAINT IMAGE
            new GraphFrame(globalAdjMatrix, dataOutput);
            //  END OF PAINT IMAGE

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

class GraphFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GraphFrame(int[][] graph, DataOutputStream outputStream) {

        // Setting name for the frame
        super("Graph Representation");

        // Adding a GraphGUI panel
        GraphGUI p = new GraphGUI(graph);

        // Setting frame size
        this.setSize(1067, 800);

        // Making frame to not be resizable
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding the panel to the frame
        this.setContentPane(p);

        this.setVisible(false);

        // Calling function to send image to client
        sendPanelImage(p, outputStream);
    }
    // Function to send image to client
    private void sendPanelImage(Component panel, DataOutputStream outputStream) {
        // Instantiating a buffer image
        BufferedImage image = new BufferedImage(1067, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        panel.paint(g2);
        setVisible(true);
        // try {
        //     // Sending the buffer image to the client
        //     ImageIO.write(image, "png", outputStream);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}

// Custom class Node to represent the 5 nodes of the graph
class Node {
    int xCenter; // x-coordinate of center
    int yCenter; // y-coorinate of center
    String nodeName; // Name of the node --> A, B, C, D, E

    public Node(int x, int y, String name) {
        this.xCenter = x;
        this.yCenter = y;
        this.nodeName = name;
    }

    // Returning xCenter
    public int getXCenter() {
        return this.xCenter;
    }

    // Returning yCenter
    public int getYCenter() {
        return this.yCenter;
    }

    // Returning nodeName
    public String getNodeName() {
        return this.nodeName;
    }
}

// Class extending JPanel to draw the Graph GUI
class GraphGUI extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // Instatiating colors to include in the GUI
    static Color dark = new Color(41, 50, 65);
    static Color light = new Color(224, 251, 252);
    static Color accent1 = new Color(238, 108, 77);
    static Color accent2 = new Color(152, 193, 217);

    int[][] matrix = new int[5][5];

    // Constructor to set the matrix
    public GraphGUI(int[][] matrix) {
        this.matrix = matrix;
    }

    // Predefined paint mathod which is used to draw the GUI
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        super.repaint();

        // Setting the background color
        this.setBackground(dark);

        Graphics2D g2d = (Graphics2D) g;

        // Adding background rectangle for image
        g2d.setColor(dark);
        g2d.fillRect(0, 0, 1067, 800);

        // Setting the general font
        g2d.setFont(new Font("Sans Serif", Font.BOLD, 25));

        // Making an array of the Node class with custom centers
        Node[] nodes = { 
            new Node(500, 100, "A"), 
            new Node(200, 350, "B"), 
            new Node(800, 350, "C"),
            new Node(300, 700, "D"), 
            new Node(700, 700, "E") 
        };

        // Array for links starting from each node with the x-y coordinated of the start
        // and end of the link
        // The empty elements are the ones never used but just to place the rest of the
        // elements in appropriate positions
        // FORMAT - x1, x2, y1, y2
        int[][][] linePoints = {
                { 
                    {}, 
                    { 504, 244, 142, 359 }, 
                    { 546, 806, 142, 359 }, 
                    { 516, 333, 151, 700 }, 
                    { 534, 716, 151, 700 } 
                },
                { 
                    {}, 
                    {}, 
                    { 250, 800, 375, 375 }, 
                    { 232, 317, 400, 700 }, 
                    { 247, 703, 391, 710 }, 
                },
                { 
                    {}, 
                    {}, 
                    {}, 
                    { 803, 347, 390, 709 }, 
                    { 817, 732, 400, 700 }, 
                },
                { 
                    {}, 
                    {}, 
                    {}, 
                    {}, 
                    { 350, 700, 725, 725 }, 
                } 
        };

        // Running a loop to draw all the 5 nodes
        for (int i = 0; i < 5; i++) {

            g2d.setColor(accent1);

            // Drawing a solid circle node with the given points and diameter 50px
            g2d.fillOval(nodes[i].getXCenter(), nodes[i].getYCenter(), 50, 50);

            g2d.setColor(light);

            // Giving a border to the nodes
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(nodes[i].getXCenter(), nodes[i].getYCenter(), 50, 50);

            // Writing the node name
            g2d.drawString(nodes[i].getNodeName(), nodes[i].getXCenter() + 17, nodes[i].getYCenter() + 35);

        }

        g2d.setColor(accent2);
        g2d.setStroke(new BasicStroke(2));

        // Running a nested loop to draw the links between the nodes
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                g2d.setColor(accent2);

                // A link is created if there is a connection between 2 nodes starting from
                // either of them
                if (matrix[i][j] == 1 || matrix[j][i] == 1)
                    g2d.drawLine(linePoints[i][j][0], linePoints[i][j][2], linePoints[i][j][1], linePoints[i][j][3]);

                g2d.setColor(light);

                // Adding arrow head to the link based on the condition where the link is
                // directed from
                if (matrix[i][j] == 1)
                    drawArrowHead((Graphics2D) g2d, linePoints[i][j][0], linePoints[i][j][2], linePoints[i][j][1],
                            linePoints[i][j][3]);

                // The link can be bi directional also
                if (matrix[j][i] == 1)
                    drawArrowHead((Graphics2D) g2d, linePoints[i][j][1], linePoints[i][j][3], linePoints[i][j][0],
                            linePoints[i][j][2]);
            }
        }
    }

    // Function to create arrow heads on the link
    private void drawArrowHead(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        double arrowAngle = Math.toRadians(30); // Setting an angle for the arrow
        double slopeAngle = Math.atan2(y2 - y1, x2 - x1); // Calculatin the angle of the slope of the line
        double arrowLength = 15; // Setting a length for the Arrow Head

        double angle1 = slopeAngle + arrowAngle; // Calculating angle for left Arrow Head
        double angle2 = slopeAngle - arrowAngle; // Calculating angle for right Arrow Head

        // Defining X and Y coordinate array for the arrow head
        // The first point has the coordinates of the link tail
        // The second and third points are calculated using the above angles
        double[] arrowPointsX = { x2, x2 - arrowLength * Math.cos(angle1), x2 - arrowLength * Math.cos(angle2) };
        double[] arrowPointsY = { y2, y2 - arrowLength * Math.sin(angle1), y2 - arrowLength * Math.sin(angle2) };

        // Declaring a path for the arrow head
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
        path.moveTo(arrowPointsX[0], arrowPointsY[0]);
        for (int i = 1; i < arrowPointsX.length; ++i) {
            path.lineTo(arrowPointsX[i], arrowPointsY[i]);
        }

        // Closing the path within the 3 points
        path.closePath();

        // Actually drawing and filling the path for Arrow Head
        g2d.fill(path);
        g2d.draw(path);

    }

}

