import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class SwingGUI extends JFrame{

    public ArrayList<Formula1Driver> newDrivers = Formula1ChampionshipManager.getList();
    JTable table;
    Object[][] rows = new Object[newDrivers.size()][9];
    //Object rows[][] = {{"1","2"}, {"1","2"}, {"1","2"}, {"1","2"}, {"1","2"}, {"1","2"}, {"1","2"}, {"1","2"}, {"1","2"}};
    String[] columns = {"Name", "Age","Team", "Country", "Race Count", "1st pos", "2nd Pos", "3rd Pos", "Points"};
    TableModel newModel;

    public void GuiMain()
    {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){

                //newModel = new DefaultTableModel(rows, columns);
                //table = new JTable(newModel);
                JTable table = new JTable();
                table.setModel(new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{"Driver name", "Driver team", "Driver location", "First positions", "Second positions ", "Third positions", "Points", "Race Count"}));
                addToTable(newDrivers, table);

                setLayout(new BorderLayout());
                setSize(800, 600);
                add(table, BorderLayout.CENTER);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setLocationRelativeTo(null);
                setVisible(true);
            }
        });
    }
    public static void addToTable(ArrayList<Formula1Driver> f1, JTable jTable) { //adding the drivers to the table

        for (Formula1Driver driver : f1) {
            ((DefaultTableModel) jTable.getModel()).addRow(new Object[]{
                    driver.getName(), driver.getTeam(),
                    driver.getLocation(), driver.getFirstPlace(), driver.getSecondPlace(), driver.getThirdPlace(), driver.getPoints(), driver.getNoOfRaces()});

        }

    }
//    public void Table()
//    {
//        for (int i = 0; i < newDrivers.size(); i++) {
//            for (int j = 0; j < newDrivers.size(); j++) {
//                rows[i][j] = data(i);           //assign values to each array element
//                //System.out.print(intArray[i][j] + " "); //print each element
//            }
//            //System.out.println();
//        }
//    }

//    public String data(int index){
//        return switch (index) {
//            case 0 -> newDrivers.get(0).getName();
//            case 1 -> newDrivers.get(1).getName();
//            default -> null;
//        };
//    }
//    public void loadDataToJtable(ArrayList<Formula1Driver> list){
//
//        for (int i = 0; i <  ; i++) {
//
//            for ( int k  = 0; k < 9 ; k++) {
//
//                for (int h = 0; h < list1.size(); h++) {
//
//                    String b =  list1.get(h);
//                    b = table.getValueAt(i, k).toString();
//
//                }
//            }
//        }
//    }
}

