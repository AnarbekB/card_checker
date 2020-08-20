package ru.balmukanov.card_checker.detector;

public class CardDetection implements Detector {

    @Override
    public String detect(String cardNumber) {
        int firstDigit = Character.getNumericValue(cardNumber.charAt(0));

        switch (firstDigit) {
            case 1:
                return "МИР";
            case 4:
                return "VISA";
            case 5:
                return "MASTERCARD";
            default:
                return null;
        }
    }
}
