package ies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ies.controlador.ControladorCliente;
import ies.controlador.ControladorProducto;
import ies.controlador.Varios;
import ies.modelo.Cliente;

public class Pruebas {
    public static void main(String[] args) {
        ControladorCliente controladorCliente = new ControladorCliente();
        ControladorProducto controladorProducto = new ControladorProducto();
        Varios gestorFicheros = new Varios();

        //Importar

        List<Cliente> administradores;
        try {
            administradores = controladorCliente.leerClientes();
            administradores.forEach(System.out::println);
        } catch (IOException e) {
            administradores = new ArrayList<>();
            e.printStackTrace();
        }

        //Exportar
        try {
            gestorFicheros.exportarAdministradoresTXT(administradores);
        } catch (IOException e) {
            System.out.println("No se ha exportado.");
            e.printStackTrace();
        }


    }
}
