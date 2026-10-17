package co.uniquindio.edu.co.controller;

public final class GarantiaExtendida extends ExtraDecorator {
    public GarantiaExtendida(Pedido pedido) { super(pedido); }
    @Override protected String nombreExtra() { return "Póliza de garantía extendida (10%)"; }
    @Override protected double valorExtra() { return pedido.getTotal() * 0.10; }
    @Override public double getTotal() { return pedido.getTotal() * 1.10; }
}

