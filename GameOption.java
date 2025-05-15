class GameOption {
    private String text;
    private String nextNode;

    public GameOption(String text, String nextNode) {
        this.text = text;
        this.nextNode = nextNode;
    }

    public String getText() {
        return text;
    }

    public String getNextNode() {
        return nextNode;
    }
}