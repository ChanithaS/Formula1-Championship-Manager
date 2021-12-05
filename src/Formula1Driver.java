import java.io.Serializable;
import java.util.Comparator;

public class Formula1Driver extends Driver implements Serializable, Comparable<Formula1Driver>{
    private int firstPlace;
    private int secondPlace;
    private int thirdPlace;
    private int noOfRaces;
    private int Points;

    /**
     * constructor for Formula1Driver class with the super class data from driver
     * @param name - name of player from driver class
     * @param location - country of player from driver class
     * @param team - team of player from driver class
     * @param age - age of player from driver class
     */
    public Formula1Driver(String name, String location, String team, int age) {
        super(name, location, team, age);
    }

    /**
     * @return - returns the first position count
     */
    public int getFirstPlace() {
        return firstPlace;
    }

    /**
     * adds the value passed into the first position count
     * @param firstPlace - no.of first places to add
     */
    public void setFirstPlace(int firstPlace) {
        this.firstPlace += firstPlace;
    }

    /**
     * @return - returns the second position count
     */
    public int getSecondPlace() {
        return secondPlace;
    }

    /**
     * adds the value passed into the second position count
     * @param secondPlace - no.of second places to add
     */
    public void setSecondPlace(int secondPlace) {
        this.secondPlace += secondPlace;
    }

    /**
     * @return - returns the third position count
     */
    public int getThirdPlace() {
        return thirdPlace;
    }

    /**
     * adds the value passed into the third position count
     * @param thirdPlace - no.of third places to add
     */
    public void setThirdPlace(int thirdPlace) {
        this.thirdPlace += thirdPlace;
    }

    /**
     * @return - returns the no of races participated by the driver
     */
    public int getNoOfRaces() {
        return noOfRaces;
    }

    /**
     * adds the value passed into the no of races participated count of the driver
     * @param noOfRaces - no of races participated to add
     */
    public void setNoOfRaces(int noOfRaces) {
        this.noOfRaces += noOfRaces;
    }

    /**
     * @return - returns the points obtained by the driver
     */
    public int getPoints() {
        return Points;
    }

    /**
     * sets the no of points by adding the value passed
     * @param points - points gained to add
     */
    public void setPoints(int points) {
        Points += points;
    }

    /**
     * using comparable this compares the class data and passes the value accordingly
     * used for sorting the data according to points and 1st positions
     * when collections.sort is called
     * @param sortTemp - value to compare to
     * @return - returns the next value according to ascending order
     */
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

    /**
     * Comparator for sorting positions by 1st pos in descending order
     */
    public static Comparator<Formula1Driver> PositionComparator = new Comparator<Formula1Driver>() {                    //https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/

        public int compare(Formula1Driver pos1, Formula1Driver pos2) {

            int posCom1 = pos1.getFirstPlace();
            int posCom2 = pos2.getFirstPlace();

            //In descending order
            return posCom2-posCom1;
        }};

    /**
     * Comparator for sorting points  in ascending order
     */
    public static Comparator<Formula1Driver> PointsAscending = new Comparator<Formula1Driver>() {

        public int compare(Formula1Driver points1, Formula1Driver points2) {

            int pointCom1 = points1.getPoints();
            int pointCom2 = points2.getPoints();

            //For ascending order
            return pointCom1-pointCom2;
        }};

    /**
     * Comparator for sorting points  in descending order
     */
    public static Comparator<Formula1Driver> PointsDescending = new Comparator<Formula1Driver>() {

        public int compare(Formula1Driver points1, Formula1Driver points2) {

            int pointCom1 = points1.getPoints();
            int pointCom2 = points2.getPoints();

            //For descending order
            return pointCom2-pointCom1;
        }};
}
