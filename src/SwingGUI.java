import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class SwingGUI{
    //creating the main frame
    JFrame j = new JFrame();

    //creating a new arraylist from the main driver's arraylist from Formula1ChampionshipManager
    public static ArrayList<Formula1Driver> newDrivers = Formula1ChampionshipManager.getList();
    public static ArrayList<Dates> newDates = Formula1ChampionshipManager.getDatesList();
    //creating all the tables
    JTable table = new JTable();
    JTable table1 = new JTable();
    JTable table2 = new JTable();
    JTable table3 = new JTable();
    JTable table4 = new JTable();

    private static JDialog dialogBox;

    public static int winnerIndex;

    /**
     * main method called for running all functions
     */
    public void GuiMain() {
        j.setIconImage(new ImageIcon(getClass().getResource("/images/Layer1.png")).getImage());
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                //adding data to the table when starting the gui
                addToTable();

                //setting a layout structure and size for the gui
                j.setLayout(new BorderLayout());
                j.setSize(1000, 600);

                //function where all the elements are placed
                panelConfig();
                //setting to be always on top when invoking the method
                j.setAlwaysOnTop (true);

                //for making the frame always stay in the middle
                j.setLocationRelativeTo(null);
                //setting the visibility of the frame to true
                j.setVisible(true);

                //getting the listener event for pressing the closing button in the frame
                j.addWindowListener(new WindowAdapter() {                                                                 //https://stackoverflow.com/questions/16372241/run-function-on-jframe-close
                    public void windowClosing(WindowEvent e) {
                        //printing exiting the application and setting the visibility to false
                        System.out.println("|                  Exiting the application..........                    |");
                        j.setVisible(false);
                        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        //dispose();

                        //calling the exit application to print the menu in the console
                        Formula1ChampionshipManager.exitApplication();
                    }
                });
            }
        });
    }
    //closing the gui completely when exiting the program on console
    public static void exitCompletely(){
        System.exit(0);
    }

    /**
     * adds the main passed data into the main table in the gui
     */
    public void addToTable() {
        //adding the drivers to the table
        //setting up the table model and assigning the table header topics
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Age","Team", "Country", "Race Count", "1st pos", "2nd Pos", "3rd Pos", "Points"}));

        //iterating through the arraylist and adding data to the table accordingly to the columns
        for (Formula1Driver driver : newDrivers) {
            ((DefaultTableModel) table.getModel()).addRow(new Object[]{
                    driver.getName(), driver.getAge(), driver.getTeam(),
                    driver.getLocation(), driver.getNoOfRaces(), driver.getFirstPlace(), driver.getSecondPlace(), driver.getThirdPlace(), driver.getPoints()});
        }

        //modifying the table by colors and text modifications
        tableModify(table);
    }

    /**
     * adding data to dates sorted table
     * @param datesArray - array of race dates held in the season
     * @param newDates - dates class array to get no of players participated
     */
    public void DateTable(ArrayList<String> datesArray, ArrayList<Dates> newDates) { //adding the drivers to the table

        table1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Race Dates", "Player Count"}));

        for (int i = 0; i < datesArray.size(); i++) {
            ((DefaultTableModel) table1.getModel()).addRow(new Object[]{
                    datesArray.get(i), newDates.get(i).getParticipated().size()});
        }
        tableModify(table1);
    }

    /**
     * adding race data to the table where when a player is searched
     * @param resDate - races in which the player has participated
     * @param pos - positions obtained by the player in that specific race
     */
    public void playerDataTable(ArrayList<String> resDate, ArrayList<Integer> pos) { //adding the drivers to the table

        table2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Races", "Positions Obtained"}));

        for (int i = 0; i < resDate.size(); i++) {
                ((DefaultTableModel) table2.getModel()).addRow(new Object[]{
                        resDate.get(i), pos.get(i)});
        }
        tableModify(table2);
    }

    /**
     * adding data with the generated race data
     * @param participated - all the drivers participated in the race
     * @param pos - wining pos of the drivers respectively
     */
    public void generatedRaceTable(ArrayList<String> participated, ArrayList<Integer> pos) { //adding the drivers to the table

        table3.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Driver Name", "Position Obtained"}));

        for (int i = 0; i < participated.size(); i++) {
            ((DefaultTableModel) table3.getModel()).addRow(new Object[]{
                    participated.get(i), pos.get(i)});
        }
        tableModify(table3);
    }

    /**
     * adding data with the generated race data with the starting positions of the players
     * @param participated - all the drivers participated
     * @param startPos - starting position of the drivers
     * @param winPos - winning pos of the drivers
     */
    public void generatedRacePosTable(ArrayList<String> participated, ArrayList<Integer> startPos, ArrayList<Integer> winPos){ //adding the drivers to the table

        table4.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Driver Name","Starting Position", "Wining Position"}));

        for (int i = 0; i < participated.size(); i++) {
            ((DefaultTableModel) table4.getModel()).addRow(new Object[]{
                    participated.get(i), startPos.get(i), winPos.get(i)});
        }
        tableModify(table4);
    }

    /**
     * setts all the layouts, buttons, icons and panel configurations
     */
    public void panelConfig(){
        //top bar//////////////////////////////////////////////////////

        //top panel containing welcome and search bar
        JPanel panelTop = new JPanel(new GridLayout(1,4,20,10));
        //adding boarders to the top panel
        panelTop.setBorder(new EmptyBorder(20,10,10,10));
        j.add(panelTop, BorderLayout.PAGE_START);
        //for putting the search option elements
        JPanel searchPanel = new JPanel(new BorderLayout());

        //getting all the images needed for the gui
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/Layer1.png")).getImage().getScaledInstance(100, 40, Image.SCALE_DEFAULT));
        Image image = new ImageIcon(getClass().getResource("/images/search.png")).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        Image imageAsc = new ImageIcon(getClass().getResource("/images/ascenWhite.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        Image imageDesc = new ImageIcon(getClass().getResource("/images/descWhite.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        Image imageGenerate = new ImageIcon(getClass().getResource("/images/RandomGen.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        Image imageGeneratePos = new ImageIcon(getClass().getResource("/images/RandomGenPos.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);

        //setting up the top bar icon F1 and welcome
        JLabel icon = new JLabel();
        icon.setIcon(imageIcon);
        JLabel welcomeLabel = new JLabel("WELCOME TO F1");
        welcomeLabel.setFont(new Font("Raleway", Font.PLAIN,30));

        //setting up the search button and icon to it
        JButton BtnSearch = new JButton();
        BtnSearch.setPreferredSize(new Dimension(40,40));
        BtnSearch.setIcon(new ImageIcon(image));

        TextField textInput = new TextField("text");
        searchPanel.add(textInput, BorderLayout.CENTER);
        searchPanel.add(BtnSearch, BorderLayout.LINE_END);

        //adding them to the panel
        panelTop.add(icon);
        panelTop.add(welcomeLabel);
        panelTop.add(searchPanel);

        //decorating the panel
        panelTop.setBackground(Color.decode("#454545"));
        welcomeLabel.setForeground(Color.WHITE);
        searchPanel.setBackground(Color.decode("#454545"));

        //Main tab pane //////////////////////////////////////////////////////////////
        //creating scroll panes for the two tabs
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //creating the two panels for the tabs
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        //creating a panel with grid layout to put the buttons in page end of panel 1
        JPanel panelLeft = new JPanel(new GridLayout(5,1, 10, 10));
        panelLeft.setBorder(new EmptyBorder(10,10,10,10));
        panelLeft.setBackground(Color.decode("#454545"));

        //creating the main tab pane and aligning it in the center
        JTabbedPane tabPane =new JTabbedPane();
        j.add(tabPane, BorderLayout.CENTER);
        tabPane.add("F1DriverTable",panel1);
        tabPane.add("Race Data",panel2);

        //customizing the panels
        panel1.setBackground(Color.decode("#454545"));
        panel2.setBackground(Color.decode("#454545"));
        panelLeft.setBackground(Color.decode("#454545"));
        tabPane.setBackground(Color.decode("#454545"));
        scrollPane.setBackground(Color.decode("#454545"));

        //creating buttons and fields for tab 1 //////////////////////////////////////////////////////
        JButton btn1 = new JButton("Points");
        JButton btn2 = new JButton("Points");
        JButton btn3 = new JButton("1st Pos");
        JButton btn4 = new JButton("Race");
        JButton btn5 = new JButton("Race/Pos");

        //setting up sizes, customization and icons for the buttons
        btn1.setPreferredSize(new Dimension(70,160));
        btn1.setIcon(new ImageIcon(imageAsc));
        setBtnColour(btn1);
        btn2.setPreferredSize(new Dimension(60,60));
        btn2.setIcon(new ImageIcon(imageDesc));
        setBtnColour(btn2);
        btn3.setPreferredSize(new Dimension(60,60));
        btn3.setIcon(new ImageIcon(imageAsc));
        setBtnColour(btn3);
        btn4.setPreferredSize(new Dimension(60,60));
        btn4.setIcon(new ImageIcon(imageGenerate));
        setBtnColour(btn4);
        btn5.setPreferredSize(new Dimension(60,60));
        btn5.setIcon(new ImageIcon(imageGeneratePos));
        setBtnColour(btn5);

        //assigning layouts and panels to the 1st tab
        panel1.setLayout(new BorderLayout());
        panel1.add(scrollPane, BorderLayout.CENTER);
        panel1.add(panelLeft, BorderLayout.LINE_START);

        //assigning buttons and fields to tab1
        panelLeft.add(btn1, BorderLayout.LINE_START);
        panelLeft.add(btn2, BorderLayout.LINE_START);
        panelLeft.add(btn3, BorderLayout.LINE_START);
        panelLeft.add(btn4, BorderLayout.LINE_START);
        panelLeft.add(btn5, BorderLayout.LINE_START);

        //assigning layouts and panels to the 2nd tab////////////////////////////////////////////////
        JScrollPane scrollPane1 = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JButton tab2BtnSort = new JButton("Sort Races");

        //setting up the layout positions for the sorting tab
        panel2.setLayout(new BorderLayout());
        panel2.add(scrollPane1, BorderLayout.CENTER);
        panel2.add(tab2BtnSort, BorderLayout.PAGE_START);

        tab2BtnSort.setBackground(new Color(255, 255, 255, 80));

        //adding action listeners to the buttons respectively
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checking if data is added or not before sorting the drivers by points
                if (raceDone())
                {
                    SortPointsDescending();
                }
                else {
                    showMessageDialog(j, "Please add Race data to drivers or generate a race to sort", "Error", ERROR_MESSAGE);
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checking if data is added or not before sorting the drivers by points
                if (raceDone())
                {
                    SortPointsAscending();
                }
                else {
                    showMessageDialog(j, "Please add Race data to drivers or generate a race to sort", "Error", ERROR_MESSAGE);
                }
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checking if data is added or not before sorting the drivers by positions
                if (raceDone())
                {
                    SortPosition();
                }
                else {
                    showMessageDialog(j, "Please add Race data to drivers or generate a race to sort", "Error", ERROR_MESSAGE);
                }
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //generating a random race
                GenerateRandomRace(1);
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //showing a warning message to get more accuracy in probability by adding 10 players at least
                if (newDrivers.size() < 10){
                    showMessageDialog(j, "Add at least 10 players for more accuracy", "Warning", JOptionPane.WARNING_MESSAGE);
                    GenerateRandomRace(2);
                }
                else {
                    GenerateRandomRace(2);
                }
            }
        });
        tab2BtnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sorting the dates
                SortDates();
            }
        });
        BtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //search player by the text field input
                SearchPlayer(textInput.getText().toLowerCase());
            }
        });
    }

    /**
     * sorting the player's data by points in descending order and adding data to the table
     */
    public void SortPointsDescending(){
        Collections.sort(newDrivers, Formula1Driver.PointsDescending);
        addToTable();
        table.repaint();
    }

    /**
     * sorting the player data by points in ascending order and updating the data in the same table
     */
    public void SortPointsAscending(){
        Collections.sort(newDrivers, Formula1Driver.PointsAscending);
        addToTable();
        table.repaint();
    }

    /**
     * sorting the player data by positions in descending order and updating the data in the same table
     */
    public void SortPosition(){
        Collections.sort(newDrivers, Formula1Driver.PositionComparator);
        addToTable();
        table.repaint();
    }

    /**
     * generating random unique positions
     * @param function - which random generate function to run is passed by this
     */
    public void GenerateRandomRace(int function) {
        //creating arraylists to store wining pos of random generated races and starting pos of the ones with starting pos
        ArrayList<Integer> randomList = new ArrayList<Integer>();
        //creating arraylists to store wining pos of random generated races with starting pos
        ArrayList<Integer> restWiningPos = new ArrayList<Integer>();
        //assigning different values to the two functions generate random race and same with probability
        int totalPos;
        int min;
        //function 1 calls the random generated race function
        if (function == 1){
            totalPos = ChampionshipManager.positions.length;
            min = 0;
        }
        else {
            //this calls the one with starting position
            totalPos = newDrivers.size();
            min = 1;

            //assigning wining positions for the pos 2 - 10
            for(int i = 0; i < newDrivers.size() -1; i++)
            {
                //as 1 st position is generated by probability, here pos 2-10 are generated
                int rand1 = randomValue(newDrivers.size(), 2);
                //checking if the same pos is generated and if not only adding them to the array
                boolean randomWin = false;
                while (!randomWin)
                {
                    if (!restWiningPos.contains(rand1)) {
                        restWiningPos.add(rand1);
                        randomWin = true;
                    }
                    else {
                        rand1 = randomValue(newDrivers.size(), 2);
                    }
                }
            }
        }

        //generating the random array values to wining pos for generating random race
        for(int i = 0; i < newDrivers.size(); i++)
        {
            int rand = randomValue(totalPos, min);
            boolean randomBoo = false;
            while (!randomBoo)
            {
                if (!randomList.contains(rand)) {
                    randomList.add(rand);
                    randomBoo = true;
                }
                else {
                    rand = randomValue(totalPos, min);
                }
            }
        }

        //generating random dates
        int year = randomValue(2021,2020);
        int month = randomValue(12,1);
        int date = randomValue(27,1);

        String randomDate = date +"/" + month +"/"+ year;

        //Set up date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Validating the date as if its a real date or not
        boolean dateValid = false;
        while (!dateValid) {
            try {
                dateFormat.parse(randomDate);
                dateValid = true;
            }
            //invalid date format therefore generating another date
            catch (ParseException e) {
                year = randomValue(2021,2020);
                month = randomValue(12,1);
                date = randomValue(27,1);
                randomDate = date +"/" + month +"/"+ year;
            }
        }
        //assigning the random date to the dates object array
        newDates.add(new Dates(randomDate));
        //getting the index of the arraylist of the date
        int index = Formula1ChampionshipManager.returnIndex(randomDate);

        if (function == 1)
        {
            //for the generated random race
            //adding points generated to the respective index of dates array
            addPoints(randomList,index);
            //adding values to the table
            generatedRaceTable(newDates.get(index).getParticipated(), newDates.get(index).getPosition());
            //setting up the dialog box with the respective table and names
            dialogBoxPop("Generated race details", "Random race data :"+randomDate, table3);
        }
        else
        {
            //for generate random race with pos
            //1st generating a random wining pos from pos 10 to 1
            int randomWinPos = randomValue(10, 1);

            //checking 40% by checking if the generated value is between 1 - 4 then assigning the
            //player in starting position 1 as the winner
            if (randomWinPos >= 1 && randomWinPos <= 4){
                //updating the winners index so the respective player can be found
                updateWinIndex(randomList, 1);
            }
            //checking 30% by checking if the generated value is between 5 - 7 then assigning the
            //player in starting position 2 as the winner
            if (randomWinPos >= 5 && randomWinPos <= 7){
                updateWinIndex(randomList, 2);
            }
            //checking two 10% by checking if the generated value is between 8 - 9 then assigning the
            //player in starting position 3 or 4 as the winner
            if (randomWinPos == 8 || randomWinPos == 9){
                //player 3 or 4 is chosen randomly
                int rand10Percent = randomValue(4,3);
                updateWinIndex(randomList, rand10Percent);
            }
            //checking rest 5 positions 2% by checking if the generated value is 10 then assigning the
            //player in starting position 5 to 9 as the winner
            if (randomWinPos == 10){
                //winner is chosen randomly from pos 5 to 9
                int rand2Percent = randomValue(9,5);
                updateWinIndex(randomList, rand2Percent);
            }
            //assigning the arraylist the missing value of the winner so the length of the arraylist drivers and restWiningPos will be same
            restWiningPos.add(winnerIndex, 1);
            //adding the data accordingly passing the arrays
            addPoints(restWiningPos, index);

            //generating the table containing all the generated data in generating race with start pos
            generatedRacePosTable(newDates.get(index).getParticipated(), randomList, newDates.get(index).getPosition());
            //passing the values to the method dialogBoxPop to create a dialog box with the data table
            dialogBoxPop("Generated race with positions details", "Random race date :"+randomDate, table4);
        }
        //adding data to the main table
        addToTable();
    }

    /**
     * updating the winnerIndex
     * @param list - passing the arraylist to check from
     * @param pos - index pos needed to find
     */
    public void updateWinIndex(ArrayList<Integer> list, int pos){
        for (int i = 0; i < newDrivers.size(); i++){
            if (list.get(i) == pos)
            {
                winnerIndex = i;
            }
        }
    }

    /**
     * adding points respective to the player position
     * @param list - winning positions of the drivers
     * @param index - which index to add data
     */
    public void addPoints(ArrayList<Integer> list, int index){
        for (int i = 0; i < newDrivers.size(); i++){
            //increasing the no of races participated
            newDrivers.get(i).setNoOfRaces(1);
            //increasing the 1st 2nd 3rd pos and points accordingly to the position obtained
            if (list.get(i) == 1) {
                newDrivers.get(i).setFirstPlace(1);
                newDrivers.get(i).setPoints(ChampionshipManager.noOfPoints[0]);
            }else if (list.get(i) == 2) {
                newDrivers.get(i).setSecondPlace(1);
                newDrivers.get(i).setPoints(ChampionshipManager.noOfPoints[1]);
            } else if (list.get(i) == 3) {
                newDrivers.get(i).setThirdPlace(1);
                newDrivers.get(i).setPoints(ChampionshipManager.noOfPoints[2]);
            } else if (list.get(i) > 3 && list.get(i) <= 9) {
                newDrivers.get(i).setPoints(ChampionshipManager.noOfPoints[list.get(i) - 1]);
            }
            //adding dates class the players participated and positions obtained
            newDates.get(index).setPartAndPos(newDrivers.get(i).getName(), list.get(i));
        }
    }

    /**
     * sorting the dates on click
     */
    public void SortDates() {
        //creating a new arraylist to store the dates
        ArrayList<String> datesAll = new ArrayList<>();

        //assigning the dates to datesAll arraylist
        for (Dates newDate : newDates) {
            datesAll.add(newDate.getDate());
        }
        //Sorting the datesAll arraylist as newDate arraylist cannot be sorted as it also contains arraylists
        Collections.sort(datesAll, new Comparator<String>() {
            DateFormat formatCheckQ = new SimpleDateFormat("dd/MM/yyyy");
            @Override
            public int compare(String o1, String o2) {
                try {
                    return formatCheckQ.parse(o1).compareTo(formatCheckQ.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        //updating the table passing the sorted dates containing array
        DateTable(datesAll, newDates);
    }

    /**
     * searching the respective players data and displaying
     * @param name - text field input name of the player
     */
    public void SearchPlayer(String name){
        //creating 2 new arraylist to store the dates and pos of the respective player
        ArrayList<String> datesParti = new ArrayList<>();
        ArrayList<Integer> posObtained = new ArrayList<>();

        //checking if the entered string input is valid or not
        if (returnYorNs(name))
        {
            //checking for every index in newDates arraylist
            for (Dates newDate : newDates) {
                //creating a temporary arraylist to store data of the participated arraylist inside the dates class for that specific index
                ArrayList<String> test = newDate.getParticipated();
                for (String s : test) {
                    //checking if the player has participated in the race held in that date
                    if (s.equalsIgnoreCase(name))
                    {
                        //getting the index of the player in that race
                        int index = test.indexOf(s);
                        //assigning the 2 arraylists created above with the data
                        datesParti.add(newDate.getDate());
                        posObtained.add(newDate.getPosition().get(index));
                    }
                }
            }
            //populating the table with the 2 arraylists created above
            playerDataTable(datesParti, posObtained);
            //creating a dialog box with the table and the heading
            dialogBoxPop("Player", "Details of Player "+name+" :", table2);
        }
        else {
            //showing an error message if not found
            showMessageDialog(j, "No Player Found, Please enter a valid player", "Error", ERROR_MESSAGE);
        }
    }

    /**
     * @param compareName - name to be compared with
     * @return - returning boolean by checking of the passed name is in the arraylist
     */
    public boolean returnYorNs(String compareName) {
        int count = 0;
        boolean result = false;
        for (int i = 0; i < newDrivers.size(); i++)
        {
            if (newDrivers.get(i).getName().equalsIgnoreCase(compareName))
            {
                count++;
            }
        }
        if (count == 1)
        {
            result = true;
        }
        return result;
    }

    /**
     * @param max - maximum value to generate random value
     * @param min - minimum value to be generated randomly
     * @return - generated random value between the passed max and min value
     */
    public int randomValue(int max, int min) {
        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /**
     * @return - checking if any race data is added to the system and returning a boolean accordingly
     */
    public boolean raceDone() {
        boolean result = false;
        for (int i = 0; i < newDrivers.size(); i++)
        {
            if (newDrivers.get(i).getNoOfRaces() < 1){
                result = false;
            }
            else{
                result = true;
            }
        }
        return result;
    }

    /**
     * method for creating a dialog box pop up
     * @param title - title  to be displayed in the dialog box
     * @param heading - heading to be displayed in the dialog box
     * @param table - table to be displayed in the dialog box
     */
    public void dialogBoxPop(String title, String heading, JTable table) {                         //https://www.javatpoint.com/java-jdialog
        JFrame f = new JFrame();
        //setting up a new frame and setting up the positions
        f.setLocationRelativeTo(null);
        j.setAlwaysOnTop(false);
        //setting up the dialog box with the title
        dialogBox = new JDialog(f, title, true);
        //setting a layout
        dialogBox.setLayout(new BorderLayout());
        //creating the scroll pane and the main button
        JButton close = new JButton("Go Back");
        JScrollPane scrollPane2 = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //setting visibility to false when button is clicked
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogBox.setVisible(false);
            }
        });

        //setting up the elements in positions
        dialogBox.add(new JLabel(heading), BorderLayout.PAGE_START);
        dialogBox.add(close, BorderLayout.PAGE_END);
        dialogBox.add(scrollPane2, BorderLayout.CENTER);

        dialogBox.setSize(500, 500);
        dialogBox.setVisible(true);
    }

    /**
     * method for beautifying the tables
     * @param table - table to be modified
     */
    public void tableModify(JTable table){
        //getting the table header and setting up the color
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.BLACK);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Tahoma", Font.BOLD,13));
        //setting up the table background color
        table.setBackground(Color.decode("#505050"));
        table.setForeground(Color.WHITE);
        table.setOpaque(true);
        table.setFillsViewportHeight(true);
    }

    /**
     * method for decorading the buttons
     * @param btn - button to be modified
     */
    public void setBtnColour(JButton btn) {
        //setting opacity to false
        btn.setOpaque(false);
        //no boarder
        btn.setBorderPainted(false);
        //button image and text positioning
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setForeground(Color.WHITE);
    }
}

