package DBManager;
import POJO.Vehiculo;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    // Conexión a la base de datos
    private static Connection conn = null;

    // Configuración de la conexión a la base de datos
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "damrenting";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";


    // Configuración de la tabla Vehiculos
    private static final String DB_VEHICULO = "vehiculos";
    private static final String DB_VEHICULO_SELECT = "SELECT * FROM " + DB_VEHICULO;
    private static final String DB_VEHICULO_MATRICULA = "Matricula";
    private static final String DB_VEHICULO_MARCA = "Marca";
    private static final String DB_VEHICULO_MODELO = "Modelo";
    private static final String DB_VEHICULO_COLOR = "Color";
    private static final String DB_VEHICULO_PRECIO = "PrecioDia";
    private static final String DB_VEHICULO_DIAS_ALQ = "DiasAlquilado";
    private static final String DB_VEHICULO_DNI = "DniCliente";

    //////////////////////////////////////////////////
    // MÉTODOS DE CONEXIÓN A LA BASE DE DATOS
    //////////////////////////////////////////////////
    ;

    /**
     * Intenta cargar el JDBC driver.
     * @return true si pudo cargar el driver, false en caso contrario
     */
    public static boolean loadDriver() {
        try {
            System.out.print("Cargando Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("OK!");
            return true;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Intenta conectar con la base de datos.
     *
     * @return true si pudo conectarse, false en caso contrario
     */
    public static boolean connect() {
        try {
            System.out.print("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("OK!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Comprueba la conexión y muestra su estado por pantalla
     *
     * @return true si la conexión existe y es válida, false en caso contrario
     */
    public static boolean isConnected() {
        // Comprobamos estado de la conexión
        try {
            if (conn != null && conn.isValid(0)) {
                System.out.println(DB_MSQ_CONN_OK);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(DB_MSQ_CONN_NO);
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Cierra la conexión con la base de datos
     */
    public static void close() {
        try {
            System.out.print("Cerrando la conexión...");
            conn.close();
            System.out.println("OK!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /////////////////////////////////////////////////////////////////
    ///             MÉTODOS VEHICULO                            ////
    ////////////////////////////////////////////////////////////////

    public static ArrayList<Vehiculo> getVehiculosData(){
        try{
            ArrayList<Vehiculo> vehiculos = new ArrayList<>();
            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(DB_VEHICULO_SELECT);
            while(rs.next()){
                vehiculos.add(new Vehiculo(rs.getString(DB_VEHICULO_MATRICULA), rs.getString(DB_VEHICULO_MARCA), rs.getString(DB_VEHICULO_MODELO), rs.getString(DB_VEHICULO_COLOR), rs.getFloat(DB_VEHICULO_PRECIO), rs.getInt(DB_VEHICULO_DIAS_ALQ), rs.getString(DB_VEHICULO_DNI)));
            }
            return vehiculos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insertarVehiculo(String matricula, String modelo, String marca, String color, int diasAlquilado, double precioDia, String dniCliente) {
        try {
            // Preparar la sentencia SQL para insertar un vehículo
            String sql = "INSERT INTO vehiculos (Matricula, Modelo, Marca, Color, DiasAlquilado, PrecioDia, DniCliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Establecer los valores de los parámetros
            statement.setString(1, matricula);
            statement.setString(2, modelo);
            statement.setString(3, marca);
            statement.setString(4, color);
            statement.setInt(5, diasAlquilado);
            statement.setDouble(6, precioDia);
            statement.setString(7, dniCliente);

            // Ejecutar la sentencia SQL
            statement.executeUpdate();

            // Cerrar la conexión y el statement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Vehiculo buscarVehiculo(String matricula){
        ArrayList<Vehiculo> vehiculos=getVehiculosData();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula() == matricula) {
                return vehiculo;
            }
        }
        return null;
    }
    public static void eliminarVehiculo(String matricula){
        // Preparar la sentencia SQL para insertar un vehículo
        try{
            String sql = "DELETE ";
            PreparedStatement statement = conn.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

