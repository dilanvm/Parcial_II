package co.uniquindio.edu.co.controller;

public class StripeUSA {
    public boolean chargeInDollars(double amountUSD, String customerEmail, String merchantKey){
        System.out.printf("[StripeUSA simulada] Cargo: $%,.2f USD | email:%s | clave:%s%n" , amountUSD,customerEmail,merchantKey);
        return true;
    }
}
