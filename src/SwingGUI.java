import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SwingGUI extends JFrame{

    public static ArrayList<Formula1Driver> newDrivers = Formula1ChampionshipManager.getList();
    public static ArrayList<Dates> newDates = Formula1ChampionshipManager.getDatesList();
    JTable table = new JTable();
    JTable table1 = new JTable();
    JTable table2 = new JTable();

    JLabel randomRaceLabel = new JLabel();
    JLabel randomRaceLabel1 = new JLabel();
    JLabel randomRaceLabel2 = new JLabel();
    JLabel randomRaceStat = new JLabel();
    JLabel tab2SearchLabel = new JLabel();

    public static int winnerIndex;

    public void GuiMain()
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                addToTable();
                //DateTable();

                setLayout(new BorderLayout());
                setSize(800, 600);

                panelConfig();

                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
                setVisible(true);

            }
        });
    }
    public void addToTable() { //adding the drivers to the table

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Age","Team", "Country", "Race Count", "1st pos", "2nd Pos", "3rd Pos", "Points"}));

        for (Formula1Driver driver : newDrivers) {
            ((DefaultTableModel) table.getModel()).addRow(new Object[]{
                    driver.getName(), driver.getAge(), driver.getTeam(),
                    driver.getLocation(), driver.getNoOfRaces(), driver.getFirstPlace(), driver.getSecondPlace(), driver.getThirdPlace(), driver.getPoints()});
        }
    }

    public void DateTable(ArrayList<String> datesArray) { //adding the drivers to the table

        table1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"RACE DATES", "count"}));

        for (String date : datesArray) {
            ((DefaultTableModel) table1.getModel()).addRow(new Object[]{
                    date});
        }
    }
    public void playerData(ArrayList<String> resDate, ArrayList<Integer> pos) { //adding the drivers to the table

        table2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Races", "Positions Obtained"}));

        for (int i = 0; i < resDate.size(); i++) {
                ((DefaultTableModel) table2.getModel()).addRow(new Object[]{
                        resDate.get(i), pos.get(i)});
        }
    }

    public void panelConfig(){
        JLabel welcomeLabel = new JLabel("Welcome");
        add(welcomeLabel, BorderLayout.PAGE_START);
        //creating scroll panes for the two tabs
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //creating the two panels for the tabs
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        //creating a panel with grid layout to put the buttons in page end of panel 1
        JPanel panelLeft = new JPanel(new GridLayout(5,1, 10, 10));
        JPanel panelBottomData = new JPanel(new GridLayout(1,4, 10, 10));

        //creating the main tab pane and aligning it in the center
        JTabbedPane tabPane =new JTabbedPane();
        add(tabPane, BorderLayout.CENTER);
        tabPane.add("F1DriverTable",panel1);
        tabPane.add("Race Data",panel2);

        //creating buttons and fields for tab 1 //////////////////////////////////////////////////////
        JTextArea textField1 = new JTextArea();

        JButton btn1 = new JButton("Sort By Name");
        JButton btn2 = new JButton("Sort By Points");
        JButton btn3 = new JButton("Sort By No.of 1st Positions");
        JButton btn4 = new JButton("Generate a Random Race");
        JButton btn5 = new JButton("Generate a Random Race with positions");

        //assigning layouts and panels to the 1st tab
        panel1.setLayout(new BorderLayout());
        panel1.add(scrollPane, BorderLayout.CENTER);
        panel1.add(panelLeft, BorderLayout.LINE_START);
        panel1.add(panelBottomData, BorderLayout.PAGE_END);

        //assigning buttons and fields to tab1
        panelLeft.add(btn1, BorderLayout.LINE_START);
        panelLeft.add(btn2, BorderLayout.LINE_START);
        panelLeft.add(btn3, BorderLayout.LINE_START);
        panelLeft.add(btn4, BorderLayout.LINE_START);
        panelLeft.add(btn5, BorderLayout.LINE_START);
        panel1.add(textField1, BorderLayout.PAGE_START);

        panelBottomData.add(randomRaceStat);
        panelBottomData.add(randomRaceLabel);
        panelBottomData.add(randomRaceLabel1);
        panelBottomData.add(randomRaceLabel2);


        //assigning layouts and panels to the 2nd tab////////////////////////////////////////////////
        JScrollPane scrollPane1 = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JScrollPane scrollPane2 = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPanel sortPanel = new JPanel();
        JPanel searchPanel = new JPanel(new BorderLayout());

        JButton tab2BtnSort = new JButton("Sort Races");
        JButton tab2BtnSearch = new JButton("Search");
        TextField textInput = new TextField("text");

        panel2.setLayout(new BorderLayout());
        panel2.add(scrollPane2, BorderLayout.LINE_END);
        panel2.add(scrollPane1, BorderLayout.LINE_START);
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        panel2.add(topPanel, BorderLayout.PAGE_START);
        topPanel.add(sortPanel);
        topPanel.add(searchPanel);

        sortPanel.add(tab2BtnSort);
        searchPanel.add(textInput, BorderLayout.CENTER);
        searchPanel.add(tab2BtnSearch, BorderLayout.LINE_END);
        panel2.add(tab2SearchLabel, BorderLayout.PAGE_END);

        //adding action listeners to the buttons respectively
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (raceDone())
                {
                    SortPointsDescending();
                    textField1.append("Welcome");
                }
                else {
                    textField1.append("Please add Race data to drivers or generate a race to sort");
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (raceDone())
                {
                    SortPointsAscending();
                    textField1.append("Welcome");
                }
                else {
                    textField1.append("Please add Race data to drivers or generate a race to sort");
                }
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (raceDone())
                {
                    SortPosition();
                }
                else {
                    textField1.append("Please add Race data to drivers or generate a race to sort");
                }
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateRandomRace(1);
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateRandomRace(2);
            }
        });
        tab2BtnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SortDates();
            }
        });
        tab2BtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchPlayer(textInput.getText().toLowerCase());
            }
        });
    }

    public void SortPointsDescending(){
        Collections.sort(newDrivers, Formula1Driver.PointsDescending);
        addToTable();
        table.repaint();
    }
    public void SortPointsAscending(){
        Collections.sort(newDrivers, Formula1Driver.PointsAscending);
        addToTable();
        table.repaint();
    }
    public void SortPosition(){
        Collections.sort(newDrivers, Formula1Driver.PositionComparator);
        addToTable();
        table.repaint();
    }
    public void GenerateRandomRace(int function) {
        //generating random unique positions
        ArrayList<Integer> randomList = new ArrayList<Integer>();
        ArrayList<Integer> restWiningPos = new ArrayList<Integer>();
        //assigning different values to the two functions generate random race and same with probability
        int totalPos;
        int min;
        if (function == 1){
            totalPos = ChampionshipManager.positions.length;
            min = 0;
        }
        else {
            totalPos = newDrivers.size();
            min = 1;

//            int length = newDrivers.size() -1;
            for(int i = 0; i < newDrivers.size() -1; i++)
            {
                int rand1 = randomValue(newDrivers.size(), 2);
                boolean randomWin = false;
                while (!randomWin)
                {
                    if (!restWiningPos.contains(rand1)) {
                        restWiningPos.add(rand1);
                        randomWin = true;
                    }
                    else {
                        rand1 = randomValue(newDrivers.size(), 2);
                        randomWin = false;
                    }
                }
            }
        }

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
                    randomBoo = false;
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
            addPoints(randomList,index);

            randomRaceStat.setText("Random race data :");
            randomRaceLabel.setText(randomDate);
            randomRaceLabel1.setText(newDates.get(index).printPlayers());
            randomRaceLabel2.setText(String.valueOf(newDates.get(index).getPosition()));
        }
        else
        {
            int randomWinPos = randomValue(10, 1);

            if (randomWinPos >= 1 && randomWinPos <= 4){
                updateWinIndex(randomList, 1);
            }
            if (randomWinPos >= 5 && randomWinPos <= 7){
                updateWinIndex(randomList, 2);
            }
            if (randomWinPos == 8 || randomWinPos == 9){
                int rand10Percent = randomValue(4,3);
                updateWinIndex(randomList, rand10Percent);
            }
            if (randomWinPos == 10){
                int rand2Percent = randomValue(9,5);
                updateWinIndex(randomList, rand2Percent);
            }
            restWiningPos.add(winnerIndex, 1);
            addPoints(restWiningPos, index);

            randomRaceStat.setText("Random race data :");
            randomRaceLabel.setText(String.valueOf(randomList));
            randomRaceLabel1.setText(newDates.get(index).printPlayers());
            randomRaceLabel2.setText(String.valueOf(newDates.get(index).getPosition()));
        }

        addToTable();
    }

    public void updateWinIndex(ArrayList<Integer> list, int pos){
        for (int i = 0; i < newDrivers.size(); i++){
            if (list.get(i) == pos)
            {
                winnerIndex = i;
            }
        }
    }
    public void addPoints(ArrayList<Integer> list, int index){
        for (int i = 0; i < newDrivers.size(); i++){
            newDrivers.get(i).setNoOfRaces(1);
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
            newDates.get(index).setPartAndPos(newDrivers.get(i).getName(), list.get(i));
        }
    }

    public void SortDates() {
        //creating a new arraylist to store the dates
        ArrayList<String> datesAll = new ArrayList<>();

        //assigning the dates to datesAll arraylist
        for (int i = 0; i < newDates.size(); i++){
            datesAll.add(newDates.get(i).getDate());
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
        DateTable(datesAll);
    }

    public void SearchPlayer(String name){

        System.out.println(returnYorNs(name));
        for (int i = 0; i < newDates.size(); i++) {
            System.out.println(newDates.get(i).getDate());
            for (int j = 0; j < newDates.get(i).getParticipated().size(); j++){
                System.out.println(newDates.get(i).ret(j));
            }
        }

        //creating 2 new arraylist to store the dates and pos of the respective player
        ArrayList<String> datesParti = new ArrayList<>();
        ArrayList<Integer> posObtained = new ArrayList<>();

        if (returnYorNs(name))
        {
            for (Dates newDate : newDates) {
                System.out.println(newDates.get(0).printPlayers());
                ArrayList<String> test = newDate.getParticipated();
                for (String s : test) {
                    if (s.equalsIgnoreCase(name))
                    {
                        int index = test.indexOf(s);
                        datesParti.add(newDate.getDate());
                        posObtained.add(newDate.getPosition().get(index));
                    }
                }
            }
            for (int i = 0; i < datesParti.size(); i++){
                System.out.println(datesParti.get(i));
                System.out.println(posObtained.get(i));
            }
            playerData(datesParti, posObtained);
        }
        else {
            tab2SearchLabel.setText("No Player Found, Please enter a valid player");
        }
        //playerData(datesParti, posObtained);
    }

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

    public int randomValue(int max, int min) {
        Random random = new Random();
        int randomNum = random.nextInt((max - min) + 1) + min;
        //int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        //int randomNum = Min + (int)(Math.random() * ((Max - Min) + 1))
        //int value = random.nextInt(10)+1; // this will give numbers between 1 and 10.
        return randomNum;
    }

    public boolean raceDone()
    {
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

}

