import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Scrabble Word Finder picks 7 random tiles and generates a list of
 * all of the playable Scrabble words from that file
 * @author jcochran
 * @version 03.28.2025
 */
public class ScrabbleWordFinder {
    private ArrayList<String> dictionary;
    private ArrayList<String> tileRack;


    /** class constructor, initializes ArrayList<String> dictionary */
    public ScrabbleWordFinder() {
        dictionary = new ArrayList<>();
        Scanner in = null;
        try {
            in = new Scanner(new File("SCRABBLE_WORDS.txt"));
            while (in.hasNext()) {
                dictionary.add(in.next());
            }
            in.close();
            // Do I need to sort the dictionary? Probably not.
            // now let's draw 7 random tiles and then shuffle my tile rack
            buildTileRack();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildTileRack() {
        String[] scrabbleTiles = {"A", "A", "A", "A", "A", "A", "A", "A", "A", "B", "B", "C", "C", "D", "D", "D", "D", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "F", "F", "G", "G", "G", "H", "H", "I", "I", "I", "I", "I", "I", "I", "I", "I", "J", "K", "L", "L", "L", "L", "M", "M", "N", "N", "N", "N", "N", "N", "O", "O", "O", "O", "O", "O", "O", "O", "P", "P", "Q", "R", "R", "R", "R", "R", "R", "S", "S", "S", "S", "T", "T", "T", "T", "T", "T", "U", "U", "U", "U", "V", "V", "W", "W", "X", "Y", "Y", "Z"};
        ArrayList<String> tiles = new ArrayList<>(Arrays.asList(scrabbleTiles));
        tileRack = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            tileRack.add(tiles.remove((int)(Math.random() * tiles.size())));
        }
        Collections.shuffle(tileRack);
    }
    /** displays the contents of the player's tile rack */
    public void printTiles()    {
        System.out.println("Letters in the tile rack: " + tileRack);
    }
    /** builds and returns an ArrayList of String objects that are values pulledfrom
     * the dictionary/database based on the available letters in the user's tile
     * rack
     * @return an ArrayList of playable words
     */
    public ArrayList<String> getPlaylist()  {
        ArrayList<String> playlist = new ArrayList<>();
        for(String temp : dictionary) {
            ArrayList<String> tilecopy = new ArrayList<>(tileRack);
            boolean canplay = true;
            // can I remove all of the letters in temp from tilecopy?
            // if yes, add temp to playlist
            // if no, continue
            for(int i = 0; i < temp.length(); i++) {
                if(!tilecopy.remove(temp.substring(i, i + 1))) {
                    canplay = false;
                    break;
                }
            }
            if(canplay)
                playlist.add(temp);
        }
        return playlist;
    }
    /** print all the playable words based on the letters in the tile rack */
    public void printMatches()  {
        ArrayList<String> wordPlays = getPlaylist();
        Collections.sort(wordPlays);
        if(wordPlays.size() == 0) {
            System.out.println("Sorry, NO words can be played from those tiles.");
            return;
        }
        System.out.println("You can play the following words from the letters in your tile rack:");
        String formatString = "%-" + 10 + "s";
        for(int i = 0; i < wordPlays.size(); i++) {
            String temp = wordPlays.get(i);
            System.out.printf(formatString, (temp + ((temp.length() == 7) ? "*":"")));
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n* denotes BINGO");
    }

    /** main method for the class; use only 3 command lines in main
     * @param args command line args, if needed
     */
    public static void main(String[] args){
        ScrabbleWordFinder app = new ScrabbleWordFinder();
        app.printTiles();
        app.printMatches();
    }
}
