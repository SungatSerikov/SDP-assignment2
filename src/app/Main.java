package app;

import abstractfactory.GUIFactory;
import abstractfactory.MacOSFactory;
import abstractfactory.WindowsFactory;
import factorymethod.Logistics;
import factorymethod.RoadLogistics;
import factorymethod.SeaLogistics;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter delivery mode (ROAD or SEA): ");

        if (!scanner.hasNextLine()) {
            System.out.println("Missing delivery mode.");
            return;
        }

        String deliveryMode = scanner.nextLine()
                .trim()
                .toUpperCase(Locale.ROOT);

        Logistics logistics = selectLogistics(deliveryMode);

        if (logistics == null) {
            System.out.println("Unsupported delivery mode: " + deliveryMode);
            return;
        }

        System.out.print("Enter UI platform (WINDOWS or MACOS): ");

        if (!scanner.hasNextLine()) {
            System.out.println("Missing UI platform.");
            return;
        }

        String platform = scanner.nextLine()
                .trim()
                .toUpperCase(Locale.ROOT);

        GUIFactory guiFactory = selectGUIFactory(platform);

        if (guiFactory == null) {
            System.out.println("Unsupported UI platform: " + platform);
            return;
        }

        System.out.println();
        System.out.println("Delivery mode: " + deliveryMode);
        System.out.println("UI platform: " + platform);

        DeliveryApplication application =
                new DeliveryApplication(guiFactory, logistics);

        application.run(
                "laboratory equipment",
                "Aktau warehouse"
        );
    }

    private static Logistics selectLogistics(String deliveryMode) {
        return switch (deliveryMode) {
            case "ROAD" -> new RoadLogistics();
            case "SEA" -> new SeaLogistics();
            default -> null;
        };
    }

    private static GUIFactory selectGUIFactory(String platform) {
        return switch (platform) {
            case "WINDOWS" -> new WindowsFactory();
            case "MACOS" -> new MacOSFactory();
            default -> null;
        };
    }
}