import java.io.Serializable;
import java.util.ArrayList;

public class Dates implements Serializable {
    private final String date;
    private ArrayList<String> participated = new ArrayList<>();
    private ArrayList<Integer> positions = new ArrayList<>();
    private static final ArrayList<Integer> startPositions = new ArrayList<>();

    public Dates(String date)
    {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public ArrayList<String> getParticipated() {
        return participated;
    }
    public ArrayList<Integer> getPosition() {
        return positions;
    }
    public ArrayList<Integer> getStartPosition() {
        return startPositions;
    }
    public void setPartAndPos(String name, int pos){
        participated.add(name);
        positions.add(pos);
    }
    public void setStartPosition(int sPos){
        startPositions.add(sPos);
    }
}