import java.io.Serializable;
import java.util.ArrayList;

public class Dates implements Serializable {
    private String date;
    private static ArrayList<String> participated = new ArrayList<>();
    private ArrayList<Integer> positions = new ArrayList<>();

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
        participated.add(name);
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
    public String printPlayers()
    {
        String players = String.valueOf(participated);
        return players;
    }
//    public static boolean nameHas(String name) {
//        boolean result = false;
//        for (int i = 0; i < participated.size(); i++)
//        {
//            if (participated.get(i).equals(name))
//            {
//                result = true;
//            }
//            else {
//                result = false;
//            }
//        }
//        return result;
//    }

}
