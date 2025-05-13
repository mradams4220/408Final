import java.util.ArrayList;
import java.util.List;

public class Node {
    public static class Option {
        public String text;
        public String nextNodeId;
        public Option(String text, String nextNodeId) {
            this.text = text;
            this.nextNodeId = nextNodeId;
        }
    }

    public String id;
    public String text;
    public boolean isEnding;
    public boolean isWin;
    public List<Option> options;
    public Node(String id, String text) {
        this.id = id;
        this.text = text;
        this.options = new ArrayList<>();
    }
}