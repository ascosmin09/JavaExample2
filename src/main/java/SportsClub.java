/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ascos
 */
abstract class SportsClub {
  String name,adress,currentOwner,value,year;
  public abstract void play(FootballClub x, FootballClub y,int homeGoals, int awayGoals);
  //simple method to get the info for any club
  public void getInfo(){
      System.out.println("Name of the club: "+this.name+"."+"Adress of the club: "+this.adress+"."+"Year the club was founded: "+this.year+"."+"Current owner of the club: "+this.currentOwner);
  } 
}
