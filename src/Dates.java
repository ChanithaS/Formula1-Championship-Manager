import java.io.Serializable;
import java.util.ArrayList;

public class Dates implements Serializable {
    private final String date;
    private ArrayList<String> participated = new ArrayList<>();
    private ArrayList<Integer> positions = new ArrayList<>();
    private ArrayList<Integer> startPositions = new ArrayList<>();

    public Dates(String date)
    {
        this.date = date;
    }

    /**
     * @return - returns the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @return - returns the players participated in the race
     */
    public ArrayList<String> getParticipated() {
        return participated;
    }

    /**
     * @return - returns the positions which players obtained
     */
    public ArrayList<Integer> getPosition() {
        return positions;
    }
    public ArrayList<Integer> getStartPosition() {
        return startPositions;
    }

    /**
     * adds the player name and the position obtained by the player to the arraylists
     * @param name - passed player name
     * @param pos - passed player position
     */
    public void setPartAndPos(String name, int pos){
        participated.add(name);
        positions.add(pos);
    }
    public void setStartPosition(int sPos){
        startPositions.add(sPos);
    }
}