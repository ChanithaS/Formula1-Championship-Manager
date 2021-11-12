public interface ChampionshipManager {
    void menu();
    void CreateDriver();
    void DeleteDriver();
    void ChangeTheTeam();
    void AddRace();

    int[] noOfPoints = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
    int[] positions = new int[30];
    String[] TeamArray = {"Mercedes", "Red Bull Racing", "McLaren",
            "Ferrari", "Alpine", "AlphaTauri", "Aston Martin", "Williams",
            "Alpha Romeo Racing", "Haas F1 Team"};
}
