package co.uniquindio.edu.co.controller;

import java.util.Objects;

public final class ProductoIndividual implements ItemCatalogo {
    private final String nombre;
    private final double precio;

    public ProductoIndividual(String nombre, double precio) {
        this.nombre = Objects.requireNonNull(nombre, "nombre");
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        this.precio = precio;
    }

    @Override public String getNombre() { return nombre; }
    @Override public double getPrecio() { return precio; }

    @Override public void mostrar(String sangria) {
        System.out.printf("%s- %s: $%,.0f COP%n", sangria, nombre, precio);
    }
}
