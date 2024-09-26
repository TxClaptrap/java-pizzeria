package ies.modelo;

public class PagarTarjeta implements Pagable {

    @Override
    public void pagar(double cantidad) {
        System.out.println("Pago en tarjeta: " + cantidad);
    }
    
}
