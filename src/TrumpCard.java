/**
 * Created by Darcie on 26/08/2016.
*/
public class TrumpCard extends Card{

    public TrumpCard(String title, String economicValue){
        super(title, economicValue);
    }

    public String toString(){
        return "cardTitle= " + cardTitle + " ," + cardChemistry;
    }
}
