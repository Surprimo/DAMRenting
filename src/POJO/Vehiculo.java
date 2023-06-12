package POJO;
import Exception.AlquilerError;
public class Vehiculo {
    private final String matricula;
    private final String marca;
    private final String modelo;

    public String getMatricula() {
        return matricula;
    }

    private String color;
    private int diasAlquilado;
    private float precioDia;
    private String dni;

    public Vehiculo(String matricula, String marca, String modelo, String color, float precioDia) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precioDia = precioDia;
    }

    public Vehiculo(String matricula, String marca, String modelo, String color, float precioDia, int diasAlquilado,  String dni) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.diasAlquilado = diasAlquilado;
        this.precioDia = precioDia;
        this.dni = dni;
    }

    public void setDiasAlquilado(int diasAlquilado) {
        this.diasAlquilado = diasAlquilado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    public void alquilar(int diasAlquilado, String dni){
        try{
            if (!isRent()){
                setDni(dni);
                setDiasAlquilado(diasAlquilado);
            }else{
                throw new AlquilerError("El vehicuo ya está alquilado");
            }
        } catch (AlquilerError e) {
            throw new RuntimeException(e);
        }
    }
    public void desalquilar()  {
        try{
            if (isRent()){
                setDni(null);
                setDiasAlquilado(Integer.parseInt(null));
            }else{
                throw new AlquilerError("El vehicuo no está alquilado");
            }
        } catch (AlquilerError e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", diasAlquilado=" + diasAlquilado +
                ", precioDia=" + precioDia +
                ", dni='" + dni + '\'' +
                '}';
    }

    public boolean isRent(){
        if (getDni()==null){
            return false;
        }else{
            return true;
        }
    }
}
