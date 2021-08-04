package View.GUI;

import javax.swing.*;

public class Home extends JFrame{
    private JButton button1;
    private JButton editarButton;
    private JButton button3;
    private JPanel homePanel;
    private JTable table1;

    public Home(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(homePanel);
        this.pack();
    }
}
