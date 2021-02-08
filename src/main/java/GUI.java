
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ascos
 */
public class GUI extends PremierLeagueManager{
    JFrame frame;
    JTable table;
    GUI(PremierLeagueManager PremierLeague){
    frame= new JFrame(); //creating a new frame
    frame.setDefaultCloseOperation ( JFrame . EXIT_ON_CLOSE ); //when the user closes the GUI the program will quit
    frame.setTitle("Premier League"); //name of the GUI
    
    String[] columnNames = { "Club", "Games played","Wins","Draws","Loses","Goals Scored","Goals received","Goal difference","Points" }; //name of the columns
    
    // Making sure the Premier League table is properly sorted before populating the table
    Comparator<FootballClub> compareBygoals = (FootballClub o1, FootballClub o2) ->Integer.compare(o1.goalDifference(),o2.goalDifference());
    Collections.sort(PL, compareBygoals.reversed());
    Comparator<FootballClub> compareBypoints = (FootballClub o1, FootballClub o2) ->Integer.compare(o1.points,o2.points);
    Collections.sort(PL, compareBypoints.reversed());
    
    //creating a 2D object that has the content of the table 
    Object gDifference;
    Object[][] data= new Object[PremierLeague.PL.size()][9];
    for(int i=0;i<PremierLeague.PL.size();i++){
        data[i][0]=PremierLeague.PL.get(i).name;
        gDifference=PremierLeague.PL.get(i).gScored-PremierLeague.PL.get(i).gReceived;
        data[i][1]=PremierLeague.PL.get(i).gamesPlayed;
        data[i][2]=PremierLeague.PL.get(i).wins;
        data[i][3]=PremierLeague.PL.get(i).draws;
        data[i][4]=PremierLeague.PL.get(i).defeats;
        data[i][5]=PremierLeague.PL.get(i).gScored;
        data[i][6]=PremierLeague.PL.get(i).gReceived;
        data[i][7]=gDifference;
        data[i][8]=PremierLeague.PL.get(i).points;    
    }
    
    //creating the table and populating it
    table = new JTable(data,columnNames); 
    table.setFillsViewportHeight ( true );
    table.setBounds(30, 40, 200, 300);
    
    //offering the possibility to sort the table by any column
    table.setAutoCreateRowSorter(true);
   
    //creating a pane and adding it to the frame
    JScrollPane sp = new JScrollPane(table); 
    frame.add(sp); 
    
    //creating a label and adding it to a container 
    JLabel label = new JLabel ("***Click on the desired column to be sorted ascending/descending" );
    Container c = frame . getContentPane ();
    c.setLayout(new BoxLayout(c , BoxLayout.Y_AXIS));
    c.add(label);
    
    //setting the frame size and visibility
    frame.setSize(1000, 500); 
    frame.setVisible(true);
    } 
}
