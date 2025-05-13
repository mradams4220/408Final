import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private String title;
    private String startingNodeId;
    private Map<String, Node> nodes;
    private Scanner scanner;
    public Game(String filename) {
        this.nodes = new HashMap<>();
        this.scanner = new Scanner(System.in);
        loadGame(filename);
    }

    public void play() {
        System.out.println("\n=== " + title + " ===\n");
        Node currentNode = nodes.get(startingNodeId);
        while (currentNode != null) {
            System.out.println("\n" + currentNode.text + "\n");
            if (currentNode.isEnding) {
                if (currentNode.isWin) {
                    System.out.println(">>> YOU WIN! <<<");
                } else {
                    System.out.println(">>> GAME OVER <<<");
                }
                break;
            }
            if (currentNode.options.isEmpty()) {
                System.out.println("(No valid options - game over)");
                break;
            }
            for (int i = 0; i < currentNode.options.size(); i++) {
                System.out.println((i + 1) + ") " + currentNode.options.get(i).text);
            }
            int choice = -1;
            while (choice < 1 || choice > currentNode.options.size()) {
                System.out.print("\nChoose (1-" + currentNode.options.size() + "): ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number!");
                    choice = -1;
                }
            }
            String nextNodeId = currentNode.options.get(choice - 1).nextNodeId;
            currentNode = nodes.get(nextNodeId);
            if (currentNode == null) {
                System.out.println("Error: Invalid game path! Missing node " + nextNodeId);
            }
        }
        scanner.close();
        System.out.println("\nThanks for playing!");
    }

    private void loadGame(String filename) {
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            parseGameData(jsonContent.toString());
        } catch (IOException e) {
            System.err.println("Error loading game file: " + e.getMessage());
            System.exit(1);
        }
    }

    private void parseGameData(String jsonString) {
        title = extractValue(jsonString, "title");
        startingNodeId = extractValue(jsonString, "startingNode");
        String nodesStr = extractBetween(jsonString, "\"nodes\":[", "]");
        if (nodesStr != null) {
            String[] nodeEntries = nodesStr.split("\\},\\s*\\{");
            for (String nodeEntry : nodeEntries) {
                nodeEntry = nodeEntry.replaceAll("^\\{", "").replaceAll("\\}$", "");
                parseNode(nodeEntry);
            }
        }
    }

    private void parseNode(String nodeData) {
        String id = extractValue(nodeData, "id");
        String text = extractValue(nodeData, "text");
        Node node = new Node(id, text);
        if (nodeData.contains("\"isEnding\":true")) {
            node.isEnding = true;
            node.isWin = nodeData.contains("\"isWin\":true");
        }
        String optionsStr = extractBetween(nodeData, "\"options\":[", "]");
        if (optionsStr != null) {
            String[] optionEntries = optionsStr.split("\\},\\s*\\{");
            for (String optionEntry : optionEntries) {
                optionEntry = optionEntry.replaceAll("^\\{", "").replaceAll("\\}$", "");
                parseOption(optionEntry, node);
            }
        }
        nodes.put(id, node);
    }

    private void parseOption(String optionData, Node node) {
        String text = extractValue(optionData, "text");
        String nextNode = extractValue(optionData, "nextNode");
        node.options.add(new Node.Option(text, nextNode));
    }

    private String extractValue(String source, String key) {
        String searchKey = "\"" + key + "\":";
        int start = source.indexOf(searchKey);
        if (start == -1) return null;
        start += searchKey.length();
        while (start < source.length() &&
                (source.charAt(start) == ' ' || source.charAt(start) == ':')) {
            start++;
        }
        boolean quoted = source.charAt(start) == '"';
        if (quoted) start++;
        int end;
        if (quoted) {
            end = source.indexOf('"', start);
        } else {
            end = source.indexOf(',', start);
            if (end == -1) end = source.indexOf('}', start);
        }
        if (end == -1) end = source.length();
        return source.substring(start, end).trim();
    }

    private String extractBetween(String source, String startDelim, String endDelim) {
        int start = source.indexOf(startDelim);
        if (start == -1) return null;
        start += startDelim.length();
        int end = source.indexOf(endDelim, start);
        if (end == -1) return null;
        return source.substring(start, end);
    }
}