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

public class SwingGUI extends JFrame{

    public static ArrayList<Formula1Driver> newDrivers = Formula1ChampionshipManager.getList();
    public static ArrayList<Dates> newDates = Formula1ChampionshipManager.getDatesList();
    JTable table = new JTable();
    JTable table1 = new JTable();
    JTable table2 = new JTable();
    JTable table3 = new JTable();
    JTable table4 = new JTable();

    private static JDialog dialogBox;

    public static int winnerIndex;

    public void GuiMain()
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                addToTable();

                setLayout(new BorderLayout());
                setSize(1000, 600);

                panelConfig();
                setAlwaysOnTop (true);

                setLocationRelativeTo(null);
                setVisible(true);

                addWindowListener(new WindowAdapter() {                                                                 //https://stackoverflow.com/questions/16372241/run-function-on-jframe-close
                    public void windowClosing(WindowEvent e) {
                        System.out.println("|                  Exiting the application..........                    |");
                        setVisible(false);
                        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        //dispose();
                        Formula1ChampionshipManager.exitApplication();
                    }
                });
            }
        });
    }
    public static void exitCompletely(){
        System.exit(0);
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

        tableModify(table);
    }

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

    public void panelConfig(){
        //top bar//////////////////////////////////////////////////////
        JPanel panelTop = new JPanel(new GridLayout(1,4,20,10));
        panelTop.setBorder(new EmptyBorder(20,10,10,10));
        add(panelTop, BorderLayout.PAGE_START);
        JPanel searchPanel = new JPanel(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/Layer1.png")).getImage().getScaledInstance(100, 40, Image.SCALE_DEFAULT));
        Image image = new ImageIcon(getClass().getResource("/images/search.png")).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        Image imageAsc = new ImageIcon(getClass().getResource("/images/ascenWhite.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        Image imageDesc = new ImageIcon(getClass().getResource("/images/descWhite.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        Image imageGenerate = new ImageIcon(getClass().getResource("/images/RandomGen.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        Image imageGeneratePos = new ImageIcon(getClass().getResource("/images/RandomGenPos.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);

        JLabel icon = new JLabel();
        icon.setIcon(imageIcon);
        JLabel welcomeLabel = new JLabel("WELCOME TO F1");
        welcomeLabel.setFont(new Font("Raleway", Font.PLAIN,30));

        JButton BtnSearch = new JButton();
        BtnSearch.setPreferredSize(new Dimension(40,40));
        BtnSearch.setIcon(new ImageIcon(image));

        TextField textInput = new TextField("text");
        searchPanel.add(textInput, BorderLayout.CENTER);
        searchPanel.add(BtnSearch, BorderLayout.LINE_END);

        panelTop.add(icon);
        panelTop.add(welcomeLabel);
        panelTop.add(searchPanel);

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
        add(tabPane, BorderLayout.CENTER);
        tabPane.add("F1DriverTable",panel1);
        tabPane.add("Race Data",panel2);

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

        panel2.setLayout(new BorderLayout());
        panel2.add(scrollPane1, BorderLayout.CENTER);
        panel2.add(tab2BtnSort, BorderLayout.PAGE_START);

        tab2BtnSort.setBackground(new Color(255, 255, 255, 80));

        //adding action listeners to the buttons respectively
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (raceDone())
                {
                    SortPointsDescending();
                }
                else {
                    showMessageDialog(null, "Please add Race data to drivers or generate a race to sort", "Error", ERROR_MESSAGE);
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (raceDone())
                {
                    SortPointsAscending();
                }
                else {
                    showMessageDialog(null, "Please add Race data to drivers or generate a race to sort", "Error", ERROR_MESSAGE);
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
                    showMessageDialog(null, "Please add Race data to drivers or generate a race to sort", "Error", ERROR_MESSAGE);
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
                if (newDrivers.size() < 10){
                    showMessageDialog(null, "Add at least 10 players for more accuracy", "Warning", JOptionPane.WARNING_MESSAGE);
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
                SortDates();
            }
        });
        BtnSearch.addActionListener(new ActionListener() {
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
            generatedRaceTable(newDates.get(index).getParticipated(), newDates.get(index).getPosition());
            dialogBoxPop("Generated race details", "Random race data :"+randomDate, table3);
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

            generatedRacePosTable(newDates.get(index).getParticipated(), randomList, newDates.get(index).getPosition());
            dialogBoxPop("Generated race with positions details", "Random race date :"+randomDate, table4);
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

    public void SearchPlayer(String name){
        //creating 2 new arraylist to store the dates and pos of the respective player
        ArrayList<String> datesParti = new ArrayList<>();
        ArrayList<Integer> posObtained = new ArrayList<>();

        if (returnYorNs(name))
        {
            for (Dates newDate : newDates) {
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
            playerDataTable(datesParti, posObtained);
            dialogBoxPop("Player", "Details of Player "+name+" :", table2);
        }
        else {
            showMessageDialog(null, "No Player Found, Please enter a valid player", "Error", ERROR_MESSAGE);
        }
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

    public void dialogBoxPop(String title, String heading, JTable table) {                         //https://www.javatpoint.com/java-jdialog
        JFrame f = new JFrame();
        dialogBox = new JDialog(f, title, true);
        dialogBox.setLayout(new BorderLayout());
        JButton close = new JButton("Go Back");

        JScrollPane scrollPane2 = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogBox.setVisible(false);
            }
        });

        dialogBox.add(new JLabel(heading), BorderLayout.PAGE_START);
        dialogBox.add(close, BorderLayout.PAGE_END);
        dialogBox.add(scrollPane2, BorderLayout.CENTER);

        dialogBox.setSize(500, 500);
        dialogBox.setVisible(true);
    }

    public void tableModify(JTable table){
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.BLACK);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setFont(new Font("Tahoma", Font.BOLD,13));
        table.setBackground(Color.decode("#505050"));
        table.setForeground(Color.WHITE);
        table.setOpaque(true);
        table.setFillsViewportHeight(true);
    }
    public void setBtnColour(JButton btn) {
        btn.setOpaque(false);
        btn.setBorderPainted(false);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setForeground(Color.WHITE);
    }
}

