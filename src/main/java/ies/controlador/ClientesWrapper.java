package ies.controlador;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ies.modelo.Cliente;

import java.util.List;
import java.util.ArrayList;

@XmlRootElement(name = "clientes")
@XmlAccessorType(XmlAccessType.FIELD)

public class ClientesWrapper {
    
    @XmlElement(name = "cliente")
    private List<Cliente> listaClientes = new ArrayList<>();

    public ClientesWrapper() {
    }

    public ClientesWrapper(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    
}
