package ies.modelo;

public class PagarEfectivo implements Pagable {

    @Override
    public void pagar(double cantidad) {
        System.out.println("Pago en efectivo: " + cantidad);
    }
    
}
