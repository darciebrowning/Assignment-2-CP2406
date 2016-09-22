import java.util.ArrayList;
public class Player {
    //    public ArrayList<Player> players;
    public ArrayList<Card> playerHand;
    public boolean isHuman;

    public Player() {
        isHuman = false;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }


    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

}


