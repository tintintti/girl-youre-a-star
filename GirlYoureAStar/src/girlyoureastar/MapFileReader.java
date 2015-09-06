package girlyoureastar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Luokka tarjoaa metodin kartan lukemiseen tekstitiedostosta
 *
 */
public class MapFileReader {

    private Scanner mapFile;
    private String filename;
    private int lines;
    private int columns;

    public MapFileReader(String filename) throws FileNotFoundException {
        this.filename = filename;
        mapFile = new Scanner(new File(filename));
        this.lines = 0;
        this.columns = Integer.MAX_VALUE;
    }

    /**
     * Metodi lukee tekstitiedostosta kartan ja muuntaa sen kaksiuloitteiseksi
     * kokonaislukutaulukoksi
     *
     * @return kartta kaksiuloitteisena taulukkona
     * @throws FileNotFoundException
     */
    public int[][] readMap() throws FileNotFoundException {

        while (mapFile.hasNext()) {
            lines++;
            String s = mapFile.nextLine();
            if (s.length() < columns) {
                columns = s.length();
            }
        }
        mapFile.close();

        int[][] map = new int[lines][columns];

        mapFile = new Scanner(new File(filename));
        int i = 0;
        while (mapFile.hasNextLine()) {
            String s = mapFile.nextLine();
            for (int j = 0; j < s.length(); j++) {
                map[i][j] = s.charAt(j) - 48;
            }
            i++;
        }

        mapFile.close();

        return map;
    }

}
