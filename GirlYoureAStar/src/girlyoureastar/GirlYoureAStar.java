package girlyoureastar;

import java.io.FileNotFoundException;

public class GirlYoureAStar {

    public static void main(String[] args) {
        TextUI ui = new TextUI();
        try {
            ui.start();
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löydy.");
        }

    }

}
