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

    /*Comparator for sorting the list by Student Name*/
    public static Comparator<Formula1Driver> DriverNameComparator = new Comparator<Formula1Driver>() {                  //https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/

        public int compare(Formula1Driver name1, Formula1Driver name2) {
            String driverName1 = name1.getName().toUpperCase();
            String driverName2 = name2.getName().toUpperCase();

            //ascending order
            //return driverName1.compareTo(driverName2);

            //descending order
            return driverName2.compareTo(driverName1);
        }};

    /*Comparator for sorting the list by roll no*/
    public static Comparator<Formula1Driver> PositionComparator = new Comparator<Formula1Driver>() {

        public int compare(Formula1Driver points1, Formula1Driver points2) {

            int pointCom1 = points1.getFirstPlace();
            int pointCom2 = points2.getFirstPlace();

            /*For ascending order*/
            //return pointCom1-pointCom2;

            /*For descending order*/
            return pointCom2-pointCom1;
        }};
}
