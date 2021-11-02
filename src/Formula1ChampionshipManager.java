import java.util.ArrayList;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager {

    //Driver driver = new Driver();
    public ArrayList<Formula1Driver> drivers = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);
    public static int value;

    public static void main(String[] args) {
        Formula1ChampionshipManager F12 = new Formula1ChampionshipManager();
        F12.menu();
    }

    @Override
    public void menu() {
        String menu = "||         Welcome to Formula1 Championship        ||\n"
                .concat("<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>\n")
                .concat("<> [1]. Create a New Driver                      <>\n")
                .concat("<> [2]. Delete a Driver                          <>\n")
                .concat("<> [3]. Delete a Team                            <>\n")
                .concat("<> [4]. Change the Team                          <>\n")
                .concat("<> [5]. Display Statistics                       <>\n")
                .concat("<> [6]. Add Race                                 <>\n")
                .concat("<> [99]. Exit the Program                        <>\n")
                .concat("<>               Choose an Option                <>\n")
                .concat("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(menu);
        String mainMenuInput = sc.next();
        MenuChoices(mainMenuInput);
    }

    public static void MenuChoices(String choice)
    {
        switch (choice){
            case "1":
                //sa
                break;
            case "2":
                //sd
                break;
            case "3":
                //sas
                break;
            case "4":
                //sdd
                break;
            case "5":
                //sag
                break;
            case "6":
                //sdf
                break;
            case "99":
                //sah
                break;
            default:
                System.out.println("Input not valid please enter a valid input \n                   :");
                String input = sc.next();
                MenuChoices(input);
        }
    }

    @Override
    public void CreateDriver(){
        System.out.println("Please enter the player name");
        String driverName = sc.next();
        System.out.println("Please enter the player Location");
        String driverLocation = sc.next();
        String teams = "||         Please select the players team         ||\n"
                .concat("<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n")
                .concat("<> [1]. Mercedes                                   <>\n")
                .concat("<> [2]. Red Bull Racing                            <>\n")
                .concat("<> [3]. McLaren                                    <>\n")
                .concat("<> [4]. Ferrari                                    <>\n")
                .concat("<> [5]. Alpine                                     <>\n")
                .concat("<> [6]. AlphaTauri                                 <>\n")
                .concat("<> [7]. Aston Martin                               <>\n")
                .concat("<> [8]. Williams                                   <>\n")
                .concat("<> [9]. Alpha Romeo Racing                         <>\n")
                .concat("<> [10]. Haas F1 Team                              <>\n")
                .concat("<> [99]. Add Custom Team                           <>\n")
                .concat("<>               Choose an Option                  <>\n")
                .concat("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(teams);
        String[] TeamNames = {"Mercedes", "Red Bull Racing", "McLaren",
                "Ferrari", "Alpine", "AlphaTauri", "Aston Martin", "Williams",
                "Alpha Romeo Racing", "Haas F1 Team"};
        int selectedTeam = sc.nextInt();
        String driverTeam;
        if(selectedTeam > 0 && selectedTeam <= 10)
        {
            driverTeam = TeamNames[selectedTeam];
        }
//        else if(selectedTeam == 10)
//        {
//
//        }
//        drivers.add(new Formula1Driver(driverName, driverLocation, TeamNames[SelectedTeam]));
    }

    @Override
    public void DeleteDriver() {

    }


    /////////////////////////////////////// Validation /////////////////////////
    public static void integerValidation(String test)
    {
        System.out.print(test);
        while (!sc.hasNextInt()) {
            //this will check for integers
            System.out.println("|                     Please enter a number                        |");
            System.out.print("|                            : ");
            sc.next();
        }
        value = sc.nextInt();
    }
}
