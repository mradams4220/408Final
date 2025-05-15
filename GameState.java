import java.util.Map;

class GameState {
    private String title;
    private String startingNode;
    private Map<String, GameNode> nodes;
    private String currentNodeId;
    public GameState(String title, String startingNode, Map<String, GameNode> nodes) {
        this.title = title;
        this.startingNode = startingNode;
        this.nodes = nodes;
        this.currentNodeId = startingNode;
    }

    public GameNode getCurrentNode() {
        return nodes.get(currentNodeId);
    }

    public void moveToNode(String nodeId) {
        if (nodes.containsKey(nodeId)) {
            currentNodeId = nodeId;
        } else {
            System.err.println("Invalid node ID: " + nodeId);
        }
    }

    public void reset() {
        currentNodeId = startingNode;
    }

    public String getTitle() {
        return title;
    }
}
