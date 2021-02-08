
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ascos
 */
//football club class which inherits sports club
public class FootballClub extends SportsClub {
    int wins, draws, defeats, gScored, gReceived, gamesPlayed,points;
    FootballClub(String name, String adress){
        this.name=name;
        this.adress=adress;
        this.draws=0;
        this.wins=0;
        this.defeats=0;
        this.gScored=0;
        this.gReceived=0;
        this.gamesPlayed=0;
        this.points=0;
        
    }
    
    //when a game is played, calling this method will update the stats for the clubs involved
    public void play(FootballClub x, FootballClub y,int homeGoals, int awayGoals){
        x.gScored=x.gScored+homeGoals;
        x.gReceived=x.gReceived+awayGoals;
        y.gScored=y.gScored+awayGoals;
        y.gReceived=y.gReceived+homeGoals;
        x.gamesPlayed++;
        y.gamesPlayed++;
        if(homeGoals>awayGoals) {
            x.wins++;
            y.defeats++;
            x.points+=3;
        }
        if(homeGoals==awayGoals){
            x.draws++;
            y.draws++;
            x.points++;
            y.points++;
        }
        if(homeGoals<awayGoals){
            x.defeats++;
            y.wins++;
            y.points+=3;
        }
    }
    //simple method to get the goal difference
    public int goalDifference(){
        return this.gScored-this.gReceived;
    }
    //simple method to get the stats for any club
    public void getStats(){
        if(this.wins>0&this.defeats>0)
            System.out.println("Points: "+this.points+" Games played this season: "+this.gamesPlayed+ " Wins: "+this.wins+" Draws: "+this.draws+ " Defeats: "+this.defeats+ " Goals scored: "+this.gScored+" Goals received: "+this.gReceived+"Points: "+this.points+" Winning rate: "+(double)(this.wins/this.defeats) +"%");
        if(this.wins>0&this.defeats==0)
            System.out.println("Points: "+this.points+" Games played this season: "+this.gamesPlayed+ " Wins: "+this.wins+" Draws: "+this.draws+ " Defeats: "+this.defeats+ " Goals scored: "+this.gScored+" Goals received: "+this.gReceived+"Points: "+this.points+" Winning rate: 100%");
        if(this.wins==0&this.defeats==0)
            System.out.println("Points: "+this.points+" Games played this season: "+this.gamesPlayed+ " Wins: "+this.wins+" Draws: "+this.draws+ " Defeats: "+this.defeats+ " Goals scored: "+this.gScored+" Goals received: "+this.gReceived+"Points: "+this.points+" Winning rate: NO MATCHES HAVE BEEN PLAYED THIS SEASON");
        if(this.wins==0&this.defeats>0)
            System.out.println("Points: "+this.points+" Games played this season: "+this.gamesPlayed+ " Wins: "+this.wins+" Draws: "+this.draws+ " Defeats: "+this.defeats+ " Goals scored: "+this.gScored+" Goals received: "+this.gReceived+"Points: "+this.points+" Winning rate: 0%");

    }
}
