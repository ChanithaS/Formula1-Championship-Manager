import java.io.Serializable;
import java.util.Comparator;

public class Formula1Driver extends Driver implements Serializable, Comparable<Formula1Driver>{
    private int firstPlace;
    private int secondPlace;
    private int thirdPlace;
    private int noOfRaces;
    private int Points;

    public Formula1Driver(String name, String location, String team, int age) {
        super(name, location, team, age);
    }

    public int getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(int firstPlace) {
        this.firstPlace += firstPlace;
    }

    public int getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(int secondPlace) {
        this.secondPlace += secondPlace;
    }

    public int getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(int thirdPlace) {
        this.thirdPlace += thirdPlace;
    }

    public int getNoOfRaces() {
        return noOfRaces;
    }

    public void setNoOfRaces(int noOfRaces) {
        this.noOfRaces += noOfRaces;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points += points;
    }

    @Override
    public int compareTo(Formula1Driver sortTemp) {
        if (sortTemp.getPoints() == this.Points)
        {
            return sortTemp.firstPlace - this.firstPlace;
        }
        else {
            return  sortTemp.getPoints() - this.Points;
        }
    }

    //Comparator for sorting positions by 1st pos in descending order
    public static Comparator<Formula1Driver> PositionComparator = new Comparator<Formula1Driver>() {                    //https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/

        public int compare(Formula1Driver pos1, Formula1Driver pos2) {

            int posCom1 = pos1.getFirstPlace();
            int posCom2 = pos2.getFirstPlace();

            //In descending order
            return posCom2-posCom1;
        }};

    //Comparator for sorting points  in ascending order
    public static Comparator<Formula1Driver> PointsAscending = new Comparator<Formula1Driver>() {

        public int compare(Formula1Driver points1, Formula1Driver points2) {

            int pointCom1 = points1.getPoints();
            int pointCom2 = points2.getPoints();

            //For ascending order
            return pointCom1-pointCom2;
        }};

    //Comparator for sorting points  in descending order
    public static Comparator<Formula1Driver> PointsDescending = new Comparator<Formula1Driver>() {

        public int compare(Formula1Driver points1, Formula1Driver points2) {

            int pointCom1 = points1.getPoints();
            int pointCom2 = points2.getPoints();

            //For descending order
            return pointCom2-pointCom1;
        }};
}
