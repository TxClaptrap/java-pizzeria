package ies.modelo;
import java.time.LocalDate;
import java.util.List;

public class Pedido {
    
    private int id;
    private LocalDate fecha = LocalDate.now();
    private List<LineaPedido> listaLineaPedidos; 
    private double precioTotal = 0;
    private Pagable metodoPago;
    private EstadoPedido estado;

    private Cliente cliente;

    public Pedido(int id, List<LineaPedido> listaLineaPedidos, Cliente cliente, EstadoPedido estado) {
        this.id = id;
        this.listaLineaPedidos = listaLineaPedidos;
        this.cliente = cliente;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }


    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }


    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public List<LineaPedido> getListaLineaPedidos() {
        return listaLineaPedidos;
    }

    public void setListaLineaPedidos(List<LineaPedido> listaLineaPedidos) {
        this.listaLineaPedidos = listaLineaPedidos;
    }

    public Pagable getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(Pagable metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    

    

}


