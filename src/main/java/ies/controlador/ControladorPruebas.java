package ies.controlador;

import java.io.IOException;
import java.util.List;

import ies.modelo.Cliente;

public class ControladorPruebas {
    private List<Cliente> listaClientes;
    Varios gestorFicheros;

        public boolean exportarAdministradoresTXT(List<Cliente> administradores) throws IOException {
        return gestorFicheros.exportarAdministradoresTXT(administradores);
    }
}
