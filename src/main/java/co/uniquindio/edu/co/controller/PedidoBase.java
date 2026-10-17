package co.uniquindio.edu.co.controller;

import java.util.Objects;

public final class PedidoBase implements Pedido {
    private final ItemCatalogo item;

    public PedidoBase(ItemCatalogo item) { this.item = Objects.requireNonNull(item, "item"); }
    @Override public double getTotal() { return item.getPrecio(); }
    @Override public void mostrarDetalle() { item.mostrar("  "); }
}