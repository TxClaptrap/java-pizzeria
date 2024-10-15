package ies.utilidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import ies.modelo.Cliente;

public class LectorCSV {
    public static List<Cliente> leer(String ruta) {
        try (Stream<String> lineas = Files.lines(Path.of(ruta))) {
            return lineas.map(linea -> {
                List<String> cosas = List.of(linea.split("[,;|]"));
                cosas = cosas.stream().map(String::trim).toList();

                return new Cliente(cosas.get(1), cosas.get(2), cosas.get(3), cosas.get(4),
                        cosas.get(5), cosas.get(6), true);
            }).toList();
        } catch (IOException e) {
            System.err.println("ERROR");
            return new ArrayList<>();
        }
    }
}
