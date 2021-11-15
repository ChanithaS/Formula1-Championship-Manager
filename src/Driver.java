import java.io.Serializable;

public abstract class Driver implements Serializable {
    private String name;
    private String location;
    private String team;
    private int age;

    public Driver(String name, String location, String team, int age) {
        this.name = name;
        this.location = location;
        this.team = team;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getAge() {
        return age;
    }
}
