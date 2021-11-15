import java.io.Serializable;
import java.util.ArrayList;

public class Dates implements Serializable {
    private String date;
    private static ArrayList<String> participated = new ArrayList<>();
    private ArrayList<Integer> positions = new ArrayList<>();
    private ArrayList<Integer> startPositions = new ArrayList<>();

    public Dates(String date)
    {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setParticipated(String name){
        participated.add(name.toLowerCase());
    }
    public ArrayList<String> getParticipated() {
        return participated;
    }
    public void setPosition(int pos){
        positions.add(pos);
    }
    public ArrayList<Integer> getPosition() {
        return positions;
    }
    public void setStartPosition(int sPos){
        startPositions.add(sPos);
    }
    public ArrayList<Integer> getStartPosition() {
        return startPositions;
    }
    public String printPlayers()
    {
        String players = String.valueOf(participated);
        return players;
    }
}
