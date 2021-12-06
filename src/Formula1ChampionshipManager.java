import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager {

    //creating 2 object arraylist to store data as drivers and dates
    public static ArrayList<Formula1Driver> drivers = new ArrayList<>();
    public static ArrayList<Dates> dates = new ArrayList<>();

    public Scanner sc = new Scanner(System.in);
    //used to take string input with white spaces
    Scanner sc1 = new Scanner(System.in).useDelimiter("\n");

    public static ArrayList<String> TeamNames = new ArrayList<>(Arrays.asList(TeamArray));

    public static int value;
    public static String driverTeam;
    //creating to remove static method out of other functions
    static Formula1ChampionshipManager F12 = new Formula1ChampionshipManager();

    public static void main(String[] args) {
        Save();
        F12.menu();
    }

    /**
     *
     * @return
     * returns the drivers object arraylist
     */
    public static ArrayList<Formula1Driver> getList() {
        return drivers;
    }

    /**
     *returns the dates arraylist when on call
     */
    public static ArrayList<Dates> getDatesList() {
        return dates;
    }

    /**
     * prints the menu
     */
    @Override
    public void menu() {
        String menu = "\n" +
                "█████████████████████████████████████████████████████████████████████████\n" +
                "█▄─█▀▀▀█─▄█▄─▄▄─█▄─▄███─▄▄▄─█─▄▄─█▄ ▀█▀─▄█▄─▄▄─███─▄─▄─█─▄▄─███▄─▄▄─█▀ ██\n" +
                "██─█─█─█─███─▄█▀██─██▀█─███▀█─██─██ █▄█─███─▄█▀█████─███─██─████─▄████ ██\n" +
                "▀▀▄▄▄▀▄▄▄▀▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▀▄▄▄▀▄▄▄▄▄▀▀▀▀▄▄▄▀▀▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▀\n"
                .concat("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n")
                .concat("|                      [1] . Create a New Driver                        |\n")
                .concat("|                      [2] . Delete a Driver                            |\n")
                .concat("|                      [3] . Change the Team                            |\n")
                .concat("|                      [4] . Display Driver Statistics                  |\n")
                .concat("|                      [5] . Display Formula 1 Driver Table             |\n")
                .concat("|                      [6] . Add Race                                   |\n")
                .concat("|                      [7] . Open Application                           |\n")
                .concat("|                      [99]. Exit the Program                           |\n")
                .concat("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n")
                .concat("|                            Choose an Option                           |\n")
                .concat("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(menu);
        System.out.print("|                             : ");
        //takes the menu input from user and pass it to switch case function
        String mainMenuInput = sc.next();
        MenuChoices(mainMenuInput);
    }

    /**
     * checks if the user input is correct and runs the function accordingly checking factors
     * @param choice - user input
     */
    public void MenuChoices(String choice) {
        switch (choice){
            case "1":
                RunFunctionAgain(0, 1, "|   To Create another driver enter Y/y or to return to menu enter Q/q   |");
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
                    RunFunctionAgain(2,1,"| To Change another driver team enter Y/y or to return to menu enter Q/q|");
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
                    RunFunctionAgain(3, 1, "|   To display another driver enter Y/y or to return to menu enter Q/q  |");
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
                    F1DriverTable();
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

            case "7":
                if (drivers.isEmpty())
                {
                    System.out.println("|                  Please add a driver to view table                    |");
                    menu();
                    break;
                }
                else {
                    //creates a new swingGui and calls the main function in that class
                    SwingGUI swing = new SwingGUI();
                    swing.GuiMain();
                    System.out.println("|                  Opening the application..........                    |");
                }
                break;
            case "99":
                exit();
                break;
            default:
                //if input doesn't match ...recurse throught the function again untill valid function given
                System.out.println("|              Input not valid please enter a valid input               |");
                System.out.print("|                             : ");
                String input = sc.next();
                MenuChoices(input);
        }
    }

    /**
     * asked if user want to run function again and if not exit
     * @param value - calling the respective function
     * @param SorM - to call the specific function
     * @param s - user instructions
     */
    public void RunFunctionAgain(int value, int SorM, String s) {
        String input;
        boolean again = true;
        do {
            if(value == 0)
            {
                CreateDriver();
            }
            else if(value == 2){
                ChangeTheTeam();
            }
            else if(value == 3)
            {
                DisplayDriverStat();
            }
            else if (value == 4)
            {
                F1DriverTable();
            }
            else if (value == 5)
            {
                SingleRace();
            }
            else if (value == 6){
                MultipleRaces();
            }
            //checks
            boolean valid;
            do {
                System.out.println(s);
                System.out.print("|                             : ");
                input = sc.next();

                if (input.equals("Q") || input.equals("q"))
                {
                    again = false;
                    valid = false;
                }
                else if (input.equals("Y") || input.equals("y"))
                {
                    valid = false;
                }
                else {
                    System.out.println("|              Input not valid please enter a valid input               |");
                    valid = true;
                }
            } while (valid);
        } while (again);

        if (SorM == 1)
        {
            menu();
        }
        else if (SorM ==2)
        {
            AddRace();
        }

    }

    /**
     * method for creating the driver
     */
    @Override
    public void CreateDriver(){

        //Getting the player name --------------------------------------------------------------------------------------
        System.out.println("|              Please enter the player name (ex: Don joe)               |");
        System.out.print("|                             : ");
        String driverName = sc1.next();
        //checks if the driver name already exits if exits asked to enter a valid name
        boolean validName = false;
        while (!validName){
            if (returnYorN(driverName))
            {
                System.out.println("|                       Please enter a valid Name                       |");
                System.out.print("|                             : ");
                driverName = sc1.next();
            }
            else {
                validName = true;
            }
        }

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
        //validating using integerValidator. Also checks if greater than 18 and less than 60
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

            //printing the table according to the largest array length to avoid errors
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
        System.out.println("|                       Driver added Successfully                       |");

        //menu();
    }

    /**
     * method for deleting a driver
     */
    @Override
    public void DeleteDriver() {
        //running a do while loop until the user wants to exit the function
        String inputOp;
        boolean again = true;
        do {
            //checking if driver list is empty and if not continuing
            if (drivers.isEmpty())
            {
                again = false;
                System.out.println("|               Driver list empty, Please add a driver.                 |");
                menu();
            }
            else {
                //printing out the driver table and ask user to select a driver
                ShowDriverTable();
                integerValidation("|                   Select a driver number to delete                    |");
                TableInputValidator("|                     Please enter a valid number                       |");

                System.out.println("|              Driver " + drivers.get(value).getName() + "was deleted successfully");
                //adding the drivers team to the team name list as the team is getting free
                TeamNames.add(drivers.get(value).getTeam());
                //deleting the driver with all the data related to the driver
                drivers.remove(value);

                //asking if want to delete another driver or not and doing accordingly
                boolean valid;
                do {
                    System.out.println("|   To delete another driver enter Y/y or to return to menu enter Q/q   |");
                    System.out.print("|                             : ");
                    inputOp = sc.next();

                    if (inputOp.equals("Q") || inputOp.equals("q"))
                    {
                        again = false;
                        valid = false;
                        menu();
                    }
                    else if (inputOp.equals("Y") || inputOp.equals("y"))
                    {
                        valid = false;
                    }
                    else {
                        System.out.println("|              Input not valid please enter a valid input               |");
                        valid = true;
                    }
                } while (valid);
            }

        } while (again);


    }

    /**
     * method for changing the team of a driver
     */
    @Override
    public void ChangeTheTeam() {
        //printing out the driver table
        ShowDriverTable();

        //asking the user to select a driver
        integerValidation("|               Select a driver number to change the team               |");
        //validating the input given from the user
        TableInputValidator("|                     Please enter a valid number                       |");
        //assigning the selected team to a temporary value
        int selectedDriver = value;
        String previousTeam = drivers.get(selectedDriver).getTeam();
        //setting a default value to that intiger
        drivers.get(selectedDriver).setTeam("-");

        //printing out the teams available to change the team
        String AlignFormat = "                    | %-29s |%n";
        System.out.format("                    +-------------------------------+%n");
        System.out.format("                    |        Teams Available        |%n");
        System.out.format("                    +-------------------------------+%n");
        for (int i = 0; i < drivers.size(); i++) {
            System.out.format(AlignFormat, "["+ i +"] " + drivers.get(i).getTeam());
        }
        System.out.format("                    +-------------------------------+%n\n");
        integerValidation("|             Select a team number to change the team with              |");
        TableInputValidator("|                     Please enter a valid number                       |");
        int selectedTeam = value;

        //validating the input of the selected team
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

        //swapping the 2 teams between the selected drivers
        drivers.get(selectedDriver).setTeam(drivers.get(selectedTeam).getTeam());
        drivers.get(selectedTeam).setTeam(previousTeam);

        System.out.println("|                       Team changed successfully                       |");
    }

    /**
     * method for asking to add race data to drivers
     */
    @Override
    public void AddRace() {
        System.out.println("""
                |                                                                       |
                |                 To add a single race stats enter S/s                  |
                |            To add multiple race data to a player enter M/m            |
                |                           To Quit enter Q/q                           |""");
        System.out.print("|                             : ");
        String addType = sc.next();

        //getting the input and returning the function accordingly
        boolean raceInput = false;
        while(!raceInput) {
            if (addType.equals("S") || addType.equals("s")) {

                raceInput = true;
                //single race function called within the function to loop until wants to exit
                RunFunctionAgain(5,2, "| To add another Race enter Y/y, to go back to previous menu enter Q/q  |");
            }
            else if (addType.equals("M") || addType.equals("m"))
            {
                raceInput = true;
                //multiple race function called within the function to loop until wants to exit
                RunFunctionAgain(6,2, "|To add another Player enter Y/y, to go back to previous menu enter Q/q |");
            }
            else if (addType.equals("Q") || addType.equals("q"))
            {
                raceInput = true;
                menu();
            }
            else
            {
                //if input not valid looping through until a valid input is given
                System.out.println("|                  Input not valid enter a valid input                  |");
                System.out.print("|                             : ");
                addType = sc.next();
            }
        }
    }

    /**
     * adding a single race data to players.
     */
    public void SingleRace() {
        //displaying all the races added upto now
        String alignDates = "                    | %-29s |%n";
        System.out.format("                    +-------------------------------+%n");
        System.out.format("                    |             Dates             |%n");
        System.out.format("                    +-------------------------------+%n");

        //checking if dates object array is empty or not and printing the dates table accordingly
        if (!dates.isEmpty()) {

            for (Dates date : dates) {
                System.out.format(alignDates, date.getDate());
            }
        } else {
            System.out.format(alignDates, "Empty");
        }
        System.out.format("                    +-------------------------------+%n\n");

        //getting a user input for dates
        System.out.println("|               Enter the race date in format(dd/MM/yyyy)               |");
        System.out.print("|                             : ");
        String date = sc.next();
        //Set up date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //Validating the date as if it's a real date or not
        boolean dateValid = false;
        while (!dateValid) {
            try {
                dateFormat.parse(date);
                dateValid = true;
            }
            catch (ParseException e) {
                /* Date format is invalid */
                System.out.println("|      "+ date + " is Invalid Date format, Please enter a valid date");
                System.out.print("|                             : ");
                date = sc.next();
            }
        }
        //adding date to the dates object array
        dates.add(new Dates(date));
        int index = returnIndex(date);

        System.out.println("|-----------------------------------------------------------------------|");
        System.out.println("|   Enter the positions players has won in the race (eg: 1, 2, 3 ...)   |");
        System.out.println("|   If not participated or no position was obtained, Please enter a 0   |");
        System.out.println("|-----------------------------------------------------------------------|");

        //initializing the positions array with a default value 9999
        //positions array in the interface
        Arrays.fill(positions, 9999);

        //for each driver
        for (Formula1Driver driver : drivers) {
            //incrementing race count by 1
            driver.setNoOfRaces(1);
            //getting the user input
            integerValidation("|    Enter the position that " + driver.getName() + " obtained in the race  ");

            //setting a while loop until player enters a valid position
            boolean alreadyAdded = false;
            while (!alreadyAdded) {
                //checking if entered position in less than zero or greater than maximum no.of drivers.
                if (value < 0 || value > positions.length)
                {
                    integerValidation("|                 Please enter a position less than "+positions.length + "                  |");
                }
                else {
                    //checking if its a default initialized value or not
                    if (positions[value] == 9999 || value == 0)
                    {
                        positions[value] = 1;
                        alreadyAdded = true;
                    }
                    else {
                        integerValidation("|     Position already obtained by a Player, Please enter a another     |");
                    }
                }
            }

            //setting the data into the specific driver of the object array
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

            //assigning the drivers and the positions obtained to the race dates containing arraylists
            dates.get(index).setPartAndPos(driver.getName(),value);
        }
    }

    /**
     * adding multiple race data into a specific selected player
     */
    public void MultipleRaces(){
        //showing the driver table to select a player
        ShowDriverTable();
        //asking for the driver index and validating it
        integerValidation("|                       Select a driver to add data                       |");
        TableInputValidator("|                     Please enter a valid number                       |");

        //getting the data input from the user and assigning the values respectively to the driver
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

        //getting the no of race count participated by the driver
        integerValidation("|   Enter the no of Races participated by " + drivers.get(selectedDriver).getName() + " :");
        //validating the total no of race count input according to the no.of 1st,2nd,3rd place counts added together
        int totalRaces = place1 + place2 + place3;
        boolean validRaces = false;
        while (!validRaces)
        {
            if (value < totalRaces) {
                //asking to enter a count greater than the pos counts add together
                System.out.println("|   Total no.of Races entered doesnt match with minimum Places count    |");
                integerValidation("|                Please enter a valid Total no.of Races                 |");
            }
            else {
                validRaces = true;
            }
        }
        drivers.get(selectedDriver).setNoOfRaces(value);

        //getting the total points gathered by the driver
        integerValidation("|   Enter the Total no of Points obtained by " + drivers.get(selectedDriver).getName() + " :");
        //validating the points count input by adding up the point total by no of 1st,2nd,3rd places
        int total = place1 * noOfPoints[0] + place2 * noOfPoints[1] + place3 * noOfPoints[2];
        boolean validTotal = false;
        //looping until a value greater than is input
        while (!validTotal)
        {
            if (value < total) {
                System.out.println("|     No.of Points entered is smaller than minimum point count " + total+"      |");
                integerValidation("|                Please enter a valid Total no.of points                |");
            }
            else {
                validTotal = true;
            }
        }
        drivers.get(selectedDriver).setPoints(value);

        System.out.println("|                        Data added successfully                        |");
    }

    /**
     * Displaying a single drivers data
     */
    public void DisplayDriverStat(){
        //displaying all the drivers added
        String alignDrivers = "                    | %-29s |%n";
        System.out.format("                    +-------------------------------+%n");
        System.out.format("                    |             Drivers           |%n");
        System.out.format("                    +-------------------------------+%n");
        for (int i = 0; i < drivers.size(); i++) {
            System.out.format(alignDrivers, "["+ i +"] " + drivers.get(i).getName());
        }
        //asking for a driver index and validating it
        integerValidation("|             Please select a Player to Display Statistics              |");
        TableInputValidator("|                     Please enter a valid number                       |");

        //setting the table format to print driver details
        String AlignFormat = "          | %-21s : %-25s |%n";
        String format2 = "          | %-49s |%n";
        System.out.format("          +-----------------------+---------------------------+%n");
        System.out.format(format2, "                   "+ drivers.get(value).getName());
        System.out.format("          +-----------------------+---------------------------+%n");

        //assigning the table reference data into an array
        String[] table = {"Team", "Country", "Age", "Points", "Podiums", "    1st Pos", "    2nd Pos", "    3rd Pos", "Races Entered"};
        //getting the no of places count and adding up to the podium count
        int noOf1 = drivers.get(value).getFirstPlace();
        int noOf2 = drivers.get(value).getSecondPlace();
        int noOf3 = drivers.get(value).getThirdPlace();
        int podiums = noOf1 + noOf2 + noOf3;
        //adding the player data into a string array
        String[] tableData = {drivers.get(value).getTeam(), drivers.get(value).getLocation(), String.valueOf(drivers.get(value).getAge()), String.valueOf(drivers.get(value).getPoints())
                , String.valueOf(podiums),String.valueOf(noOf1),String.valueOf(noOf2),String.valueOf(noOf3), String.valueOf(drivers.get(value).getNoOfRaces())};

        //printing out the two arrays in the table format
        for (int i = 0; i < table.length; i++){
            System.out.format(AlignFormat,table[i], tableData[i]);
        }
        System.out.format("          +-----------------------+---------------------------+%n");

        //telling that null values are present due to having less data added into the player
        if (drivers.get(value).getNoOfRaces() < 1)
        {
            System.out.println("""
                    |       NOTE : Player race data hasn't been added to this player.       |
                    |               To view player statistics in more detail,               |
                    |                  Please add race data to the player.                  |
                    """);
        }
    }

    /**
     * printing all the player data
     */
    public void F1DriverTable() {
        //checking if any player has missing data slot
        if (RaceAdded())
        {
            //sorting the players according to the no.of points and 1st places
            Collections.sort(drivers);

            //setting up the table format
            String tableFormat = "|  %-3s|      %-15s      |     %-3S     |   %-15s   | %-4s  |%n";
            System.out.format("+-----+---------------------------+-------------+---------------------+-------+%n");
            System.out.format("| POS |          Driver           | NATIONALITY |         CAR         |  PTS  |%n");
            System.out.format("+-----+---------------------------+-------------+---------------------+-------+%n");
            //printing the players and there data by an for loop
            for (int i = 0; i < drivers.size(); i++){
                System.out.format(tableFormat, i + 1, drivers.get(i).getName(), drivers.get(i).getLocation(),drivers.get(i).getTeam(), drivers.get(i).getPoints());
            }
            System.out.format("+-----+---------------------------+-------------+---------------------+-------+%n\n");

            //getting a user input to return to the menu rather than exiting right away
            System.out.println("|                To return to main menu enter any value                 |");
            System.out.print("                             : ");
            String back = sc.next();
            if (!back.equals("99999999999"))
            {
                menu();
            }
        }
        else {
            System.out.println("|      Please add race data to drivers to view the F1 Driver Table      |");
        }
        menu();
    }

    /**
     * loading the saved data into the array on opening
     */
    private static void Save(){
        //using try catch to catch any exception
        try {
            System.out.println("|                      Loading saved data....                      |");
            //loading data from the saved file as a new object output stream
            ObjectInputStream LoadFile = new ObjectInputStream(new FileInputStream("F1Data.txt"));

            //first clearing the arraylists and assigning the object saved into the respective object array
            drivers.clear();
            drivers = (ArrayList<Formula1Driver>) LoadFile.readObject();
            dates.clear();
            dates = (ArrayList<Dates>) LoadFile.readObject();
            TeamNames.clear();
            TeamNames = (ArrayList<String>) LoadFile.readObject();

            LoadFile.close();
        } catch (IOException | ClassNotFoundException e) {
            //if any saved files cannot be found giving a error massage
            System.out.println("|       You don't have any files to Load. Please Save a file       |");
            //e.printStackTrace();
        }
    }

    /**
     * saving all the data into a file
     */
    private void exit() {
        try {
            //creating a new data file and storing data into it as a object output stream
            ObjectOutputStream SaveFile = new ObjectOutputStream(new FileOutputStream("F1Data.txt"));    //referred from https://www.programiz.com/java-programming/objectoutputstream
                                                                         //referred from https://stackoverflow.com/questions/27787067/storing-integers-and-arrays-in-a-file-and-reading-them
            SaveFile.writeObject(drivers);
            SaveFile.writeObject(dates);
            SaveFile.writeObject(TeamNames);
            //saving the object arrays as object

            //data is written to the file and closed
            SaveFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("                     ▀█▀.█▄█.█▀█.█▄.█.█▄▀　█▄█.█▀█.█─█                    \n" +
                "                    ─.█.─█▀█.█▀█.█.▀█.█▀▄　─█.─█▄█.█▄█                    ");

        //calling the exit method from the swing gui, because the program only sets the visibility to false on closing
        //therefore the program doesn't close properly and this function will call the exit code
        SwingGUI.exitCompletely();
    }

    /**
     * returning race data added or not boolean value
     * @return - boolean
     */
    public boolean RaceAdded () {
        //checking if any driver has less than 1, if so no data is added to that driver.
        //returning true if data is added to driver and false if not
        boolean result = false;
        for (Formula1Driver driver : drivers) {
            result = driver.getNoOfRaces() >= 1;
        }
        return result;
    }

    /**
     * printing the drivers with teams using a table format
     */
    public void ShowDriverTable() {
        String AlignFormat = "              | %-2s] %-17s | %-16s |%n";
        System.out.format("              +-----------------------+------------------+%n");
        System.out.format("              |         Driver        |    Driver Team   |%n");
        System.out.format("              +-----------------------+------------------+%n");
        for (int i = 0; i < drivers.size(); i++){
            System.out.format(AlignFormat,"["+ i,drivers.get(i).getName(), drivers.get(i).getTeam());
        }
        System.out.format("              +-----------------------+------------------+%n");
    }

    /**
     * returning the index pos of the specific value passed in the dates arraylist
     * @param compDate - the date to compare
     * @return - returning index
     */
    public static int returnIndex(String compDate) {
        int valueIndex = 0;
        for (Dates date : dates) {
            if (date.getDate().equals(compDate)){
                valueIndex = dates.indexOf(date);
            }
        }
        return valueIndex;
    }

    /**
     * retuning true or false if the driver name is existing in the array or not
     * @param compName - the name to compare
     * @return - is present or not
     */
    public static boolean returnYorN(String compName) {
        boolean result = false;
        for (int i = 0; i < drivers.size(); i++)
        {
            if (drivers.get(i).getName().equalsIgnoreCase(compName))
            {
                result = true;
            }
        }
        return result;
    }

    /**
     * printing the menu when the swing gui is getting closed
     */
    public static void exitApplication(){
        F12.menu();
    }

    /////////////common Validation functions/////////////////////////

    /**
     * validating the player index given by the user according to the printed table
     * @param sentence - user instructions for validation
     */
    public void TableInputValidator(String sentence) {
        boolean validDriverNumber = false;
        while (!validDriverNumber)
        {
            if (value > drivers.size() || value < 0){
                //checking if an integer or not
                integerValidation(sentence);
            }
            else {
                validDriverNumber = true;
            }
        }
    }

    /**
     * validating if the value asked is a integer or not
     * @param test - user instructions on what to enter
     */
    public void integerValidation(String test) {
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
