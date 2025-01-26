import java.util.Random;
import java.util.Scanner;

public class Task_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int roundsPlayed = 0;
        int totalScore = 0;
        boolean playAgain;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            roundsPlayed++;
            int numberToGuess = random.nextInt(100) + 1; // Generate random number between 1 and 100
            int attemptsLeft = 5; // Limit to 5 attempts
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + roundsPlayed + ":");
            System.out.println("Guess the number between 1 and 100. You have " + attemptsLeft + " attempts.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    totalScore += attemptsLeft + 1; // Higher score for fewer attempts
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again. Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("Too high! Try again. Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + numberToGuess + ".");
            }

            System.out.print("\nWould you like to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        } while (playAgain);

        System.out.println("\nGame Over!");
        System.out.println("Rounds Played: " + roundsPlayed);
        System.out.println("Total Score: " + totalScore);
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
