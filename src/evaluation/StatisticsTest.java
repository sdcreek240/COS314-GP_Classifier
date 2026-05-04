package evaluation;

import java.util.*;

public class StatisticsTest {

    public static double tTest(double[] runsA, double[] runsB){

        double meanA = mean(runsA), meanB = mean(runsB);
        double varA = variance(runsA, meanA), varB = variance(runsB, meanB);
        
        int n = runsA.length;

        return (meanA-meanB)/Math.sqrt((varA+varB)/n);
    }//tTest

    private static double mean(double[] v) { 

        double s=0; for (double x:v) s+=x;
        return s/v.length;
    }//mean

    private static double variance(double[] v, double mean) {

        double s=0; for (double x:v) s += (x-mean)*(x-mean);
        return s/(v.length-1);
    }//var
}//StatisticsTest