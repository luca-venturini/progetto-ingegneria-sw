package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(){
        cards = new ArrayList<Card>();

        cards.add( new ApolloCard() );
        cards.add( new ArtemisCard() );
        cards.add( new AthenaCard() );
        cards.add( new AtlasCard() );
        cards.add( new DemeterCard() );
        cards.add( new HephaestusCard() );
        cards.add( new MinotaurCard() );
        cards.add( new PanCard() );
        cards.add( new PrometheusCard() );
        cards.add(new HestiaCard() );
        cards.add(new TritonCard() );
        cards.add(new ZeusCard() );
        cards.add(new ChronusCard() );
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
