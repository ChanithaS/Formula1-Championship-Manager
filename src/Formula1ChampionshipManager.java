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
        String menu = "\n" +
                "█████████████████████████████████████████████████████████████████████████\n" +
                "█▄─█▀▀▀█─▄█▄─▄▄─█▄─▄███─▄▄▄─█─▄▄─█▄─▀█▀─▄█▄─▄▄─███─▄─▄─█─▄▄─███▄─▄▄─█▀ ██\n" +
                "██─█─█─█─███─▄█▀██─██▀█─███▀█─██─██─█▄█─███─▄█▀█████─███─██─████─▄████ ██\n" +
                "▀▀▄▄▄▀▄▄▄▀▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▀▄▄▄▀▄▄▄▄▄▀▀▀▀▄▄▄▀▀▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▀\n"
                .concat("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n")
                .concat("|                      [1] . Create a New Driver                        |\n")
                .concat("|                      [2] . Delete a Driver                            |\n")
                .concat("|                      [3] . Change the Team                            |\n")
                .concat("|                      [4] . Display Driver Statistics                  |\n")
                .concat("|                      [5] . Display Formula 1 Driver Table             |\n")
                .concat("|                      [6] . Add Race                                   |\n")
                .concat("|                      [99]. Exit the Program                           |\n")
                .concat("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n")
                .concat("|                            Choose an Option                           |\n")
                .concat("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(menu);
        System.out.print("|                             : ");
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
                if (drivers.isEmpty())
                {
                    System.out.println("|                Please add a driver to Delete a driver                 |");
                    menu();
                    break;
                }
                else {
                    DeleteDriver();
                }
                break;
            case "3":
                if (drivers.size() >= 2)
                {
                    ChangeTheTeam();
                }
                else {
                    System.out.println("|           Please add two or more drivers to change the team           |");
                    menu();
                    break;
                }
                break;
            case "4":
                if (drivers.isEmpty())
                {
                    System.out.println("|           Please add a driver to Display Driver Statistics            |");
                    menu();
                    break;
                }
                else {
                    DeleteDriver();
                }
                break;
            case "5":
                if (drivers.isEmpty())
                {
                    System.out.println("|            Please add a driver to Display F1 Driver Table             |");
                    menu();
                    break;
                }
                else {
                    DeleteDriver();
                }
                break;
            case "6":
                if (drivers.isEmpty())
                {
                    System.out.println("|                   Please add a driver to add races                    |");
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
        System.out.println("|              Please enter the player name (ex: Don joe)               |");
        System.out.print("|                             : ");
        String driverName = sc1.next();

        //getting the player location in ISO format --------------------------------------------------------------------
        System.out.println("|        Please enter the player Location (ex: Sri Lanka = LKA )        |");
        System.out.print("|                             : ");
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
                System.out.println("|                     Please enter a valid location                     |");
                System.out.print("|                             : ");
                driverLocation = sc1.next();
            }
        }

        //getting the player age ---------------------------------------------------------------------------------------
        integerValidation("|                      Please enter the player Age                      |");
        int driverAge = 0;
        //validating using integerValidator. Also checks if greater than 18
        boolean validAge = false;
        while(!validAge) {
            if (value > 18 && value < 60) {
                driverAge = value;
                validAge = true;
            }
            else {
                integerValidation("|                Please enter an age above 18 and below 60                |");
            }
        }

        //getting player team ------------------------------------------------------------------------------------------
        //printing teams table for better user experience
        String AlignFormat = "     | %-27s | %-29s |%n";
        System.out.format("     +-----------------------------+-------------------------------+%n");
        System.out.format("     |        Current Teams        |    List of teams available    |     %n");
        System.out.format("     +-----------------------------+-------------------------------+%n");

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
        System.out.format("     +-----------------------------+-------------------------------+%n");

        //getting the input for team name
        System.out.println("|         Enter the player Team from the chart or any new team          |");
        System.out.print("|                             : ");
        driverTeam = sc1.next();

        //checking if the team already exits. if present getting the user input again
        if (!drivers.isEmpty()){
            boolean validTeam = false;
            while (!validTeam)
            {
                for (Formula1Driver driver : drivers) {
                    if (driver.getTeam().equalsIgnoreCase(driverTeam)) {
                        System.out.println("|          The team already exists, Please enter another team           |");
                        System.out.print("|                             : ");
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

        //adding data to drivers array as a new Formula1Driver object
        drivers.add(new Formula1Driver(driverName, driverLocation.toUpperCase(), driverTeam, driverAge));

        menu();
    }

    @Override
    public void DeleteDriver() {

        ShowDriverTable();
        integerValidation("|                   Select a driver number to delete                    |");
        TableInputValidator("|                     Please enter a valid number                       |");

        System.out.println("|              Driver " + drivers.get(value).getName() + "was deleted successfully");
        TeamNames.add(drivers.get(value).getTeam());
        drivers.remove(value);

        menu();
    }

    @Override
    public void ChangeTheTeam() {
        ShowDriverTable();

        integerValidation("|               Select a driver number to change the team               |");
        TableInputValidator("|                     Please enter a valid number                       |");
        int selectedDriver = value;
        String previousTeam = drivers.get(selectedDriver).getTeam();
        drivers.get(selectedDriver).setTeam("-");

        String AlignFormat = "                    | %-35s |%n";
        System.out.format("                    +-------------------------------+%n");
        System.out.format("                    |        Teams Available        |%n");
        System.out.format("                    +-------------------------------+%n");
        for (Formula1Driver driver : drivers) {
            System.out.format(AlignFormat, driver.getTeam());
        }
        integerValidation("|             Select a team number to change the team with              |");
        TableInputValidator("|                     Please enter a valid number                       |");
        int selectedTeam = value;

        boolean sameNo = false;
        while (!sameNo) {
            if (selectedTeam == selectedDriver)
            {
                integerValidation("|                    Please select a different Team                     |");
                TableInputValidator("|                     Please enter a valid number                       |");
                selectedTeam = value;
            }
            else {
                sameNo = true;
            }
        }

        drivers.get(selectedDriver).setTeam(drivers.get(selectedTeam).getTeam());
        drivers.get(selectedTeam).setTeam(previousTeam);

        System.out.println("|                       Team changed successfully                       |");

        menu();

    }

    @Override
    public void AddRace() {
        System.out.println("""
                |                 To add a single race stats enter S/s                  |
                |            To add multiple race data to a player enter M/m            |
                |                           To Quit enter Q/q                           |""");
        System.out.print("|                             : ");
        String addType = sc.next();

        boolean raceInput = false;
        while(!raceInput) {
            if (addType.equals("S") || addType.equals("s")) {

                raceInput = true;
                SingleRace();
                While("|           To add another Races enter Y/y, to exit enter Q/q           |", 1);
            }
            else if (addType.equals("M") || addType.equals("m"))
            {
                raceInput = true;
                MultipleRaces();
                While("|          To add another Player enter Y/y, to exit enter Q/q           |", 2);

            }
            else if (addType.equals("Q") || addType.equals("q"))
            {
                raceInput = true;
                menu();
            }
            else
            {
                System.out.println("|                  Input not valid enter a valid input                  |");
                System.out.print("|                             : ");
                addType = sc.next();
            }
        }
    }

    private void While(String printValue, int SorM)
    {
        System.out.println(printValue);
        System.out.print("|                             : ");
        String addAgain = sc.next();
        boolean again = false;
        while (!again) {
            if (addAgain.equals("Y") || addAgain.equals("y")) {
                if (SorM == 1)
                {
                    SingleRace();
                }
                else if (SorM == 2)
                {
                    MultipleRaces();
                }
                System.out.println("|    To add another enter Y/y, to go back to previous menu enter Q/q    |");
                System.out.print("|                             : ");
                addAgain = sc.next();
            }
            else if (addAgain.equals("Q") || addAgain.equals("q"))
            {
                //raceInput = true;
                again = true;
                AddRace();
            }
            else {
                System.out.println("|                  Input not valid enter a valid input                  |");
                System.out.print("|                             : ");
                addAgain = sc.next();
            }
        }
    }
    public void SingleRace()
    {
        String AlignFormat = "| %-15s |%n";
        System.out.format("+-----------------+------+%n");
        System.out.format("|      Dates added       |%n");
        System.out.format("+-----------------+------+%n");

        if (!dates.isEmpty()) {

            for (String datesAdded : dates) {
                System.out.format(AlignFormat, datesAdded);
            }
        } else {
            System.out.format(AlignFormat, "Empty");
        }

        System.out.println("|               Enter the race date in format(dd/MM/yyyy)               |");
        System.out.print("|                             : ");
        String date = sc.next();
        //Set up date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        boolean dateValid = false;
        while (!dateValid) {
            try {
                dateFormat.parse(date);
                dateValid = true;
            }
            /* Date format is invalid */ catch (ParseException e) {
                System.out.println("|      "+ date + " is Invalid Date format, Please enter a valid date");
                System.out.print("|                             : ");
                date = sc.next();
            }
        }
        dates.add(date);

        System.out.println("|-----------------------------------------------------------------------|");
        System.out.println("|   Enter the positions players has won in the race (eg: 1, 2, 3 ...)   |");
        System.out.println("|   If not participated or no position was obtained, Please enter a 0   |");
        System.out.println("|-----------------------------------------------------------------------|");

        for (Formula1Driver driver : drivers) {
            driver.setNoOfRaces(1);
            integerValidation("|    Enter the position that " + driver.getName() + " obtained in the race  ");
            if (value == 1) {
                driver.setFirstPlace(1);
                driver.setPoints(noOfPoints[0]);
            } else if (value == 2) {
                driver.setSecondPlace(1);
                driver.setPoints(noOfPoints[1]);
            } else if (value == 3) {
                driver.setThirdPlace(1);
                driver.setPoints(noOfPoints[2]);
            } else if (value > 3 && value <= 9) {
                driver.setPoints(noOfPoints[value - 1]);
            }
        }
    }

    public void MultipleRaces(){
        ShowDriverTable();
        integerValidation("|                       Select a driver to add data                       |");
        TableInputValidator("|                     Please enter a valid number                       |");

        int selectedDriver = value;
        integerValidation("|   Enter the no of 1st Places obtained by " + drivers.get(selectedDriver).getName() + " :");
        drivers.get(selectedDriver).setFirstPlace(value);
        int place1 = value;
        integerValidation("|   Enter the no of 2nd Places obtained by " + drivers.get(selectedDriver).getName() + " :");
        drivers.get(selectedDriver).setSecondPlace(value);
        int place2 = value;
        integerValidation("|   Enter the no of 3rd Places obtained by " + drivers.get(selectedDriver).getName() + " :");
        drivers.get(selectedDriver).setThirdPlace(value);
        int place3 = value;
        integerValidation("|   Enter the no of Races participated by " + drivers.get(selectedDriver).getName() + " :");
        drivers.get(selectedDriver).setNoOfRaces(value);
        integerValidation("|   Enter the Total no of Points obtained by " + drivers.get(selectedDriver).getName() + " :");

        int total = place1 * noOfPoints[0] + place2 * noOfPoints[1] + place3 * noOfPoints[2];
        boolean validTotal = false;
        while (!validTotal)
        {
            if (value < total) {
                System.out.println("|Total no.of Points entered doesnt match with positions/race count total|");
                integerValidation("|                Please enter a valid Total no.of points                |");
            }
            else {
                validTotal = true;
            }
        }
        drivers.get(selectedDriver).setPoints(value);

        System.out.println("|                        Data added successfully                        |");
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

    public void integerValidation(String test)
    {
        System.out.println(test);
        System.out.print("                             : ");
        while (!sc.hasNextInt()) {
            //this will check for integers
            System.out.println("|                       Please enter a number                           |");
            System.out.print("                             : ");
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

//    public static boolean dateValidation(String inputDate)
//    {
//        //Set up date format
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        //dateFormat.setLenient(false);
//        /* Create Date object
//         * parse the string into date
//         */
//        try
//        {
//            dateFormat.parse(inputDate);
//            System.out.println(inputDate+" is valid date format");
//        }
//        /* Date format is invalid */
//        catch (ParseException e)
//        {
//            System.out.println(inputDate+" is Invalid Date format");
//            return false;
//        }
//        /* Return true if date format is valid */
//        return true;
//    }

