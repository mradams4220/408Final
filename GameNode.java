import java.util.ArrayList;
import java.util.List;

class GameNode {
    private String id;
    private String text;
    private List<GameOption> options;
    private boolean isEnding;
    private boolean isWin;
    public GameNode(String id, String text, boolean isEnding, boolean isWin) {
        this.id = id;
        this.text = text;
        this.isEnding = isEnding;
        this.isWin = isWin;
        this.options = new ArrayList<>();
    }

    public void addOption(GameOption option) {
        options.add(option);
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<GameOption> getOptions() {
        return options;
    }

    public boolean isEnding() {
        return isEnding;
    }

    public boolean isWin() {
        return isWin;
    }
}
