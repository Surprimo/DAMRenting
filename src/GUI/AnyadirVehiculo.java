package GUI;

import javax.swing.*;
import java.awt.*;

public class AnyadirVehiculo extends JFrame{

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton añadirButton;
    private JButton cancelarButton;

    public JPanel getPanel1() {
        return panel1;
    }

    public AnyadirVehiculo(){
        setContentPane(panel1);
        setSize(400,400);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AnyadirVehiculo");
        frame.setContentPane(new AnyadirVehiculo().getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
