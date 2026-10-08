package ch05.collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class TestArrayCollection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        ArrayCollection<String> animalList = new ArrayCollection<>(1000);
        try {
            Scanner fileScanner = new Scanner(new File("ch05/collections/Animals.txt"));
            while (fileScanner.hasNextLine()) {
                String animal = fileScanner.nextLine().trim();
                if (!animal.isEmpty()) {
                    animalList.add(animal.toLowerCase());
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return;
        }

        System.out.println("Loaded animals in collection: " + animalList.size());
        System.out.println("First animals: " + animalList);

        char letter = generateRandomLetter(random);
        ArrayCollection<String> guessedAnimals = new ArrayCollection<>();

        System.out.println("Your letter is: " + letter);
        System.out.println("Type as many animals as you can that start with '" + letter + "'.");
        System.out.println("Type 'quit' to stop.\n");

        while (true) {
            System.out.print("Enter an animal: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            String normalized = input.toLowerCase();
            if (startsWithLetter(input, letter)) {
                if (animalList.contains(normalized)) {
                    if (!guessedAnimals.contains(normalized)) {
                        guessedAnimals.add(normalized);
                        System.out.println("Nice! \"" + input + "\" counts.");
                    } else {
                        System.out.println("You already used that one.");
                    }
                } else {
                    System.out.println("That animal is not in the list. Try a different one.");
                }
            } else {
                System.out.println("That does not start with '" + letter + "'.");
            }
        }

        System.out.println("\nYour score: " + guessedAnimals.size());
        scanner.close();
    }

    private static char generateRandomLetter(Random random) {
        return (char) ('A' + random.nextInt(26));
    }

    private static boolean startsWithLetter(String word, char letter) {
        return word.length() > 0 && Character.toUpperCase(word.charAt(0)) == Character.toUpperCase(letter);
    }
}

