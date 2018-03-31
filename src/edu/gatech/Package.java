package edu.gatech;
import java.util.Random;
public class Package {
	private int id;
	private int genegrateTime;
	private int expectedArriveTime;
	private int nowTime;
	private String originalLocation;
	private String endLocation;
	private int arrivalTime;
	private int flyTime; 
	
	public Package(int id, int genegrateTime, int expectedArriveTime, int nowTime,String originalLocation, String endLocation,int arrivalTime, int flyTime) {
		    this.id  = id;
		    this.genegrateTime = genegrateTime;
		    this.expectedArriveTime = expectedArriveTime;
		  	this.nowTime = nowTime;
		  	this.originalLocation = originalLocation;
		  	this.endLocation = endLocation;
		  	this.arrivalTime = arrivalTime;
		  	this.flyTime=flyTime;

		    //Order order=new Order (id, )
		    //this.clientOrder = order;
		    }
	  public int getID() {
		    return id;
		  }
	  
	  public int getGenegrateTime() {
		    return genegrateTime;
		  }
	  public void setGenegrateTim(int time) {
		  genegrateTime=time; 
		  }
	  public int getExpectedArriveTime() {
		    return expectedArriveTime;
		  }
	  public void setExpectedArriveTime(int time) {
		  expectedArriveTime=time; 
		  }
	  
	  public String getOriginalLocation() {
		    return originalLocation;
		  }
	  public void setOriginalLocation(String location) {
		  originalLocation=location; 
		  }
	  
	  public String getEndLocation() {
		    return endLocation;
		  }
	  public void setEndLocation(String location) {
		  endLocation=location; 
		  }
	  
	  public int getArrivalTime() {
		    return arrivalTime;
		  }
	  public void setArrivalTime(int time) {
		  arrivalTime=time; 
		  }
	  public int getNowTime() {
		    return nowTime;
		  }
	  public void setNowTime(int time) {
		  nowTime=time; 
		  }
	  public int getFlyTime() {
		    return flyTime;
		  }
	  public void setFlyTime(int time) {
		  flyTime=time; 
		  }

}
