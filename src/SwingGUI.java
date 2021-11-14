import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SwingGUI extends JFrame{

    public ArrayList<Formula1Driver> newDrivers = Formula1ChampionshipManager.getList();
    public ArrayList<Dates> newDates = Formula1ChampionshipManager.getDatesList();
    JTable table = new JTable();
    JTable table1 = new JTable();

    JLabel randomRaceLabel = new JLabel();
    JLabel randomRaceLabel1 = new JLabel();
    JLabel randomRaceLabel2 = new JLabel();

    public void GuiMain()
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                table.setModel(new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{"Name", "Age","Team", "Country", "Race Count", "1st pos", "2nd Pos", "3rd Pos", "Points"}));
                addToTable(newDrivers, table);

                setLayout(new BorderLayout());
                setSize(800, 600);

                panelConfig();

                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
                setVisible(true);

            }
        });
    }
    public static void addToTable(ArrayList<Formula1Driver> f1Players, JTable jTable) { //adding the drivers to the table

        for (Formula1Driver driver : f1Players) {
            ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{
                    driver.getName(), driver.getAge(), driver.getTeam(),
                    driver.getLocation(), driver.getNoOfRaces(), driver.getFirstPlace(), driver.getSecondPlace(), driver.getThirdPlace(), driver.getPoints()});
        }
    }

    public void panelConfig(){
        //creating scroll panes for the two tabs
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JScrollPane scrollPane1 = new JScrollPane(table1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //creating the two panels for the tabs
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        //creating a panel with grid layout to put the buttons in page end of panel 1
        JPanel panelLeft = new JPanel(new GridLayout(5,1, 10, 10));
        JPanel panelBottomData = new JPanel(new GridLayout(1,3, 10, 10));
        JPanel panelBottom1 = new JPanel(new GridLayout(1,2, 10, 10));

        //creating buttons and fields
        JTextArea textField1 = new JTextArea();

        JButton btn1 = new JButton("Sort By Name");
        JButton btn2 = new JButton("Sort By Points");
        JButton btn3 = new JButton("Sort By No.of 1st Positions");
        JButton btn4 = new JButton("Generate a Random Race");
        JButton btn5 = new JButton("Generate a Random Race with positions");

        //creating the main tab pane and aligning it in the center
        JTabbedPane tabPane =new JTabbedPane();
        add(tabPane, BorderLayout.CENTER);
        tabPane.add("F1DriverTable",panel1);
        tabPane.add("Race Data",panel2);

        //assigning layouts and panels to the 1st tab
        panel1.setLayout(new BorderLayout());
        panel1.add(scrollPane, BorderLayout.CENTER);
        panel1.add(panelLeft, BorderLayout.LINE_START);
        panel1.add(panelBottomData, BorderLayout.PAGE_END);

        //assigning layouts and panels to the 2nd tab
        panel2.setLayout(new BorderLayout());
        panel2.add(scrollPane1, BorderLayout.CENTER);
        panel2.add(panelBottom1, BorderLayout.PAGE_END);

        //assigning buttons and fields to tab1
        panelLeft.add(btn1, BorderLayout.LINE_START);
        panelLeft.add(btn2, BorderLayout.LINE_START);
        panelLeft.add(btn3, BorderLayout.LINE_START);
        panelLeft.add(btn4, BorderLayout.LINE_START);
        panelLeft.add(btn5, BorderLayout.LINE_START);
        panel1.add(textField1, BorderLayout.PAGE_START);

        panelBottomData.add(randomRaceLabel);
        panelBottomData.add(randomRaceLabel1);
        panelBottomData.add(randomRaceLabel2);

        //adding action listeners to the buttons respectively
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SortNames();
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (raceDone())
                {
                    SortPoints();
                    textField1.append("Welcome");
                    //textField1.repaint();
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
                if (raceDone())
                {
                    GenerateRandomRace();
                }
                else {
                    textField1.append("Please add Race data to drivers or generate a race to sort");
                }
            }
        });
    }

    public void SortNames(){
        Collections.sort(newDrivers, Formula1Driver.DriverNameComparator);
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Age","Team", "Country", "Race Count", "1st pos", "2nd Pos", "3rd Pos", "Points"}));
        addToTable(newDrivers, table);
        table.repaint();
    }
    public void SortPoints(){
        Collections.sort(newDrivers);
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Age","Team", "Country", "Race Count", "1st pos", "2nd Pos", "3rd Pos", "Points"}));
        addToTable(newDrivers, table);
        table.repaint();
    }
    public void SortPosition(){
        Collections.sort(newDrivers, Formula1Driver.PositionComparator);
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Age","Team", "Country", "Race Count", "1st pos", "2nd Pos", "3rd Pos", "Points"}));
        addToTable(newDrivers, table);
        table.repaint();
    }
    public void GenerateRandomRace() {
        //generating random unique positions
        ArrayList<Integer> randomList = new ArrayList<Integer>();
        int totalPos = ChampionshipManager.positions.length;
        for(int i = 0; i < newDrivers.size(); i++)
        {
            int rand = randomValue(totalPos, 1);
            boolean randomBoo = false;
            while (!randomBoo)
            {
                if (!randomList.contains(rand)) {
                    randomList.add(rand);
                    randomBoo = true;
                }
                else {
                    rand = randomValue(10,1);
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
        int index = Formula1ChampionshipManager.returnIndex(randomDate);

        for (int i = 0; i < newDrivers.size(); i++) {
            if (randomList.get(i) == 1) {
                newDrivers.get(i).setFirstPlace(1);
                newDrivers.get(i).setPoints(ChampionshipManager.noOfPoints[0]);
            } else if (randomList.get(i) == 2) {
                newDrivers.get(i).setSecondPlace(1);
                newDrivers.get(i).setPoints(ChampionshipManager.noOfPoints[1]);
            } else if (randomList.get(i) == 3) {
                newDrivers.get(i).setThirdPlace(1);
                newDrivers.get(i).setPoints(ChampionshipManager.noOfPoints[2]);
            } else if (randomList.get(i) > 3 && randomList.get(i) <= 9) {
                newDrivers.get(i).setPoints(ChampionshipManager.noOfPoints[randomList.get(i) - 1]);
            }
            newDates.get(index).setParticipated(newDrivers.get(i).getName());
            newDates.get(index).setPosition(randomList.get(i));
        }

        randomRaceLabel.setText(randomDate);
        randomRaceLabel1.setText(String.valueOf(newDates.get(index).getParticipated()));
        randomRaceLabel2.setText(String.valueOf(newDates.get(index).getPosition()));
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

