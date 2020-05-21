package it.polimi.ingsw.ParenteVenturini.Network.Client;


/**
 * this class convert the Integer into workers colors
 */
public class ColourPrint {
    static final String RESET = "\u001B[0m";
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_BLUE = "\u001B[34m";
    private static final String TEXT_GREEN = "\u001B[32m";

    public void print(int colour, String s){
        switch (colour){
            case 1:
                System.out.print(TEXT_RED + s + RESET);
                break;
            case 2:
                System.out.print(TEXT_BLUE + s + RESET);
                break;
            case 3:
                System.out.print(TEXT_GREEN + s + RESET);
                break;
            default:
                System.out.print(s);
        }
    }
}
