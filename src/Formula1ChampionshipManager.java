import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager {

    //Driver driver = new Driver();
    public static ArrayList<Formula1Driver> drivers = new ArrayList<>();

    public Scanner sc = new Scanner(System.in);
    Scanner sc1 = new Scanner(System.in).useDelimiter("\n");

    public static String[] TeamArray = {"Mercedes", "Red Bull Racing", "McLaren",
            "Ferrari", "Alpine", "AlphaTauri", "Aston Martin", "Williams",
            "Alpha Romeo Racing", "Haas F1 Team"};
    public static List<String> TeamNames = new ArrayList<>(Arrays.asList(TeamArray));
    public static ArrayList<String> dates = new ArrayList<>();

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
                ChangeTheTeam();
                break;
            case "5":
                //saggygg
                break;
            case "6":
                if (drivers.isEmpty())
                {
                    System.out.println("Please add a driver to add races");
                    menu();
                    break;
                }
                else {
                    AddRace();
                }
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
            System.out.format(AlignFormat, "Empty", TeamNames.get(0));
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
        TeamNameInputValidator();

        //this removes the team entered from the list and will not recommend that team again
        //removeIf function will represent a boolean-valued function of one argument and value -> value will go through all elements of the list
        TeamNames.removeIf(value->value.equalsIgnoreCase(driverTeam));

        //adding data to drivers array as a new Formula1Driver object
        drivers.add(new Formula1Driver(driverName, driverLocation.toUpperCase(), driverTeam, driverAge));

        menu();
    }

    @Override
    public void DeleteDriver() {

        ShowDriverTable();
        integerValidation("Select a driver number to delete");
        TableInputValidator("Please enter a valid number ");

        System.out.println("Driver " + drivers.get(value).getName() + "was deleted successfully");
        TeamNames.add(drivers.get(value).getTeam());
        drivers.remove(value);

        menu();
    }

    @Override
    public void ChangeTheTeam() {
        ShowDriverTable();
        integerValidation("Select a driver number to change the team");
        TableInputValidator("Please enter a valid number ");

        int selectedDriver = value;

        String AlignFormat = "| %-15s |%n";
        System.out.format("+-----------------+------+%n");
        System.out.format("|     Teams Available     |%n");
        System.out.format("+-----------------+------+%n");
        for (String teamName : TeamNames) {
            System.out.format(AlignFormat, teamName);
        }
        TeamNameInputValidator();

        TeamNames.add(drivers.get(selectedDriver).getTeam());
        drivers.get(selectedDriver).setTeam(driverTeam);

        menu();

    }

    @Override
    public void AddRace() {
        System.out.println("To add a single race stats enter S/s  || To add multiple race data to a player enter M/m");
        System.out.print("|                                     : ");
        String addType = sc.next();

        boolean raceInput = false;
        while(!raceInput)
        {
            if (addType.equals("S") || addType.equals("s"))
            {
                String AlignFormat = "| %-15s |%n";
                System.out.format("+-----------------+------+%n");
                System.out.format("|      Dates added       |%n");
                System.out.format("+-----------------+------+%n");

                if (!dates.isEmpty())
                {

                    for (String datesAdded : dates) {
                        System.out.format(AlignFormat, datesAdded);
                    }
                }
                else {
                    System.out.format(AlignFormat, "Empty");
                }

                System.out.println("Enter the race date in format(dd/MM/yyyy) : ");
                String date = sc.next();
                //Set up date format
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                boolean dateValid = false;
                while (!dateValid)
                {
                    try
                    {
                        dateFormat.parse(date);
                        dateValid = true;
                    }
                    /* Date format is invalid */
                    catch (ParseException e)
                    {
                        System.out.println(date+" is Invalid Date format, Please enter a valid date :");
                        date = sc.next();
                    }
                }
                dates.add(date);

                System.out.println("Enter the positions players has won in the race (eg: 1, 2, 3 ...)");
                System.out.println("If not participated or no position was obtained, Please enter a 0 ");

                for (Formula1Driver driver : drivers) {
                    integerValidation("Enter the position of the driver " + driver.getName() + " obtained in the race : ");

                        driver.setFirstPlace(1);
                }

                raceInput = true;
            }
            else if (addType.equals("M") || addType.equals("m"))
            {
                raceInput = true;
            }
            else {
                System.out.println("|              Input not valid enter a valid input:                |");
                System.out.print("|                            : ");
                addType = sc.next();
            }
        }



    }

    public void ShowDriverTable() {
        String AlignFormat = "| %-3s | %-15s | %-15s |%n";
        System.out.format("+-----------------+------+%n");
        System.out.format("|     Driver     |    Driver Team   |%n");
        System.out.format("+-----------------+------+%n");
        for (int i = 0; i < drivers.size(); i++){
            System.out.format(AlignFormat,"["+ i +"]",drivers.get(i).getName(), drivers.get(i).getTeam());
        }
    }


    /////////////////////////////////////// Validation /////////////////////////
    public void TableInputValidator(String sentence) {
        boolean validDriverNumber = false;
        while (!validDriverNumber)
        {
            if (value > drivers.size() || value < 0){
                integerValidation(sentence);
            }
            else {
                validDriverNumber = true;
            }
        }
    }

    private void TeamNameInputValidator() {
        //getting the input for team name
        System.out.println("Enter the player Team from the chart or any new team: ");
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
    }

    public static boolean dateValidation(String inputDate)
    {
        //Set up date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //dateFormat.setLenient(false);
        /* Create Date object
         * parse the string into date
         */
        try
        {
            dateFormat.parse(inputDate);
            System.out.println(inputDate+" is valid date format");
        }
        /* Date format is invalid */
        catch (ParseException e)
        {
            System.out.println(inputDate+" is Invalid Date format");
            return false;
        }
        /* Return true if date format is valid */
        return true;
    }

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

//        for (String str : TeamNames) {
//            if (str.equalsIgnoreCase(driverTeam)) {
//                System.out.println("present");
//            }
//        }
//        System.out.println(Arrays.toString(TeamNames.toArray()));

