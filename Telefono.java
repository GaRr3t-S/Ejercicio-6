interface EDispositivo {
    public void encender();
    public void apagar();
    public boolean Encendido();
}

public class Telefono implements EDispositivo {
    private String modelo;
    private boolean encendido;

    public String getModelo() {
        return  modelo;
    }

    public Telefono(String modelo, boolean encendido){
        this.modelo = modelo;
        this.encendido = encendido; 
    }

    public Telefono(String modelo) {
        this.modelo = modelo;
        this.encendido = false;
    }

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