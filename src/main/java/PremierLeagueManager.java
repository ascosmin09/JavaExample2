
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date; 
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ascos
 */

    class PremierLeagueManager implements LeagueManager{
    ArrayList<FootballClub> PL=new ArrayList<>(); //creating an arraylist with all the football clubs 
    ArrayList<FootballMatch> matches= new ArrayList<>(); // creating an arraylist with all the matches played
    public static void main(String args[]){
        PremierLeagueManager PremierLeague= new PremierLeagueManager(); //creating a new instance of premierleaguemanager
        PremierLeague.load(); //loading the last saved file to offer continuity
        Scanner input = new Scanner(System.in);
        
        String x; //the variable where I save the chosen option
        PremierLeague.displayMenu(); //displaying the menu for the user
        x= input.next(); //saving the selected option
        while(x.equals("Q")==false){ //until the used inputs "Q" that represents quit or closes the gui interface, the program will keep running 
            //for each option that the users chooses i call the required methods and display the required messages
            if("C".equals(x)) PremierLeague.create();
            
            if("D".equals(x)){
                System.out.println("Here is a list with the clubs that are currently in Premier League: ");
                PremierLeague.PL.forEach((i) -> {
                    System.out.println(i.name);
                });
                System.out.println(" Please enter the name of the club which will be relegated:");
                PremierLeague.delete();
            }
            if("VS".equals(x)){
                System.out.println("Here is a list with the clubs that are currently in Premier League: ");
                PremierLeague.PL.forEach((i) -> {
                    System.out.println(i.name);
                });
                System.out.println(" Please enter the name of the club to retrieve all the stats: ");
                PremierLeague.statistics();
            }
            if("VL".equals(x))PremierLeague.displayTable();
            if("A".equals(x))PremierLeague.addMatch();
            if("S".equals(x))PremierLeague.save();
            if("L".equals(x))PremierLeague.load();
            if("GUI".equals(x))new GUI(PremierLeague);
            PremierLeague.displayMenu(); //displaying the menu for the user
             x= input.next(); //reading the next letter that the user inputs until it inputs "Q"
            }
            
    }
    
    
    @Override
     public void create(){
        System.out.println("Please enter the name of the club: ");
        Scanner input = new Scanner(System.in);
        String name=input.nextLine();
        System.out.println("Please enter the adress of the club");
        String adress=input.nextLine();
        System.out.println("Please enter the name of the owner of the club");
        String owner=input.nextLine();
        System.out.println("Please enter the year when the club was founded");
        String year=input.nextLine();
        System.out.println("Please enter the estimated value of the club");
        String value=input.nextLine();
        //after i saved all the required variables i call the constructor for a footballclub and i add it to the arraylist. i assing the other values manually
        PL.add(new FootballClub(name,adress));
        PL.get(PL.size()-1).currentOwner=owner;
        PL.get(PL.size()-1).value=value;
        PL.get(PL.size()-1).year=year;
    }
    
    @Override
    public void delete(){
        Scanner input = new Scanner(System.in);
        String name=input.nextLine();
        for(int i=0;i<PL.size();i++)
            if(PL.get(i).name.equals(name))PL.remove(i);//deleting/relegating a club using 
        System.out.println(name+" has been relegated from Premier League!");
        
    }
    
    @Override
    public void statistics(){
        Scanner input = new Scanner(System.in);
        String name=input.nextLine();
        for(int i=0;i<PL.size();i++)
            if(PL.get(i).name.equals(name)){PL.get(i).getInfo(); //iterating though the arraylist until i find the desired football club, displaying all info after
                                            PL.get(i).getStats();}  
    }
    
    @Override
    public void addMatch(){
        Scanner input=new Scanner(System.in);
        System.out.println("Please enter the date(dd/mm/yy) when the match has been played: ");
        String date=input.nextLine();
        Date date1 = null;
        //converting the string to date facilitating the sorting that is required to be done for the gui interface
        try {
            date1=new SimpleDateFormat("dd/mm/yy").parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(PremierLeagueManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Please enter the name of the team who plays at home: ");
        String home=input.nextLine();
        System.out.println("Please enter the name of the team who plays away from home: ");
        String away=input.nextLine();
        System.out.println("Please enter the number of goals that the home team scored: ");
        int homeGoals=input.nextInt();
        System.out.println("Please enter the number of goals that the away team scored: ");
        int awayGoals=input.nextInt();
        matches.add(new FootballMatch(home, away, date1,homeGoals,awayGoals)); //adding the match to the arraylist of matches facilitating the sorting that is required to be done for the gui interface
        int i,j=0,k=0;
        for(i=0;i<PL.size();i++){
            if(PL.get(i).name.equals(home))j=i; //identifying which clubs were involved in the game
            if(PL.get(i).name.equals(away))k=i;
        }
        PL.get(0).play(PL.get(j),PL.get(k),homeGoals,awayGoals);//calling the method play on the clubs that were involved so all the stats will be updated automatically
        
    }
    
    @Override
    public void displayTable(){
        //sorting the table by goals first, and then by points. if it has more goals but less number of points is not a problem, the contrary is.
        Comparator<FootballClub> compareBygoals = (FootballClub o1, FootballClub o2) ->Integer.compare(o1.goalDifference(),o2.goalDifference());
        Collections.sort(PL, compareBygoals.reversed());
        Comparator<FootballClub> compareBypoints = (FootballClub o1, FootballClub o2) ->Integer.compare(o1.points,o2.points);
        Collections.sort(PL, compareBypoints.reversed());
        //printing the table by iterating the arraylist
        System.out.println("Club   Games Played   Wins   Draws   Loses   Goals Scored   Goals Received   Goal Difference   Points");
        for(int i=0;i<PL.size();i++)
            System.out.println(PL.get(i).name+"       "+PL.get(i).gamesPlayed+"       "+PL.get(i).wins+"       "+PL.get(i).draws+"       "+PL.get(i).defeats+"            "+PL.get(i).gScored+"            "+PL.get(i).gReceived+"                 "+PL.get(i).goalDifference()+"              "+PL.get(i).points);
    }
    
    @Override
    public void save(){
        //using a buffered writer to write . as noted i wrote in the file in a specific pattern in order to be easily read out of the file, by a person or the program itself.
        try { 
                BufferedWriter writer = new BufferedWriter(new FileWriter("save.dat"));
                //firstly i write all the clubs that are involved, one by one, enumarating all their info
                for(int i=0;i<PL.size();i++){
                    writer.write("Club: \r\n");
                    writer.write(PL.get(i).name+"\r\n");
                    writer.write("Adress of the club:\r\n");
                    writer.write(PL.get(i).adress+"\r\n");
                    writer.write("Current owner of the club: \r\n");
                    writer.write(PL.get(i).currentOwner+"\r\n");
                    writer.write("Year when whe club was founded:\r\n");
                    writer.write(PL.get(i).year+"\r\n");
                    writer.write("Estimated value of the club: \r\n");
                    writer.write(PL.get(i).value+"\r\n");
                    writer.write("Wins this season: \r\n");
                    writer.write(PL.get(i).wins+"\r\n");
                    writer.write("Draws this season: \r\n");
                    writer.write(PL.get(i).draws+"\r\n");
                    writer.write("Defeats this season: \r\n");
                    writer.write(PL.get(i).defeats+"\r\n");
                    writer.write("Goals scored this season: \r\n");
                    writer.write(PL.get(i).gScored+"\r\n");
                    writer.write("Goals received this season: \r\n");
                    writer.write(PL.get(i).gReceived+"\r\n");
                    writer.write("Points accumulated this season: \r\n");
                    writer.write(PL.get(i).points+"\r\n");
                    
                }
                //after i check if any matches were played, and if they were i enumarate them one by one with all the info
                if(matches.size()>0)
                for(int i=0;i<matches.size();i++){
                    writer.write("Match: \r\n");
                    DateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");  
                    String strDate = dateFormat.format(matches.get(i).date);  
                    writer.write(strDate+"\r\n");
                    writer.write(matches.get(i).home+"\r\n");
                    writer.write(matches.get(i).homeGoals+"\r\n");
                    writer.write(matches.get(i).away+"\r\n");
                    writer.write(matches.get(i).awayGoals+"\r\n");
                }
                writer.write("END"); // i print end in the end to facilitate the reading from the file
                writer.flush();
                writer.close();
                }
            catch (IOException e) {System.out.println("Error IOException is: " + e);}
        }
    
    public void load(){
        //before i load a file i make sure that my arraylists are empty. otherwise if i added a new football club for example and i realized i made a mistake, and i try to load the saved file again, my arraylists will contain duplicates and eronated data
        PL.clear();
        matches.clear();
        try { Scanner rf = new Scanner(new BufferedReader(new FileReader("save.dat")));
        int clubCount=-1, matchcount=-1,lineCounter=0;  
        String name = null, adress=null, currentOwner=null, year=null, value=null, home=null, away=null,owner=null;
        int wins=0, draws=0, defeats=0, gScored=0, gReceived=0, points=0, homeGoals=0, awayGoals=0;
        Date date = null;
        String x =rf.nextLine();
        //until the read line will be " Match:" i know i am still reading football clubs. each football club contains 22 lines of info, but only the even lines have the actual info, the ood lines are description of the even lines.
        while ( "Match: ".equals(x)==false) {
            //before the info about a club starts, the line "Club:" will be found. after I identify the start of a new club, i increase my clubcounter which will be the arraylist index for that club and i reset the linecounter which identifies the type of information for each line
               if("Club: ".equals(x)==true){
                   clubCount++;
                   lineCounter=1;
               }
               if(lineCounter==2)name=x;
               if(lineCounter==4)adress=x;
               if(lineCounter==6)currentOwner=x;
               if(lineCounter==8)year=x;
               if(lineCounter==10)value=x;
               if(lineCounter==12)wins=Integer.parseInt(x);
               if(lineCounter==14)draws=Integer.parseInt(x);
               if(lineCounter==16)defeats=Integer.parseInt(x);
               if(lineCounter==18)gScored=Integer.parseInt(x);
               if(lineCounter==20)gReceived=Integer.parseInt(x);
               if(lineCounter==22){ //at the last line before the next "Club: ", which is always line 22 or multiple of 22, i create my football club and i add it to the array list. i transfer all the other info manually
                   points=Integer.parseInt(x);
                    PL.add(new FootballClub(name,adress));
                    PL.get(clubCount).currentOwner=currentOwner;
                    PL.get(clubCount).value=value;
                    PL.get(clubCount).year=year;
                    PL.get(clubCount).wins=wins;
                    PL.get(clubCount).draws=draws;
                    PL.get(clubCount).defeats=defeats;
                    PL.get(clubCount).gScored=gScored;
                    PL.get(clubCount).gReceived=gReceived;
                    PL.get(clubCount).points=points;
               }
               lineCounter++;//after each iteration line counter needs to be increased to represent all the info correctly
               x=rf.nextLine(); // i read another line until i encounter :"Match: "
            }
        // after i encountered the first line that contains :"Match " i start another loop until i finish the document
        while(rf.hasNextLine()){
            //following the same principle as for the football clubs, because i created the save document,i know the structure and i extract the data accordingly
            if("Match: ".equals(x)){
                matchcount++;
                lineCounter=1;
            }
            if(lineCounter==2){
                Date date1 = null;
                try {
                    date1=new SimpleDateFormat("dd/mm/yy").parse(x);
                } catch (ParseException ex) {
                    Logger.getLogger(PremierLeagueManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                date=date1;}
            if(lineCounter==3)home=x;
            if(lineCounter==4)homeGoals=Integer.parseInt(x);
            if(lineCounter==5)away=x;
            if(lineCounter==6){
                awayGoals=Integer.parseInt(x);
                matches.add(new FootballMatch(home, away, date,homeGoals,awayGoals));
            }
            lineCounter++;
            x=rf.nextLine();
        }
            
            rf.close();
            } 
            catch (IOException e) {
            System.out.println("Error IOException is: " + e);
            }
        }
    
    
    private void displayMenu(){ //simplet method to display the menu 
            System.out.println("Please enter one of the following options: \n"
                    +"‘C’ to create a new football club and add it in the premier league. \n"+ 
                    "‘D’ to delete (relegate) an existing football club from the premier league. \n"+
                    "‘VS’ to view the various statistics for a selected club. \n"+
                    "‘VL’ to display the Premier League Table. \n"+
                    "‘A’to add a played match with its score and its date. \n"+
                    "‘S’ to save changes to the Premier League. \n"+
                    "'L' to load the last saved Premier League file.\n"+
                    "'GUI' to load the graphic interface.\n"+
                    "'Q' to quit.");
        }
}
