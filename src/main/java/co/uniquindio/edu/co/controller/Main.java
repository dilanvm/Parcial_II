package co.uniquindio.edu.co.controller;

import java.util.List;

public final class Main {
    private static final String CLAVE_COMERCIO_DEMO = "SHOPMASTER-DEMO-KEY";

    public static void main(String[] args) {
        ShopMasterFacade shopMaster = new ShopMasterFacade(() ->
                new StripeUSAAdapter(new StripeUSA(), CLAVE_COMERCIO_DEMO,
                        usuario -> usuario + "@shopmaster.test"));

        Combo kitLimpieza = new Combo("Kit de Limpieza")
                .agregar(new ProductoIndividual("Paño de microfibra", 25_000))
                .agregar(new ProductoIndividual("Limpiador", 15_000));
        Combo comboSetup = new Combo("Combo Setup Gamer")
                .agregar(new ProductoIndividual("Monitor", 800_000))
                .agregar(new ProductoIndividual("Teclado", 200_000))
                .agregar(kitLimpieza);

        // Verificación visible del Composite solicitado: $1.040.000 COP.
        if (Math.abs(comboSetup.getPrecio() - 1_040_000) > 0.001)
            throw new IllegalStateException("El total del combo no coincide con $1.040.000");
        System.out.println("=== Verificación Composite: total esperado $1.040.000 COP ===");
        comboSetup.mostrar("");

        System.out.println("\n######## CASO 1: COMPRA EXITOSA COMPLETA ########");
        // Orden explícito: garantía sobre $1.040.000, luego regalo +$15.000 => $1.159.000.
        shopMaster.ejecutarCompraRapida("cliente_vip", comboSetup,
                List.of(TipoExtra.GARANTIA_EXTENDIDA, TipoExtra.EMPAQUE_REGALO));

        System.out.println("\n######## CASO 2: RECHAZO POR TOPE ########");
        ItemCatalogo servidor = new ProductoIndividual("Servidor Empresarial", 6_000_000);
        shopMaster.ejecutarCompraRapida("cliente_normal", servidor,
                List.of(TipoExtra.MANEJO_PRIORITARIO_FRAGIL));

        System.out.println("\n######## CASO 3: RECHAZO POR USUARIO ########");
        ItemCatalogo mouse = new ProductoIndividual("Mouse", 150_000);
        shopMaster.ejecutarCompraRapida("BLOQUEADO", mouse, List.of());
    }
}