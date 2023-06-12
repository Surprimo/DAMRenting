package GUI;

import DBManager.DBManager;
import Exception.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarVehiculo {
    public JPanel panel1;
    private JTextField matriculaTextField;
    private JButton buscarButton;
    private JLabel matriculaLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton eliminarButton;
    private JButton cancelarButton;

    public EliminarVehiculo() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarMatricula();
                } catch (ErrorMatricula ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void buscarMatricula() throws ErrorMatricula {
        String matricula = matriculaTextField.getText();
        if (matricula.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La matrícula es un campo obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si la matrícula está en blanco
        }
        if(!matricula.matches("\\d{4}[A-Z]{3}")){
            JOptionPane.showMessageDialog(null,"La matricula tiene que estar fomrada por 4 números y 3 letras. EJ:(1111AAA)");
            return; // Salir del método si la matrícula no coincide con el regex
        }
        DBManager.buscarVehiculo(matricula);
    }
}
