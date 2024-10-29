package ies.controlador;

import ies.modelo.Cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;



public class ControladorCliente {
    //clienteActual
    private Cliente clienteActual;
    private List<Cliente> listaClientes;
    GestorFicheros gestorFicheros;
    
    public ControladorCliente() {
        gestorFicheros = new GestorFicheros();
    }

    public ControladorCliente(Cliente clienteActual) {
        this.clienteActual = clienteActual;
        this.listaClientes = new ArrayList<Cliente>();
        gestorFicheros = new GestorFicheros();
    }

    public Cliente getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(Cliente clienteActual) {
        this.clienteActual = clienteActual;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    //registrarCliente()
    public void registrarCliente(String dni, String nombre, String direccion, String telefono, String email, String password) {
        if (listaClientes != null) {
            boolean emailDuplicado = false;
    
            for (Cliente cliente : listaClientes) {
                if (cliente.getEmail() != null && cliente.getEmail().equals(email)) {
                    emailDuplicado = true;
                    break; 
                }
            }
    
            if (!emailDuplicado) {
                listaClientes.add(new Cliente(dni, nombre, direccion, telefono, email, password));
                System.out.println("Cliente registrado con éxito.");
            } else {
                System.out.println("Error: El email ya está registrado.");
            }
        } else {
            System.out.println("No se pudo acceder a la lista de clientes.");
        }
        
    }

    //loginCliente(mail, pass)
    public boolean loginCliente(String email, String password) {
        if (listaClientes != null) {
            for (Cliente cliente : listaClientes) {
                if (cliente.getEmail() != null && cliente.getEmail().equals(email) && cliente.getPassword().equals(password)) {
                    clienteActual = cliente;
                    System.out.println("Login correcto. Bienvenido " + cliente.getNombre());
                    return true;
                }
            }
            System.out.println("Error: Email o contraseña incorrectos.");
        } else {
            System.out.println("No se pudo acceder a la lista de clientes.");
        }
        return false;
    }

    //Métodos de encapsulado, para no hacerlo todo desde GestorFicheros
    public List<Cliente> leerClientes() throws IOException {
        return gestorFicheros.leerAdministradores();
    } 

    public boolean exportarClientesXML(List<Cliente> clientes) throws JAXBException {

        return gestorFicheros.exportarXML(clientes);
    }

    public List<Cliente> importarClientesXML() throws JAXBException {
        return gestorFicheros.importarXML();
    }
}
