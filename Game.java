import java.util.List;
import java.util.Scanner;

class Game {
    private GameState gameState;
    private Scanner scanner;
    public Game(GameState gameState) {
        this.gameState = gameState;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== " + gameState.getTitle() + " ===");
        System.out.println();
        while (true) {
            GameNode currentNode = gameState.getCurrentNode();
            displayNode(currentNode);
            if (currentNode.isEnding()) {
                handleEnding(currentNode);
                break;
            }
            GameOption selectedOption = getPlayerChoice(currentNode);
            if (selectedOption == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            gameState.moveToNode(selectedOption.getNextNode());
            System.out.println();
        }
        scanner.close();
    }

    private void displayNode(GameNode node) {
        System.out.println(node.getText());
        System.out.println();
        if (node.isEnding()) {
            return;
        }
        System.out.println("What will you do?");
        List<GameOption> options = node.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i).getText());
        }
    }

    private GameOption getPlayerChoice(GameNode node) {
        System.out.print("> ");
        try {
            int choice = Integer.parseInt(scanner.nextLine()) - 1;
            if (choice >= 0 && choice < node.getOptions().size()) {
                return node.getOptions().get(choice);
            }
        } catch (NumberFormatException e) {
            System.out.println("That was not one of the options.");
        }
        return null;
    }

    private void handleEnding(GameNode node) {
        if (node.isWin()) {
            System.out.println("\n*** You achieved a victorious ending! ***");
        } else {
            System.out.println("\n*** This is the end of your journey... for now. ***");
        }
        System.out.println("\nWould you like to play again? (yes/no)");
        System.out.print("> ");
        String response = scanner.nextLine().toLowerCase();
        if (response.equals("yes")) {
            gameState.reset();
            System.out.println("\n\n");
            start();
        }
    }
}
