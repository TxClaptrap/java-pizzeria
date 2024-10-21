package ies.controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import ies.modelo.Ingrediente;

public class ControladorProducto {
    GestorFicheros gestorFicheros;

    public ControladorProducto() {
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
