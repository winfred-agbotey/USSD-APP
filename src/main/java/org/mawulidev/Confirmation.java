package org.mawulidev;

import org.mawulidev.exceptions.InvalidUSSDCodeException;
import org.mawulidev.interfaces.ConfirmationActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.NumberFormat;

import static org.mawulidev.USSD.scanner;

public class Confirmation implements ConfirmationActions {
    HandleAirtime handleAirtime = new HandleAirtime();
    private Client client;
    public Confirmation(Client client) {
        this.client = client;
    }

    public void option() {
        System.out.println("Are you sure you want to continue this process?");
        System.out.println("1. Yes");
        System.out.println("2. Cancel(quit)");
        System.out.println("0. go back");

        int option = scanner.nextInt();
        switch (option) {
            case 1 -> yes();
            case 2 -> cancel();
            case 0 -> back();
            default -> throw new InvalidUSSDCodeException("Invalid option selected");
        }
        scanner.close();
    }

    @Override
    public void yes() {
        NumberFormat currency =  NumberFormat.getCurrencyInstance();
        double amount;
        try (FileInputStream fileIn = new FileInputStream("amount.ser")) {
            ObjectInputStream in = new ObjectInputStream(fileIn);
            amount = (double) in.readObject();
            double balance = client.getAccountBalance() -amount;
            String currentBalance = currency.format(balance);
            String airtimePurchased = currency.format(amount);

            if (handleAirtime.getPhone() == null) {
                System.out.println("Congrats! You just purchased " + airtimePurchased + " worth of airtime");
                System.out.println("Current Balance: "+ currentBalance);
            } else {
                System.out.println("Congrats! You just purchased " + airtimePurchased + " worth of airtime for " + handleAirtime.getPhone());
                System.out.println("Current Balance: "+ currentBalance);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void cancel() {
        System.out.println("Sad you decided not to purchase anymore!!!");
        System.exit(0);
    }

    @Override
    public void back() {
        HandleAirtime.enterAmount();
    }


}
