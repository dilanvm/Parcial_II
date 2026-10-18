package co.uniquindio.edu.co.controller;

import java.util.Objects;
import java.util.function.Supplier;

public class ProcesadorPagoProxy implements ProcesadorPago{
    private static final double TOPE_COP = 5_000_000;
    private final Supplier<ProcesadorPago> procesadorFactory;
    private ProcesadorPago procesadorReal;

    public ProcesadorPagoProxy(Supplier<ProcesadorPago> procesadorFactory) {
        this.procesadorFactory = Objects.requireNonNull(procesadorFactory, "procesadorFactory");
    }

    @Override public boolean cobrarEnPesos(String usuario, double montoCOP) {
        if ("BLOQUEADO".equalsIgnoreCase(usuario)) {
            System.out.println("[RECHAZADO] Usuario reportado como BLOQUEADO. No se contactó la pasarela.");
            return false;
        }
        if (montoCOP > TOPE_COP) {
            System.out.printf("[RECHAZADO] El monto $%,.0f COP supera el tope de $%,.0f COP. No se contactó la pasarela.%n",
                    montoCOP, TOPE_COP);
            return false;
        }

        // Instanciación diferida: los pagos bloqueados no construyen Adapter ni StripeUSA.
        if (procesadorReal == null) procesadorReal = procesadorFactory.get();
        boolean aprobado = procesadorReal.cobrarEnPesos(usuario, montoCOP);
        if (aprobado) System.out.println("[AUDITORÍA: Transacción aprobada para " + usuario + "]");
        return aprobado;
    }
}
