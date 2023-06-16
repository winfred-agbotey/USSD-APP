package org.mawulidev;

import org.mawulidev.exceptions.InvalidUSSDCodeException;

import java.util.Scanner;

public class USSD {
    static Scanner scanner = new Scanner(System.in);

    public static void start() throws InvalidUSSDCodeException {
        System.out.println("Welcome to the USSD Application");
        System.out.println("Please select an option:");
        System.out.println("1. Buy airtime/ data");
        System.out.println("2. Send cash");
        System.out.println("3. Settings");

        int option = scanner.nextInt();

        switch (option) {
            case 1 -> HandleUSSDRequest.buyAirtimeOrData();
            case 2 -> HandleUSSDRequest.cash();
            case 3 -> HandleUSSDRequest.settings();
            default -> throw new InvalidUSSDCodeException("Invalid option selected");
        }
        scanner.close();
    }


}

