import java.awt.image.BufferedImage;

/**
 * Created by Darcie on 26/08/2016.
*/
public class TrumpCard extends Card{

    public TrumpCard(String title, String economicValue, BufferedImage cardImage){
        super(title, economicValue, cardImage);
    }

    public String toString(){

        return "Card Name: " + cardTitle + " Category: " + cardEconomicValue + "\n";

    }
}
