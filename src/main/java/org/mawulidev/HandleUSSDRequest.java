package org.mawulidev;

import org.mawulidev.exceptions.InvalidUSSDCodeException;

import static org.mawulidev.USSD.scanner;


public class HandleUSSDRequest {

    //airtime/data
    static void buyAirtimeOrData() throws InvalidUSSDCodeException {

        System.out.println("Please select an option:");
        System.out.println("1. Buy airtime");
        System.out.println("2. Buy Data");
        System.out.println("3. go back");

        int option = scanner.nextInt();
        switch (option) {
            case 1 -> HandleAirtime.airtime();
            case 2 -> HandleData.data();
            case 3 -> USSD.start();
            default -> throw new InvalidUSSDCodeException("Invalid option selected");
        }
        scanner.close();
    }


    //cash
    static void cash() throws InvalidUSSDCodeException {
        System.out.println("Coming Soon!!!...");
    }

    //settings
    static void settings() throws InvalidUSSDCodeException {
        System.out.println("Coming Soon!!!...");
    }


}
