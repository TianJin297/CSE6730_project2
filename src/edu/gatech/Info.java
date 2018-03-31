package edu.gatech;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 19940 on 2017/4/11.
 */
public class Info {
    public List<Integer> airports = new ArrayList<>();
    public int time;
    public int plane;
    public int minSpeed;
    public int maxSpeed;
    public int maxPassengers;
    public int minPassengers;
    public int runways;
    public boolean randomWeather;



    public Info(){};


    Random rand = new Random();
    public int getRandomSpeed(){
        return (int) (this.minSpeed + (this.maxSpeed-this.minSpeed)*rand.nextDouble());
    };
    public int getRandomCapacity(){
        return (int) (this.minPassengers + (this.maxPassengers-this.minPassengers)*rand.nextDouble());
    };
    public boolean[] getAirportSwitch(int size){
        boolean[] result = new boolean[size];
        for (Integer i : this.airports){
            result[i]=true;
        }
        return result;
    };
    public int[][] Random_Num_Plane(int size){
        int[][] result = new int[size][size];
        double[][] temp = new double [size][size];
        double sumOfP = 0;
        int sumOfPlane = 0;
        for (Integer i : this.airports){
            for (Integer j : this.airports){
                if(i!=j){
                    temp[i][j] = rand.nextDouble();
                    sumOfP += temp[i][j];
                }
            }
        }
        for (Integer i : this.airports){
            for (Integer j : this.airports){
                if(i != j){
                    result[i][j] =(int)( this.plane * temp[i][j] / sumOfP);
                    sumOfPlane += result[i][j];
                }
            }
        }
        
        int ramaining = this.plane - sumOfPlane;
        for (Integer i : this.airports){
            for (Integer j : this.airports){
                if(i != j){
                    result[i][j]++;
                    ramaining--;
                }
                if(ramaining == 0)
                    return result;
            }
        }
        
        return result;
        //result[this.airports.get(0)][this.airports.get(1)] += this.plane - sumOfPlane;
        //return result;
    };

}
