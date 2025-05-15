import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GameState gameState = createGameState();
        Game game = new Game(gameState);
        game.start();
    }

    private static GameState createGameState() {
        Map<String, GameNode> nodes = new HashMap<>();
        GameNode node1 = new GameNode("1", "You exit Vault 111 to a nuclear sunrise. Diamond City radio crackles: 'Raiders at Concord! Gunners at Cambridge!' Your Pip-Boy marks a settlement to the north.", false, false);
        node1.addOption(new GameOption("Investigate Concord", "2"));
        node1.addOption(new GameOption("Head toward Cambridge", "3"));
        node1.addOption(new GameOption("Check the settlement marker", "4"));
        nodes.put("1", node1);

        GameNode node2 = new GameNode("2", "In Concord, Minutemen are pinned by raiders. A power armor frame sits nearby, but you hear a growl from a nearby deathclaw nest.", false, false);
        node2.addOption(new GameOption("Grab the power armor", "5"));
        node2.addOption(new GameOption("Sneak past to help Minutemen", "6"));
        node2.addOption(new GameOption("Retreat to Vault 111", "1"));
        nodes.put("2", node2);

        GameNode node3 = new GameNode("3", "Cambridge Police Station: Paladin Danse fights ferals. He shouts: 'Grab that laser rifle and cover me!' A synth component glints on a corpse.", false, false);
        node3.addOption(new GameOption("Join Danse's fight", "7"));
        node3.addOption(new GameOption("Take the synth component and run", "8"));
        nodes.put("3", node3);

        GameNode node4 = new GameNode("4", "Sanctuary Hills: Codsworth greets you. 'The Institute took Shaun! A workbench contains a 10mm pistol and a Vault-Tec lunchbox.", false, false);
        node4.addOption(new GameOption("Take supplies and search for clues", "9"));
        node4.addOption(new GameOption("Ask Codsworth to join you", "10"));
        nodes.put("4", node4);

        GameNode node5 = new GameNode("5", "The power armor's HUD boots up just as the deathclaw emerges. You shred it with the minigun, saving the Minutemen. Preston swears allegiance.", false, false);
        node5.addOption(new GameOption("Become Minutemen General", "11"));
        nodes.put("5", node5);

        GameNode node6 = new GameNode("6", "You save the Minutemen but take heavy wounds. Preston gives you a laser musket and leads you to Sanctuary. 'Another settlement needs our help...'", true, false);
        nodes.put("6", node6);

        GameNode node7 = new GameNode("7", "After the battle, Danse recruits you into the Brotherhood. 'Ad Victoriam!' Your search for Shaun continues under their banner.", true, false);
        nodes.put("7", node7);

        GameNode node8 = new GameNode("8", "The Railroad intercepts you. 'That component proves the Institute is nearby!' They offer help finding Shaun... for a price.", true, false);
        nodes.put("8", node8);

        GameNode node9 = new GameNode("9", "The lunchbox contains a fusion core! Vault-Tec files hint at 'the Institute' being beneath the CIT ruins.", false, false);
        node9.addOption(new GameOption("Head to CIT with your new gear", "12"));
        nodes.put("9", node9);

        GameNode node10 = new GameNode("10", "Codsworth's flamer makes short work of raiders. Together you uncover a pre-war holotape: Shaun was taken to a place called 'Kellogg's'.", true, false);
        nodes.put("10", node10);

        GameNode node11 = new GameNode("11", "As Minutemen General, you rebuild the Commonwealth. Years later, you storm the Institute with an army of settlers... and find an old man claiming to be Shaun.", true, true);
        nodes.put("11", node11);

        GameNode node12 = new GameNode("12", "At CIT, you use the power armor to break into the Institute. The scientists surrender immediately. 'Father' welcomes you home... as his replacement.", true, true);
        nodes.put("12", node12);
        return new GameState("Fallout CYOA", "1", nodes);
    }
}


