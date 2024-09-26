package ies.modelo;

public class Bebida extends Producto{
    private SIZE tamano;

    public Bebida(int id, String nombre, double precio, SIZE tamano) {
        super(id, nombre, precio);
        this.tamano = tamano;
    }

    public SIZE getTamano() {
        return tamano;
    }

    public void setTamano(SIZE tamano) {
        this.tamano = tamano;
    }
    
}
