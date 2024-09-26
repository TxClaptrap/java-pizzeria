package ies.modelo;

public abstract class Producto {
    private int id;
    private String nombre;
    private double precioTotal;
    
    public Producto(int id, String nombre, double precioTotal) {
        this.id = id;
        this.nombre = nombre;
        this.precioTotal = precioTotal;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precioTotal;
    }
    public void setPrecio(double precio) {
        this.precioTotal = precio;
    } 

    
}
