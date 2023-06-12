import DBManager.DBManager;
import GUI.AnyadirVehiculo;
import GUI.EliminarVehiculo;
import POJO.Vehiculo;
import GUI.AnyadirVehiculo;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aplicación de vehículos de alquiler
 *
 * Tendrá un menú igual que el del examen último, desde el que se cargarán las pantallas.
 * Tendréis que validar con Excepciones en la clase Vehiculo (constructores y métodos alquilar / desalquilar). Estos métodos se llamarán para cuando vayáis a realizar las acciones en las pantallas. Todos deben ser utilizados.
 * Tendrá la siguientes opciones:
 * Añadir vehículo (JFrame)
 * Eliminar Vehículo (JFrame) - Búsqueda previa por matrícula del vehículo y carga de datos, para luego poder proceder a eliminar. Debéis cercionaros primero de que se ha cargado el vehículo antes de permitir eliminar.
 * Modificar vehículo (misma pantalla que añadir, pero cargando sus datos ya en los campos)
 * Alquilar / Desalquilar (esta pantalla a vuestro gusto), pero tendréis que seleccionar un coche primero, antes de poder alquilar o desalquilar (y que esté por alquilar o ya alquilado)
 * Cargar datos vehículos en tabla y permitir cargar los alquilados / no alquilados separadamente, o todos
 *  NOTA: Debéis en cada operación hacer la acción con la BD y luego con vuestra lista estática que tendréis en DAMRenting (Menú). Por ejemplo, al añadir un vehículo, lo añadiréis a la base de datos, y si se ha podido, a vuestra lista también.
 *
 * NOTA2: Sólo habrá un método main, que será el que esté en la clase DAMRenting.java.
 *
 *  NOTA3: Seguiréis teniendo sólo 3 clases: Vehiculo, DAMRenting y DBManager, más las necesarias para utilizar como pantallas. Además, en el switch del menú, llamaréis en cada opción a un método que contendrá dentro la funcionalidad. Este método del main debe ser lo más reducido posible.
 *
 *  SEGUIRÉIS TENIENDO SÓLO 3 CLASES: Vehiculo, DAMRenting, DBManager más las necesarias para utilizar como pantallas.
 */
public class DAMRenting {
public static ArrayList<Vehiculo> vehiculos;
public static Scanner sc = new Scanner(System.in);
public static void anyadirVehiculos(){
    JFrame frame = new JFrame("Añadir Vehiculo");
    frame.setContentPane(new AnyadirVehiculo().getPanel1());
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setVisible(true);

}
    public static void setVehiculosArray() {
        vehiculos=DBManager.getVehiculosData();
    }
    private static void menu() {
        int opt;
        do{
            System.out.print("\nDAMRenting MENU" +
                    "\n----------------------------" +
                    "\n1.Añadir vehiculo" +
                    "\n2.Eliminar vehiculo" +
                    "\n3.Modificar vehiculo" +
                    "\n4.Alquilar/Desalquilar vehiculo" +
                    "\n----------------------------" +
                    "\nElige una opcion: ");
            while (!sc.hasNextInt()) {
                System.out.println("Eso no es un número!");
                System.out.print("\nIntroduce una opcion: ");
                sc.next();
            }
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    anyadirVehiculos();
                    break;
                case 2:
                    eliminarVehiculo();
                    break;
                case 3:
                    modificarVehiculo();
                    break;
                case 4:
                    alquilarDesalquilar();
                    break;
                default:
                    System.out.println("Selecciona un numero entre 0 y 4");
                    break;
                case 0:
                    System.out.println("Gracias por su visita, adios!");
                    break;
            }
        }while(opt!=0);
    }

    private static void alquilarDesalquilar() {
    }

    private static void modificarVehiculo() {
    }

    private static void eliminarVehiculo() {
        JFrame frame = new JFrame("EliminarVehiculo");
        frame.setContentPane(new EliminarVehiculo().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,400);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        DBManager.loadDriver();
        DBManager.connect();
        setVehiculosArray();
        menu();
       for (int i=0; i < vehiculos.size(); i++){
           System.out.println(vehiculos.get(i).toString());
       }
    }


}