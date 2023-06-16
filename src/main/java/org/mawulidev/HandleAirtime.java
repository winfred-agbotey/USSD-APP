package org.mawulidev;

import lombok.NoArgsConstructor;
import org.mawulidev.exceptions.InvalidAmountException;
import org.mawulidev.exceptions.InvalidPhoneNumber;
import org.mawulidev.exceptions.InvalidUSSDCodeException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static org.mawulidev.USSD.scanner;

@NoArgsConstructor
public class HandleAirtime implements Serializable {

    private static double amount;
    private static String phone;

    public HandleAirtime(double amount, String phone) {
        this.amount = amount;
        this.phone = phone;
    }

    public double getAmount() {
        return amount;
    }

    public  String getPhone() {
        return phone;
    }

    public static void airtime() throws InvalidUSSDCodeException {
        System.out.println("Please select an option:");
        System.out.println("1. Buy airtime for yourself");
        System.out.println("2. Buy airtime for someone");
        System.out.println("3. go back");

        int option = scanner.nextInt();
        switch (option) {
            case 1 -> enterAmount();
            case 2 -> forSomeone();
            case 3 -> HandleUSSDRequest.buyAirtimeOrData();
            default -> throw new InvalidUSSDCodeException("Invalid option selected");
        }
        scanner.close();
    }

     static void enterAmount() {
        System.out.println("Enter the amount to buy");
        amount = setAmount(scanner.nextDouble());
        if (amount < 0.5 || amount > 10000) {
            throw new InvalidAmountException("Please enter a valid amount");
        } else {
            try (FileOutputStream fileOut = new FileOutputStream("amount.ser")) {
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(amount);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            Confirmation confirmation = new Confirmation(new Client());
            confirmation.option();
        }
    }

    public static double setAmount(double amount) {
        HandleAirtime.amount = amount;
        return amount;
    }

    public static String setPhone(String phone) {
        HandleAirtime.phone = phone;
        return phone;
    }

    private static void forSomeone() {
        System.out.println("Enter the recipient number");
        phone = setPhone(scanner.next());
        if (isValidPhoneNumber(phone)) {
//            try (FileOutputStream fileOut = new FileOutputStream("phone.ser")) {
//                ObjectOutputStream out = new ObjectOutputStream(fileOut);
//                out.writeObject(phone);
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
            enterAmount();
        } else {
            throw new InvalidPhoneNumber("Please Enter a valid phone number.");
        }


    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String pattern = "^(\\+\\d{2})?\\d{10}$";
        return phoneNumber.matches(pattern);
    }

}
