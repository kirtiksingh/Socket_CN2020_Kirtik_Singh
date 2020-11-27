
class Paint extends JPanel{
    // ovverride paint component method
    // panel ke size se half pe start
    // hardcode coordintates
    // //if adj i j != 0 then
    // draw oval pehle then drawline
    // g.drawline(i element ki xcoordinate = radius, y ka x coordinate plus radius )
    int s = 35;
    JPanel paint;

    Paint() {
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        p.setBackground(new Color(236, 165, 76));
        paint = new JPanel();
        paint.setBackground(new Color(232, 235, 214));
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JTextField tf1 = new JTextField("", 3);
        JTextField tf2 = new JTextField("", 3);
        JTextField tf3 = new JTextField("", 3);
        JButton b4 = new JButton("Find");
        JButton b1 = new JButton("Insert");
        JButton b2 = new JButton("Delete");
        JButton b3 = new JButton("Print");
        JButton printbutton = new JButton("Print tree");

        JLabel l = new JLabel("Binary Search Tree");
        JLabel l1 = new JLabel("");
        JLabel l2 = new JLabel("");
        JLabel l3 = new JLabel("");
        Color c1 = new Color(59, 16, 81);

        l.setForeground(new Color(236, 165, 76));
        l.setBackground(c1);
        // Font myfont = new Font("Monospace", Font.BOLD, 24);
        // Font myfont1 = new Font("Sans Serif", Font.BOLD, 18);
        // l.setFont(myfont);
        p1.setBackground(c1);
        p1.add(l, BorderLayout.NORTH);
        
        l1.setForeground(c1);
        l2.setForeground(c1);
        b1.setForeground(new Color(236, 165, 76));
        b1.setBackground(c1);
        b2.setForeground(new Color(236, 165, 76));
        b2.setBackground(c1);
        b3.setForeground(new Color(236, 165, 76));
        b3.setBackground(c1);
        b4.setForeground(new Color(236, 165, 76));
        b4.setBackground(c1);
        printbutton.setForeground(new Color(236, 165, 76));
        printbutton.setBackground(c1);

        JPanel panel = new JPanel();
        panel.add(p1);
        panel.add(p);
        panel.setLayout(new GridLayout(2, 1));        

        p.add(tf1);
        p.add(b1);
        p.add(tf2);
        p.add(b2);
        p.add(tf3);
        p.add(b4);
        p.add(b3);
        p.add(printbutton);
        p.setLayout(new FlowLayout(FlowLayout.CENTER));
        p2.setBackground(new Color(236, 165, 76));
        p2.add(l1);
        p2.add(l2);
        p2.add(l3);
        p2.setLayout(new FlowLayout(FlowLayout.CENTER));
//        f.add(panel);
        f.add(panel, BorderLayout.NORTH);
        f.add(paint, BorderLayout.CENTER);
        f.add(p2, BorderLayout.SOUTH);
        l3.setForeground(c1);
       

        f.setVisible(true);
        f.setSize(700, 700);
        //f.setLayout(null);
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    void drawTheNode(int x, int y, int data) {
        Graphics g = paint.getGraphics();
        Color c1 = new Color(59, 16, 81);
        g.setColor(c1);
        g.fillRect(x, y, s, s);
        g.setColor(new Color(236, 165, 76));
        g.drawString(Integer.toString(data), x + s / 4 + 4, y + s / 2 + 4);
    }
}