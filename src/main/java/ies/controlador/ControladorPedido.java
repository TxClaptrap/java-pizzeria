package ies.controlador;

import ies.modelo.Pedido;
import ies.modelo.LineaPedido;
import ies.modelo.Pagable;
import ies.modelo.Producto;
import ies.modelo.SIZE;

import java.util.ArrayList;
import java.util.List;

import ies.modelo.Cliente;
import ies.modelo.EstadoPedido;


public class ControladorPedido {

    private Pedido pedidoActual;
    private List<Pedido> listaFinalizados;

    public ControladorPedido(int id, Cliente clienteActual) {
        pedidoActual = new Pedido(id, clienteActual);
        listaFinalizados = new ArrayList<Pedido>();
    }

    //agregarLineaPedido
    public void agregarLineaPedido(Producto producto, int cantidad, SIZE tamaño) throws IllegalAccessException {
        if (pedidoActual.getCliente() == null) {
            throw new IllegalAccessException("No se puede agregar pedido sin estar logeado.");
        }
        pedidoActual.getListaLineaPedidos().add(new LineaPedido(pedidoActual.getListaLineaPedidos().size() + 1, cantidad, pedidoActual, producto));
        
        System.out.println("Producto " + producto.getNombre() + " agregado al pedido. Cantidad: " + cantidad);

        if (pedidoActual.getEstado()!=EstadoPedido.PENDIENTE) {
            pedidoActual.setEstado(EstadoPedido.PENDIENTE);
        }
    }

    //finalizarPedido()
    public void finalizarPedido(Pagable metodo) throws IllegalAccessException {
        if (pedidoActual.getCliente() == null) {
            throw new IllegalAccessException("No se puede agregar pedido sin estar logeado.");
        }
        if (pedidoActual.getEstado() == EstadoPedido.PENDIENTE) {
            for (LineaPedido linea: pedidoActual.getListaLineaPedidos()) {
                pedidoActual.setPrecioTotal(pedidoActual.getPrecioTotal() + (linea.getProducto().getPrecio() * linea.getCantidad()));
            }
            metodo.pagar(pedidoActual.getPrecioTotal());
            pedidoActual.setEstado(EstadoPedido.FINALIZADO);
            System.out.println("Pedido finalizado, se entregará en breve. Ya no se pueden realizar cambios o cancelar el pedido");
            listaFinalizados.add(pedidoActual);
            pedidoActual.setListaLineaPedidos(null);
        } else {
            System.out.println("No se pudo finalizar el pedido.");
        }
    }

    //cancelarPedido()
    public void cancelarPedido() throws IllegalAccessException {
        if (pedidoActual.getCliente() == null) {
            throw new IllegalAccessException("No se puede agregar pedido sin estar logeado.");
        }
        if (pedidoActual.getEstado() != EstadoPedido.FINALIZADO || pedidoActual.getEstado() == EstadoPedido.ENTREGADO) {
            pedidoActual.setEstado(EstadoPedido.CANCELADO);
            System.out.println("El pedido ha sido cancelado.");
        }  else {
            System.out.println("No se puede cancelar el pedido en el estado actual.");
        }
    }

    //entregarPedido()
    public void entregarPedido(int idPedido) {
        for (Pedido pedido : listaFinalizados) {
            if (idPedido == pedido.getId()) {
                pedidoActual.setEstado(EstadoPedido.ENTREGADO);
                System.out.println("El pedido ha sido entregado.");
            } else {
                System.out.println("No se puede entregar un pedido que no ha sido finalizado.");
            }
        }   
    }
}