import java.util.*;

class QuizQuestion {
    public String question;
    public String[] options;
    int correctAnswerIndex;

    public QuizQuestion(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean checkAnswer(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private int totalQuestions;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.score = 0;
        this.totalQuestions = questions.size();
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        int questionIndex = 0;

        for (QuizQuestion question : questions) {
            System.out.println("Question " + (questionIndex + 1) + ": " + question.getQuestion());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            // Set a timer for each question (e.g., 10 seconds)
            long startTime = System.currentTimeMillis();
            System.out.print("You have 10 seconds to answer. Enter your choice (1-4): ");
            int userAnswer = -1;

            while (System.currentTimeMillis() - startTime < 10000) {
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt();
                    if (userAnswer >= 1 && userAnswer <= 4) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please select a valid option (1-4).");
                    }
                }
            }

            if (userAnswer == -1) {
                System.out.println("Time's up! You didn't answer in time.");
            } else {
                boolean isCorrect = question.checkAnswer(userAnswer - 1);
                if (isCorrect) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer was: " + options[question.correctAnswerIndex]);
                }
            }
            questionIndex++;
            System.out.println();
        }

        displayResult();
        scanner.close();
    }

    private void displayResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Total Questions: " + totalQuestions);
        System.out.println("Your Score: " + score + "/" + totalQuestions);
    }
}

public class Task_4 {
    public static void main(String[] args) {
        List<QuizQuestion> questions = new ArrayList<>();

        // Add questions to the quiz
        questions.add(new QuizQuestion("What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2));
        questions.add(new QuizQuestion("Who is the CEO of Tesla?",
                new String[]{"Elon Musk", "Jeff Bezos", "Bill Gates", "Mark Zuckerberg"}, 0));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1));
        questions.add(new QuizQuestion("What is the largest ocean on Earth?",
                new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, 3));
        questions.add(new QuizQuestion("What is 2 + 2?",
                new String[]{"3", "4", "5", "6"}, 1));

        // Create the quiz
        Quiz quiz = new Quiz(questions);

        // Start the quiz
        quiz.startQuiz();
    }
}
