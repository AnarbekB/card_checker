package ru.balmukanov.card_checker.validator;

import java.util.ArrayList;

public class Luna implements Validator {

    @Override
    public boolean isValid(String cardNumber) {
        int length = cardNumber.length();
        int even = cardNumber.length() % 2;
        int digitSum = 0;

        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));

            if (i % 2 == even) {
                if (digit * 2 > 9) {
                    digits.add(digit * 2 - 9);
                } else {
                    digits.add(digit * 2);
                }
            } else {
                digits.add(digit);
            }
        }

        for (Integer d : digits) {
            digitSum += d;
        }

        return digitSum % 10 == 0;
    }
}
