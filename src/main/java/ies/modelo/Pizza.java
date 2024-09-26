package ies.modelo;

import java.util.List;

public class Pizza  extends Producto {
    private SIZE tamano;
    private List<Ingrediente> listaIngredientes;
    
    public Pizza(int id, String nombre, double precio, SIZE tamano, List<Ingrediente> listaIngredientes) {
        super(id, nombre, precio);
        this.tamano = tamano;
        this.listaIngredientes = listaIngredientes;
    }
    
    public SIZE getTamano() {
        return tamano;
    }
    public void setTamano(SIZE tamano) {
        this.tamano = tamano;
    }
    public List<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }
    public void setListaIngredientes(List<Ingrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    

}
