import java.io.Serializable;
import java.util.ArrayList;

public class Dates implements Serializable {
    private final String date;
    private ArrayList<String> participated = new ArrayList<>();
    private ArrayList<Integer> positions = new ArrayList<>();
    private static final ArrayList<Integer> startPositions = new ArrayList<>();

    public Dates(String date)
    {
        //, ArrayList<String> participated, ArrayList<Integer> positions
        this.date = date;
//        Dates.participated = participated;
//        Dates.positions = positions;
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

    public String ret(int i)
    {
        return participated.get(i);
    }
    public Integer ret1(int i)
    {
        return positions.get(i);
    }
//    public void setDate(String date) {
//        this.date = date;
//    }

    //    public void setParticipated(String name){
//        participated.add(name);
//    }
//
//    public void setPosition(int pos){
//        positions.add(pos);
//    }
    public void setPartAndPos(String name, int pos){
        participated.add(name);
        positions.add(pos);
    }

    public void setStartPosition(int sPos){
        startPositions.add(sPos);
    }

    public String printPlayers()
    {
        String players = String.valueOf(participated);
        return players;
    }
}