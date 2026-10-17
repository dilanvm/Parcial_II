package co.uniquindio.edu.co.controller;

public final class ManejoPrioritarioFragil extends ExtraDecorator {
    private static final double PRECIO = 25_000;
    public ManejoPrioritarioFragil(Pedido pedido) { super(pedido); }
    @Override protected String nombreExtra() { return "Manejo prioritario frágil"; }
    @Override protected double valorExtra() { return PRECIO; }
}
