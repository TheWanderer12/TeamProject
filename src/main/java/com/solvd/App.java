package com.solvd;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //tokyo lat 35,6839 lng 139,7744
        //delhi lat 28,6667 lng 77,2167

        //tokyo
        double originLat = Math.toRadians(35.6839);
        double originLng = Math.toRadians(139.7744);

        //delhi
        double destinationLat = Math.toRadians(28.6667);
        double destinationLng = Math.toRadians(77.2167);
        //arccos( sin(lng1) * sin(lng2) * cos(lat1 - lat2) + cos(lng1) * cos(lng2));
        double result = Math.acos(Math.sin(originLng)
                * Math.sin(destinationLng)
                * Math.cos(originLat - destinationLat)
                + Math.cos(originLng)
                * Math.cos(destinationLng)) * 6371;
        System.out.println( result );
    }
}
