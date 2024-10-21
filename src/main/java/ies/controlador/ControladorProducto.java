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

    //Métodos de encapsulado, para no hacerlo todo desde GestorFicheros
    public boolean exportarIngredientesCSV(List<Ingrediente> ingredientes)
            throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, FileNotFoundException {
        return gestorFicheros.exportarCSV(ingredientes);
    }

    public List<Ingrediente> importarIngredientesCSV() throws FileNotFoundException, IOException {
        return gestorFicheros.importarCSV();
    }

}
