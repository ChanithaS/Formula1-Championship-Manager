public class Formula1Driver extends Driver{
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
}
