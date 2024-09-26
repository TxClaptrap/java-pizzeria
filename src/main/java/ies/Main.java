package ies;

import java.util.ArrayList;
import java.util.List;

import ies.controlador.ControladorCliente;
import ies.controlador.ControladorPedido;
import ies.modelo.Cliente;
import ies.modelo.Ingrediente;
import ies.modelo.LineaPedido;
import ies.modelo.PagarTarjeta;
import ies.modelo.Pasta;
import ies.modelo.Pedido;
import ies.modelo.Pizza;
import ies.modelo.SIZE;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        Ingrediente echamas = new Ingrediente(1, "Queso", List.of("Lactosa"));
        Ingrediente baseTrigo = new Ingrediente(2, "Base con trigo", List.of("gluten"));
        Ingrediente baseSosa = new Ingrediente(2, "Base sin trigo", List.of());
        Ingrediente tomatico = new Ingrediente(3, "Tomate", List.of("fructosa"));
        Ingrediente chicha = new Ingrediente(4, "Jamón York", List.of());
        Ingrediente gazpacho = new Ingrediente(5, "Piña", List.of("fructosa"));
        Ingrediente chicha2 = new Ingrediente(4, "Carne picada", List.of());

        Pizza aberrante = new Pizza(1, "Hawaiana", 8, SIZE.MEDIANO , List.of(baseTrigo, tomatico, echamas, chicha, gazpacho));
        Pasta bolognese = new Pasta(2, "Boloñesa celiaca", 10, List.of(echamas, baseSosa, tomatico, chicha2));

        PagarTarjeta pagoTarjeta = new PagarTarjeta();

        ControladorCliente controladorCliente = new ControladorCliente(null);
        ControladorPedido controladorPedido = new ControladorPedido();
        controladorCliente.registrarCliente("00000000Z", "Pepe", "C/aaaaaa", "000000000", "a@g.com", "lala");
        controladorCliente.registrarCliente("00000001X", "Pepa", "C/aaaaaa", "000000001", "b@g.com", "lale");

        for (Cliente cliente : controladorCliente.getListaClientes()) {
            System.out.println(cliente);
        }
        controladorCliente.loginCliente("a@g.com", "lala");
        controladorCliente.loginCliente("b@g.com", "lala");

        Pedido pedido1 = new Pedido(1, new ArrayList<LineaPedido>(), controladorCliente.getClienteActual(), null);
        
        controladorPedido.agregarLineaPedido(pedido1, bolognese, 3, null);
        controladorPedido.agregarLineaPedido(pedido1, aberrante, 1, SIZE.MEDIANO);

        controladorPedido.finalizarPedido(pagoTarjeta);
        controladorPedido.cancelarPedido();

        controladorPedido.entregarPedido(1);


        
    }
}
