package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(){
        cards= new ArrayList<Card>();

        cards.add( new ApolloCard("Apollo","") );
        cards.add( new ArtemisCard("Artemis","") );
        cards.add( new AthenaCard("Athena","") );
        cards.add( new AtlasCard("Atlas","") );
        cards.add( new DemeterCard("Demeter","") );
        cards.add( new HephaestusCard("Hephaestus","") );
        cards.add( new MinotaurCard("Minotaur","") );
        cards.add( new PanCard("Pan","") );
        cards.add( new PrometheusCard("Prometheus","") );
    }

    public Card selectByName(String name){
        for(Card c: cards){
            if( name.equals(c.getName()) ) {
                return c;
            }
        }
        return null;
    }

    public List<String> getCardNames(){
        ArrayList<String> cardsName = new ArrayList<>();
        for (Card c: cards)
            cardsName.add(c.getName());
        return cardsName;
    }
}
