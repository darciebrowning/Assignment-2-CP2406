import java.util.ArrayList;
class Player {

    public ArrayList<Card> playerHand;
    public boolean isHuman;
    public boolean hasPassed;
    public boolean wonHand;

    public Player() {
        isHuman = false;
        hasPassed = false;
        wonHand = false;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }


    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

}


