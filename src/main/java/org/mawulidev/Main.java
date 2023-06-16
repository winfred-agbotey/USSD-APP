package org.mawulidev;

import org.mawulidev.exceptions.InvalidUSSDCodeException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RuntimeException {
        //start application
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the USSD code:");
            String ussdCode = scanner.nextLine();
            if (!ussdCode.equals("*455#")) {
                throw new InvalidUSSDCodeException("Invalid USSD Code.");
            } else {
                USSD.start();
            }
        } catch (InvalidUSSDCodeException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

}