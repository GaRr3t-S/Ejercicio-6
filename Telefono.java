// interfaz con los metodos de los dispositivos, la puse en este .java porque me estaba dando problemas y aqui si funciono por alguna razon asi que aqui la deje :P
interface EDispositivo {
    public void encender();
    public void apagar();
    public boolean Encendido();
}

public class Telefono implements EDispositivo {
    //variables telefono
    private String modelo;
    private boolean encendido;

    //getter
    public String getModelo() {
        return  modelo;
    }

    //contructor telefono
    public Telefono(String modelo, boolean encendido){
        this.modelo = modelo;
        this.encendido = encendido; 
    }

    //funciones de la interfaz
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