package co.uniquindio.edu.co.controller;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public final class ShopMasterFacade {
    private final Supplier<ProcesadorPago> procesadorFactory;

    public ShopMasterFacade(Supplier<ProcesadorPago> procesadorFactory) {
        this.procesadorFactory = Objects.requireNonNull(procesadorFactory, "procesadorFactory");
    }

    public boolean ejecutarCompraRapida(String usuario, ItemCatalogo item, List<TipoExtra> extras) {
        System.out.println("\n=== Compra rápida para " + usuario + " ===");
        Pedido pedido = new PedidoBase(item);
        for (TipoExtra extra : extras) {
            pedido = switch (extra) {
                case GARANTIA_EXTENDIDA -> new GarantiaExtendida(pedido);
                case EMPAQUE_REGALO -> new EmpaqueRegalo(pedido);
                case MANEJO_PRIORITARIO_FRAGIL -> new ManejoPrioritarioFragil(pedido);
            };
        }
        pedido.mostrarDetalle();
        double total = pedido.getTotal();
        System.out.printf("Total a cobrar: $%,.0f COP%n", total);

        // Proxy nuevo por operación: si la validación falla, nunca se crea el Adapter real.
        ProcesadorPago pagoSeguro = new ProcesadorPagoProxy(procesadorFactory);
        return pagoSeguro.cobrarEnPesos(usuario, total);
    }
}
