package co.uniquindio.edu.co.controller;

public final class EmpaqueRegalo extends ExtraDecorator {
    private static final double PRECIO = 15_000;
    public EmpaqueRegalo(Pedido pedido) { super(pedido); }
    @Override protected String nombreExtra() { return "Empaque de regalo premium"; }
    @Override protected double valorExtra() { return PRECIO; }
}