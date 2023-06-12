package GUI;

import DBManager.DBManager;
import Exception.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnyadirVehiculo extends JFrame{

    private JPanel panel1;
    private JTextField matriculaTextField;
    private JTextField marcaTextField;
    private JTextField modeloTextField;
    private JTextField colorTextField;
    private JTextField dniTextField;
    private JButton anyadirButton;
    private JButton cancelarButton;
    private JLabel matriculaLabel;
    private JLabel marcaLabel;
    private JLabel modeloLablel;
    private JLabel colorLabel;
    private JLabel dniLabel;
    private JTextField diasTextField;
    private JTextField precioTextField;
    private JLabel diasLabel;
    private JLabel precioLabel;

    public JPanel getPanel1() {
        return panel1;
    }

    public AnyadirVehiculo(){
        setContentPane(panel1);
        setSize(400,400);
        setLocationRelativeTo(null);
        anyadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula= matriculaTextField.getText();
                String marca= marcaTextField.getText();
                String modelo= modeloTextField.getText();
                String color= colorTextField.getText();
                String dni= dniTextField.getText();
                // Validar si algún campo obligatorio está en blanco
                if (matricula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La matrícula es un campo obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si la matrícula está en blanco
                }
                if(!matricula.matches("\\d{4}[A-Z]{3}")){
                    JOptionPane.showMessageDialog(null,"La matricula tiene que estar fomrada por 4 números y 3 letras. EJ:(1111AAA)");
                }
                // Establecer valores vacíos si los campos opcionales están en blanco
                if (marca.isEmpty()) {
                    marca = "";
                }
                if (modelo.isEmpty()) {
                    modelo = "";
                }
                if (color.isEmpty()) {
                    color = "";
                }
                if (dni.isEmpty()) {
                    dni = "";
                }

                // Validar y obtener el valor numérico de días alquilado
                int dias;
                try {
                    if(diasTextField.getText().isBlank()){
                        dias=0;
                    }else{
                        dias = Integer.parseInt(diasTextField.getText());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El valor de 'Días alquilado' no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir del método si no se pudo convertir a número
                }

                // Obtener el valor numérico de precio por día (opcional)
                double precioDia = 0.0;
                String precioText = precioTextField.getText().trim();
                if (!precioText.isEmpty()) {
                    try {
                        precioDia = Double.parseDouble(precioText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El valor de 'Precio por día' no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Salir del método si no se pudo convertir a número
                    }
                }

                // Insertar el vehículo en la base de datos
                try {
                    DBManager.insertarVehiculo(matricula, modelo, marca, color, dias, precioDia, dni);
                    JOptionPane.showMessageDialog(null, "El vehículo se ha insertado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
