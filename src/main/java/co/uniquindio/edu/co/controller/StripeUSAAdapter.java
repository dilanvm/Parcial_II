package co.uniquindio.edu.co.controller;
import java.util.Objects;
import java.util.function.Function;

public final class StripeUSAAdapter implements ProcesadorPago{
    private static final double COP_POR_USD =4_000.0;
    private final StripeUSA stripe;
    private final String merchantKey;
    private final Function<String, String> emailResolver;

    public StripeUSAAdapter(StripeUSA stripe, String merchantKey, Function<String, String> emailResolver){
        this.stripe =Objects.requireNonNull(stripe, "stripe");
        this.merchantKey=Objects.requireNonNull(merchantKey, "merchantKey");
        this.emailResolver=Objects.requireNonNull(emailResolver, "emailResolver");
    }


    @Override
    public boolean cobrarEnPesos(String usuario, double montoCOP) {
        double montoUSD = montoCOP / COP_POR_USD;
        String email = emailResolver.apply(usuario);
        System.out.printf("[Adapter] Conversión:$%,.0f COP -> $%,.2F USD%n", montoCOP, montoUSD);
        return stripe.chargeInDollars(montoUSD, email,merchantKey);
    }
}
