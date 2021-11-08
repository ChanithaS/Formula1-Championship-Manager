import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager {

    //Driver driver = new Driver();
    public static ArrayList<Formula1Driver> drivers = new ArrayList<>();

    public Scanner sc = new Scanner(System.in);
    Scanner sc1 = new Scanner(System.in).useDelimiter("\n");

    public static String[] TeamArray = {"Mercedes", "Red Bull Racing", "McLaren",
            "Ferrari", "Alpine", "AlphaTauri", "Aston Martin", "Williams",
            "Alpha Romeo Racing", "Haas F1 Team"};
    public static List<String> TeamNames = new ArrayList<>(Arrays.asList(TeamArray));

    public static int value;
    public static String driverTeam;

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
                .concat("<> [3]. -------------                            <>\n")
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

    public void MenuChoices(String choice)
    {
        switch (choice){
            case "1":
                CreateDriver();
                break;
            case "2":
                DeleteDriver();
                break;
            case "3":
                //sas
                break;
            case "4":
                //sdd
                break;
            case "5":
                //saggygg
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

        //Getting the player name --------------------------------------------------------------------------------------
        System.out.println("Please enter the player name (ex: Dohn joe)");
        String driverName = sc1.next();

        //getting the player location in ISO format --------------------------------------------------------------------
        System.out.println("Please enter the player Location (ex: Sri Lanka = LKA )");
        String driverLocation = sc1.next();
        //validating the location to 3 letters and only alphabetical letters
        boolean validLocation = false;
        while(!validLocation)
        {
            if (driverLocation.length() == 3 && driverLocation.matches("[a-zA-Z]+"))
            {
                validLocation = true;
            }
            else{
                System.out.println("|   Please enter a valid location   |");
                System.out.print("|                            : ");
                driverLocation = sc1.next();
            }
        }

        //getting the player age ---------------------------------------------------------------------------------------
        integerValidation("Please enter the player Age");
        int driverAge = 0;
        //validating using integerValidator. Also checks if greater than 18
        boolean validAge = false;
        while(!validAge) {
            if (value > 18) {
                driverAge = value;
                validAge = true;
            }
            else {
                integerValidation("Please enter an age above 18");
            }
        }

        //getting player team ------------------------------------------------------------------------------------------
        //printing teams table for better user experience
        String AlignFormat = "| %-15s | %-15s |%n";
        System.out.format("+-----------------+------+%n");
        System.out.format("|     Current Teams     |    List of teams available   |%n");
        System.out.format("+-----------------+------+%n");

        //checking if list is empty
        if(drivers.isEmpty())
        {
            //if empty printing out add player
            System.out.format(AlignFormat, "Please add a player", TeamNames.get(0));
            for (int i = 1; i < TeamNames.size(); i++){
                System.out.format(AlignFormat, "", TeamNames.get(i));
            }
        }
        else
        {
            //getting the maximum length array of the two arrays TeamNames and drivers
            int rows = Math.max(TeamNames.size(), drivers.size());

            //printing the table according to the different array lengths to avoid errors
            for (int i = 0; i < rows; i++) {
                if (i < drivers.size()) {
                    System.out.format(AlignFormat, drivers.get(i).getTeam(), TeamNames.get(i));
                }
                else if (i < TeamNames.size()) {
                    System.out.format(AlignFormat, "", TeamNames.get(i));
                } else {
                    System.out.println(".");
                }
            }
        }
        System.out.format("+-----------------+------+%n");

        //getting the input for team name
        System.out.println("Enter the player Team : ");
        driverTeam = sc1.next();

        //checking if the team already exits. if present getting the user input again
        if (!drivers.isEmpty()){
            boolean validTeam = false;
            while (!validTeam)
            {
                for (Formula1Driver driver : drivers) {
                    if (driver.getTeam().equalsIgnoreCase(driverTeam)) {
                        System.out.println("The team already exists, Please enter another team");
                        driverTeam = sc1.next();
                    }
                    else {
                        validTeam = true;
                    }
                }
            }
        }

        //this removes the team entered from the list and will not recommend that team again
        //removeIf function will represent a boolean-valued function of one argument and value -> value will go through all elements of the list
        TeamNames.removeIf(value->value.equalsIgnoreCase(driverTeam));

//        for (String str : TeamNames) {
//            if (str.equalsIgnoreCase(driverTeam)) {
//                System.out.println("present");
//            }
//        }
//        System.out.println(Arrays.toString(TeamNames.toArray()));

        //adding data to drivers array as a new Formula1Driver object
        drivers.add(new Formula1Driver(driverName, driverLocation.toUpperCase(), driverTeam, driverAge));

        menu();
    }

    @Override
    public void DeleteDriver() {

        String AlignFormat = "| %-3s | %-15s | %-15s |%n";
        System.out.format("+-----------------+------+%n");
        System.out.format("|     Driver     |    Driver Team   |%n");
        System.out.format("+-----------------+------+%n");
        for (int i = 0; i < drivers.size(); i++){
            System.out.format(AlignFormat,"["+ i +"]",drivers.get(i).getName(), drivers.get(i).getTeam());
        }

        System.out.println("Select a driver to delete");

    }


    /////////////////////////////////////// Validation /////////////////////////
    public void integerValidation(String test)
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
