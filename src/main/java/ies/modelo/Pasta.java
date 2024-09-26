package ies.modelo;

import java.util.List;

public class Pasta  extends Producto {
    private List<Ingrediente> listaIngredientes;

    public Pasta(int id, String nombre, double precio, List<Ingrediente> listaIngredientes) {
        super(id, nombre, precio);
        this.listaIngredientes = listaIngredientes;
    }

    public List<Ingrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(List<Ingrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    
}
