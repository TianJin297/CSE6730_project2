package edu.gatech;

import javax.sound.sampled.Port;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Random;
import java.util.Queue;

public class AirportSim {
    static final private double RADIUS = 6371;
    public static double computeDistance(Airport airport_1,Airport airport_2) {
        double theta1 = Math.toRadians(airport_1.getLatitude() - airport_2.getLatitude());
        double theta2 = Math.toRadians(airport_1.getLongitude() - airport_2.getLongitude());
        double theta3 = Math.sin(theta1 / 2) * Math.sin(theta2 / 2) + Math.cos(Math.toRadians(airport_1.getLatitude())) * Math.cos(Math.toRadians(airport_2.getLatitude())) * Math.sin(theta2 / 2) * Math.sin(theta2 / 2);
        double theta4 = 2 * Math.atan2(Math.sqrt(theta3), Math.sqrt(1 - theta3));
        return RADIUS * theta4;
    }
    static Buffer ReturnBuffer = new Buffer();
    public static Buffer airportSimulation(Info info) {

        //Return Buffer

        //LinkedList used to travel all the Airport
        LinkedList<Airport> PortList = new LinkedList<Airport>();

        int Global_max_airport = 20;
//        Info test_case = new Info();
//        test_case.plane = 20;
//        test_case.maxSpeed = 500;
//        test_case.minSpeed = 400;
//        test_case.maxPassengers = 200;
//        test_case.minPassengers = 100;
//        test_case.randomWeather = true;
//        test_case.time = 20;
//        test_case.runways = 2;
//        test_case.airports.add(1);
//        test_case.airports.add(2);
//        test_case.airports.add(3);



        Info Info_input =info;
        // parse the input info
        int Global_runwaySize = Info_input.runways;
        boolean Global_weather_switch = Info_input.randomWeather;
        boolean[] Airport_switch = Info_input.getAirportSwitch(Global_max_airport);
        int Sim_time = Info_input.time;
        int [][] numOfFlight = Info_input.Random_Num_Plane(Global_max_airport);
        //================================================================
        //=======================   Print Option   =======================
        //================================================================
//        System.out.println("<Option>The total number of planes = "+ Info_input.plane);
//        System.out.println("<Option>The airport_Ids picked are:");
//        System.out.print("           ");
        ReturnBuffer.bufferOption.append("<Option>The total number of planes = "+ Info_input.plane+"\n");
        ReturnBuffer.bufferOption.append("<Option>The airport_Ids picked are:\n");
        ReturnBuffer.bufferOption.append("           ");
        for (Integer i : Info_input.airports){
//            System.out.print(i +"; ");
            ReturnBuffer.bufferOption.append(i +"; ");
        }
//        System.out.print("\n");
//        System.out.println("<Option>The number of runways = "+ Global_runwaySize);
//        System.out.println("<Option>The time of simulation = "+ Sim_time);
//        System.out.println("<Option>The max capacity for airplanes = "+ Info_input.maxPassengers);
//        System.out.println("<Option>The min capacity for airplanes = "+ Info_input.minPassengers);
//        System.out.println("<Option>The max speed for airplanes = "+ Info_input.maxSpeed);
//        System.out.println("<Option>The min speed for airplanes = "+ Info_input.minSpeed);
        ReturnBuffer.bufferOption.append("\n");
        ReturnBuffer.bufferOption.append("<Option>The number of runways = "+ Global_runwaySize+"\n");
        ReturnBuffer.bufferOption.append("<Option>The time of simulation = "+ Sim_time+"\n");
        ReturnBuffer.bufferOption.append("<Option>The max capacity for airplanes = "+ Info_input.maxPassengers+"\n");
        ReturnBuffer.bufferOption.append("<Option>The min capacity for airplanes = "+ Info_input.minPassengers+"\n");
        ReturnBuffer.bufferOption.append("<Option>The max speed for airplanes = "+ Info_input.maxSpeed+"\n");
        ReturnBuffer.bufferOption.append("<Option>The min speed for airplanes = "+ Info_input.minSpeed+"\n");






        if(Info_input.randomWeather == true){
//            System.out.println("<Option>The bad weather function is enabled.");
            ReturnBuffer.bufferOption.append("<Option>The bad weather function is enabled.\n");
        }
        else {
//            System.out.println("<Option>The bad weather function is disenabled.");
            ReturnBuffer.bufferOption.append("<Option>The bad weather function is disenabled.\n");
        }
//        System.out.println("<Option>Print out the number of airplanes in every airline:");
        ReturnBuffer.bufferOption.append("<Option>Print out the number of airplanes in every airline:\n");
        //head
//        System.out.print("\tID\t|");
        ReturnBuffer.bufferOption.append("\tID\t|");
        for(int i = 0; i < Global_max_airport; i++) {
//            System.out.print("\t"+i);
            ReturnBuffer.bufferOption.append("\t"+i);
        }
//        System.out.print("\n");
        ReturnBuffer.bufferOption.append("\n");
        //separation
//        System.out.println("--------|-------------------------------------------------------------------------");
        ReturnBuffer.bufferOption.append("--------|-------------------------------------------------------------------------\n");
        //data
        for (int row = 0; row <Global_max_airport; row++){
//            System.out.print("\t"+row+"\t|");
            ReturnBuffer.bufferOption.append("\t"+row+"\t|");
            for(int i = 0; i < Global_max_airport; i++) {
//                System.out.print("\t"+numOfFlight[row][i]);
                ReturnBuffer.bufferOption.append("\t"+numOfFlight[row][i]);
            }
//            System.out.print("\n");
            ReturnBuffer.bufferOption.append("\n");
        }


        //===============================================================
        //=======================   Option Area   =======================
        //===============================================================
        //Overall Annotation
        //The measurement unit for the different parameters should be the same
        //Recommended unit system :     Distance - km
        //                              Time - hour
        //                              number of passenger - 1





        //set random parameter for weather(Gloabal - All airport are the same)
        //Every 12 hours, every airport gets a weather forcast for the coming 12 hours
        //There may be a period time of bad weather or not, which depends on the variable weahterchance.
        //The length of bad weather depends on the variables wetherMean and weatherVariance.

        double Global_weather_chance = 0.3;
        double Global_weather_mean = 2;
        double Global_weather_variance = 1;





        //Default Airport
        //Format:Airport(String name,double latitude,double longitude,int airportId,int runwaySize, double runwayTimeToLand, double requiredTimeOnGround, double runwayTimeToTakeoff,double weatherChance, double weatherMean, double weatherVariance,boolean Random_weather_switch, LinkedList<Airport> List)
        //airportID should be valid: 0, 1 , 2 ...
        //Can be more than 5 airports

        int iii = 0;

        Airport ATL = new Airport("ATL", 33.647292, -84.427618,iii, Global_runwaySize, 0.03,2,0.07, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport OIA = new Airport("OIA", 28.4312, -81.3081,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport CLT = new Airport("CLT", 35.2144,  -80.9473,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport TPA = new Airport("TPA", 27.9835,  -82.5371,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);

        Airport JFK = new Airport("JFK", 40.645269,  -73.778532,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport BOS = new Airport("BOS", 42.365613, -71.0095602,iii, Global_runwaySize, 0.03,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport DCA = new Airport("DCA", 38.85196,  -77.046253,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport PHL = new Airport("PHL", 39.8743959, -75.2424229,iii, Global_runwaySize, 0.05,2,0.04, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport BGR = new Airport("BGR", 44.8069,-68.8233,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);

        Airport SEA = new Airport("SEA", 47.4502499, -122.3088165,iii, Global_runwaySize, 0.03,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport DEN = new Airport("DEN", 39.847472, -104.6738532,iii, Global_runwaySize, 0.05,2,0.04, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport SLC = new Airport("SLC", 40.7899, -111.9791,iii, Global_runwaySize, 0.05,2,0.04, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport PDX = new Airport("PDX", 45.5898, -122.5951,iii, Global_runwaySize, 0.05,2,0.04, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);

        Airport ORD = new Airport("ORD", 41.977114,  -87.901212,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport PIT = new Airport("PIT", 40.4957722, -80.2413113,iii, Global_runwaySize, 0.05,2,0.05, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport DTW = new Airport("DTW", 42.2161722, -83.3553842,iii, Global_runwaySize, 0.05,2,0.05, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport BNA = new Airport("BNA", 36.1263, -86.6774,iii, Global_runwaySize, 0.05,2,0.05, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport IND = new Airport("IND", 36.7196, -86.7156,iii, Global_runwaySize, 0.05,2,0.05, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);

        Airport LAX = new Airport("LAX", 33.94561,   -118.40068,iii, Global_runwaySize, 0.03,2,0.07, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport LAS = new Airport("LAS", 36.085359,  -115.147959,iii, Global_runwaySize, 0.05,2,0.04, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport PHX = new Airport("PHX", 33.437269,  -112.007788,iii, Global_runwaySize, 0.03,2,0.07, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport SFO = new Airport("SFO", 37.602942,  -122.377788,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport ELP = new Airport("ELP", 31.8053,  -106.3824,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);
        Airport MCI = new Airport("MCI", 39.2991,  -94.7108,iii, Global_runwaySize, 0.05,2,0.03, Global_weather_chance, Global_weather_mean,Global_weather_variance,Global_weather_switch && Airport_switch[iii++], PortList);


        //Distance Matrix : Must be symmetric Matrix
        //The matrix shown below is for the Default airport
        //double[][] distance = {{0,2470,759,738,1389},{2470,0,1942,1741,1232},{759,1942,0,606,730},{738,1741,606,0,802},{1389,1232,730,802,0}};
        double[][] distance = new double [Global_max_airport][Global_max_airport];
        for(int i = 0; i<Global_max_airport; i++){
            for(int j = 0; j<Global_max_airport;j++){
                if( i == j) distance[i][j]=0;
                else distance[i][j]=computeDistance(PortList.get(i),PortList.get(j));
            }
        }



        //AddAirplane
        //Formate:(name, int maxNumberPassengers, Airport airport1, Airport airport2, double speed, distance);
        //The program will automatically generate initial departure event
        //The initial departure event is with delay 0, starting from airport1.
        //The first added airplane will take off early.

        //1.Add self-defined
        //Airplane A1 = new Airplane("747", 300, atl, lax,500, distance);
        //2.Add recursively
        //k = numOfFlight[i][j] means there are k airplanes that initialed with airportID i,j.

        //int [][] numOfFlight = {{0,18,3,4,1},{19,0,9,11,14},{4,9,0,13,17},{4,11,13,0,10},{1,15,17,11,0}};


        //Set random parameter for passengers
        //Upon taking off, the program will generate a Gaussian random percentage of passenger number and assign it to that plane
        //The mean percentage(0 - 1), variance and minimum passenger percentage(0 - 1) are set below;
        //when the random percentage is over 1, it will be set as 1;
        //Similarly, when it is less than minimum passenger percentage, it will be set as minimum passenger percentage.
        double mean = 0.75;
        double variance = 0.15;
        double minimum = 0.3;




        //===============================================================
        //====================   Option Area End  =======================
        //===============================================================
        Airplane temp = new Airplane();
//        System.out.println("<Option>The Gaussian mean = "+temp.setMean(mean));
//        System.out.println("<Option>The Gaussian variance = "+ temp.setVariance(variance));
//        System.out.println("<Option>The Gaussian minimun percentage = "+temp.setMinimumPassengerPercentage(minimum));
        ReturnBuffer.bufferOption.append("<Option>The Gaussian mean = "+temp.setMean(mean)+"\n");
        ReturnBuffer.bufferOption.append("<Option>The Gaussian variance = "+ temp.setVariance(variance)+"\n");
        ReturnBuffer.bufferOption.append("<Option>The Gaussian minimun percentage = "+temp.setMinimumPassengerPercentage(minimum)+"\n");




        for(int i=0; i<numOfFlight.length; i++ ){
            for (int j=0; j<numOfFlight[i].length;j++){
                for(int k=0; k < numOfFlight[i][j]; k++){
                    new Airplane("AutoAdded",Info_input.getRandomCapacity(), PortList.get(i),PortList.get(j),Info_input.getRandomSpeed(),distance);
                }
            }
        }



        Simulator.stopAt(Sim_time);
        Simulator.run();
        for (Airport i : PortList){
            if(Airport_switch[i.getId()]){
//                System.out.println("<Statistic>Airport "+i.getId() + " - "+i.getName()+" : ");
//                System.out.println("             Passengers depart : "+i.getPassengerDepature());
//                System.out.println("             Passengers arrive : "+i.getPassengerArrive());
//                System.out.println("             Total circling time : "+i.getSumCirclingTime());
                ReturnBuffer.bufferStatistic.append("<Statistic>Airport "+i.getId() + " - "+i.getName()+" : \n");
                ReturnBuffer.bufferStatistic.append("             Passengers depart : "+i.getPassengerDepature()+"\n");
                ReturnBuffer.bufferStatistic.append("             Passengers arrive : "+i.getPassengerArrive()+"\n");
                ReturnBuffer.bufferStatistic.append("             Total circling time : "+((int)(100*i.getSumCirclingTime()))/(double)100+"\n");

            }
        }
        return ReturnBuffer;
    }
}

