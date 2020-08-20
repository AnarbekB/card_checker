package ru.balmukanov.card_checker;

import org.apache.commons.cli.*;
import ru.balmukanov.card_checker.detector.CardDetection;
import ru.balmukanov.card_checker.detector.Detector;
import ru.balmukanov.card_checker.validator.Luna;
import ru.balmukanov.card_checker.validator.Validator;

public class Main {

    private static final Validator checker = new Luna();

    private static final Detector detector = new CardDetection();

    public static void main(String[] args) {

        Options options = new Options();

        Option inputCardNumber = new Option("c", "card", true, "Card number");
        inputCardNumber.setRequired(true);
        options.addOption(inputCardNumber);

        Option inputOperationType = new Option("t", "type", true, "Operation type: check|detect");
        inputOperationType.setRequired(false);
        options.addOption(inputOperationType);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        String cardNumber = cmd.getOptionValue("card");
        if (!cardNumber.matches("[0-9]+")) {
            System.out.println("Only digit");
            System.exit(1);
        }

        String operationType = cmd.getOptionValue("type");
        OperationType operationTypeEnum =  OperationType.CHECK;
        if (operationType != null) {
            operationTypeEnum = OperationType.valueOf(operationType.toUpperCase());
        }

        switch (operationTypeEnum) {
            case CHECK:
                if (checker.isValid(cardNumber)) {
                    System.out.println("Card " + cardNumber + " is valid");
                } else {
                    System.out.println("Card " + cardNumber + " not valid");
                }
                break;
            case DETECT:
                System.out.println("Detect is: " + detector.detect(cardNumber));
                break;
        }
    }

}
