
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ascos
 */
//football match class. Using it is much easier to handle the football matches and sort them by date in the GUI
public class FootballMatch {
    String home, away;
    Date date;
    int homeGoals, awayGoals;
    FootballMatch(String home, String away, Date date, int homeGoals, int awayGoals){
        this.home=home;
        this.away=away;
        this.date=date;
        this.homeGoals=homeGoals;
        this.awayGoals=awayGoals;
    }
    
}
