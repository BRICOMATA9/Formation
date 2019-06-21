package Auction2;

import java.util.ArrayList;

public class Stock implements Subject {
 private ArrayList<Observer> observers;
 private Bid latestBid;
 
 public Stock () {
  observers = new ArrayList<Observer>();
 }

 public void addObserver(Observer o) {
  observers.add(o);
 }

 public void removeObserver(Observer o) {
  observers.remove(o);
 }

 public void notifyObservers() {
  for (int i = 0; i < observers.size(); i++) {
   Observer obs = observers.get(i);
   obs.update(latestBid);
  }
	System.out.println("");
 }

 public void submitBid(Bid latestBid) {
  this.latestBid = latestBid;
  notifyObservers();  
 }
}
