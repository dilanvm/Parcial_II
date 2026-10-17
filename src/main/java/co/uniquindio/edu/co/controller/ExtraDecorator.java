package co.uniquindio.edu.co.controller;

import java.util.Objects;

public abstract class ExtraDecorator implements Pedido {
    protected final Pedido pedido;
    protected ExtraDecorator(Pedido pedido) { this.pedido = Objects.requireNonNull(pedido, "pedido"); }
    protected abstract String nombreExtra();
    protected abstract double valorExtra();

    @Override public double getTotal() { return pedido.getTotal() + valorExtra(); }
    @Override public void mostrarDetalle() {
        pedido.mostrarDetalle();
        System.out.printf("  + %s: $%,.0f COP%n", nombreExtra(), valorExtra());
    }
}