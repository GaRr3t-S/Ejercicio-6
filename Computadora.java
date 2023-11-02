public class Computadora implements EDispositivo {
    //variables de computadora
    private String marca;
    private boolean encendido;

    //constructor
    public Computadora(String marca, boolean encendido){
        this.marca = marca;
        this.encendido = encendido;
    }

    
    public String getMarca() {
        return marca;
    }

    //
    @Override
    public void encender() {
        encendido = true;
    }

    @Override
    public void apagar() {
        encendido = false;
    }

    @Override
    public boolean Encendido() {
        return encendido;
    }
}
