package ies.controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import ies.modelo.Ingrediente;
import ies.modelo.Producto;

public class ControladorProducto {
    Producto productoActual;
    GestorFicheros gestorFicheros;

    public ControladorProducto(Producto productoActual) {
        this.productoActual = productoActual;
        gestorFicheros = new GestorFicheros();
    }

    public boolean exportarIngredientesCSV(List<Ingrediente> ingredientes)
            throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, FileNotFoundException {
        return gestorFicheros.exportarCSV(ingredientes);
    }

    public List<Ingrediente> importarIngredientesCSV(String ruta) throws FileNotFoundException, IOException {
        return gestorFicheros.importarCSV(ruta);
    }

}
