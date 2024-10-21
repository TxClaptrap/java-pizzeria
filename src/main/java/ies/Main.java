package ies;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import ies.controlador.ControladorCliente;
import ies.controlador.ControladorPedido;
import ies.controlador.ControladorProducto;
import ies.controlador.GestorFicheros;
import ies.modelo.Cliente;
import ies.modelo.Ingrediente;
import ies.modelo.PagarTarjeta;
import ies.modelo.Pasta;
import ies.modelo.Pizza;
import ies.modelo.SIZE;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        /*Ingrediente echamas = new Ingrediente(1, "Queso", List.of("lactosa, adictivos"));
        Ingrediente baseTrigo = new Ingrediente(2, "Base con trigo", List.of("gluten, sulfitos"));
        Ingrediente baseSosa = new Ingrediente(3, "Base sin trigo", List.of());
        Ingrediente tomatico = new Ingrediente(4, "Tomate", List.of("fructosa"));
        Ingrediente chicha = new Ingrediente(5, "Jamón York", List.of());
        Ingrediente gazpacho = new Ingrediente(6, "Piña", List.of("fructosa"));
        Ingrediente chicha2 = new Ingrediente(7, "Carne picada", List.of());
        
        Pizza aberrante = new Pizza(1, "Hawaiana", 8, SIZE.MEDIANO , List.of(baseTrigo, tomatico, echamas, chicha, gazpacho));
        Pasta bolognese = new Pasta(2, "Boloñesa celiaca", 10, List.of(echamas, baseSosa, tomatico, chicha2));

        PagarTarjeta pagoTarjeta = new PagarTarjeta();*/

        //Creando controladores necesarios
        ControladorCliente controladorCliente = new ControladorCliente(null);
        ControladorProducto controladorProducto = new ControladorProducto(null);
        
        /*controladorCliente.registrarCliente("00000000Z", "Pepe", "C/aaaaaa", "000000000", "a@g.com", "lala");
        controladorCliente.registrarCliente("00000001X", "Pepa", "C/aaaaaa", "000000001", "b@g.com", "lale");

        for (Cliente cliente : controladorCliente.getListaClientes()) {
            System.out.println(cliente);
        }
        controladorCliente.loginCliente("a@g.com", "lala");
        controladorCliente.loginCliente("b@g.com", "lala");

        ControladorPedido controladorPedido = new ControladorPedido(1, controladorCliente.getClienteActual());
        
        controladorPedido.agregarLineaPedido(bolognese, 3, SIZE.GRANDE);
        controladorPedido.agregarLineaPedido(aberrante, 1, SIZE.MEDIANO);

        controladorPedido.finalizarPedido(pagoTarjeta);
        controladorPedido.cancelarPedido();

        controladorPedido.entregarPedido(1);*/

        //Probando que lee el archivo .txt
        List<Cliente> administradores;
        try {
            administradores = controladorCliente.leerClientes("admin.txt");
            administradores.forEach(System.out::println);
        } catch (IOException e) {
            administradores = new ArrayList<>();
            e.printStackTrace();
        }

        //Probando que exporta clientes a XML
        try {
            controladorCliente.exportarClientesXML(administradores);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Probando que importa clientes desde XML
        try {
            controladorCliente.importarClientesXML("clientes.xml").forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Creando lista de ingredientes
        List<Ingrediente> ingredientes = new ArrayList<>() {{
            add(new Ingrediente(1, "Queso", List.of("lactosa", "adictivos")));
            add(new Ingrediente(2, "Base con trigo", List.of("gluten", "sulfitos")));
            add(new Ingrediente(3, "Base sin trigo", List.of()));
            add(new Ingrediente(4, "Tomate", List.of("fructosa")));
            add(new Ingrediente(5, "Jamón York", List.of()));
        }};

        //Probando que exporta ingredientes a CSV
        try {
            controladorProducto.exportarIngredientesCSV(ingredientes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Probando que importa ingredientes desde CSV
        try {
            controladorProducto.importarIngredientesCSV("ingredientes.csv").forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
