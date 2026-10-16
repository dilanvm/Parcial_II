package co.uniquindio.edu.co.shopmaster.catalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

    private final String nombre;
    private final List<ItemCatalogo> elementos = new ArrayList<>();

    public Combo(String nombre) { this.nombre = Objects.requireNonNull(nombre, "nombre"); }

    public Combo agregar(ItemCatalogo elemento) {
        elementos.add(Objects.requireNonNull(elemento, "elemento"));
        return this;
    }

    @Override public String getNombre() { return nombre; }

    @Override public double getPrecio() {
        return elementos.stream().mapToDouble(ItemCatalogo::getPrecio).sum();
    }

    @Override public void mostrar(String sangria) {
        System.out.printf("%s+ %s (total: $%,.0f COP)%n", sangria, nombre, getPrecio());
        for (ItemCatalogo elemento : elementos) elemento.mostrar(sangria + "  ");
    }
}