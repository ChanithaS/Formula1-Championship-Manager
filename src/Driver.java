import java.io.Serializable;

public abstract class Driver implements Serializable {
    private String name;
    private String location;
    private String team;
    private int age;

    /**
     * constructor for the driver class
     * @param name - name of player
     * @param location - country of player
     * @param team - team name of the player
     * @param age - age of the player
     */
    public Driver(String name, String location, String team, int age) {
        this.name = name;
        this.location = location;
        this.team = team;
        this.age = age;
    }

    /**
     * @return - returns the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * @return - returns the location of the player
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return - returns the team of the player
     */
    public String getTeam() {
        return team;
    }

    /**
     * sets the team name according to the string passed
     * @param team - team name
     */
    public void setTeam(String team) {
        this.team = team;
    }

    /**
     * @return - returns the age of the player
     */
    public int getAge() {
        return age;
    }
}
