import data.*;
import gp.*;
import evaluation.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter seed: ");
        long baseSeed = sc.nextLong();

        String trainPath = "data/Breast_train.csv";
        String testPath = "data/Breast_test.csv";

        System.out.println("\nLoading dataset...");
        List<Instance> trainData = DataLoader.load(trainPath);
        List<Instance> testData  = DataLoader.load(testPath);
        Dataset dataset = new Dataset(trainData, testData);
        int runs = 30;

        double[] testAccuraciesArith = new double[runs];
        double[] testAccuraciesLogic = new double[runs];

        Individual overallBestArith = null;
        Individual overallBestLogic = null;

        long totalTimeArith = 0;
        long totalTimeLogic = 0;

        System.out.println("\n==================================================");
        System.out.println("STARTING 30 INDEPENDENT RUNS (ARITHMETIC GP)");
        System.out.println("==================================================");

        for(int i = 0; i < runs; i++){
            long currentSeed = baseSeed  + i;
            System.out.println("\n-- Arithmetic Run " + (i + 1) + "/30 (Seed: " + currentSeed + ") ---");
            long start = System.currentTimeMillis();
            GPEngine engine = new GPEngine(200, 100, 0.8, 0.2, 5, dataset, 5, dataset.numFeatures, new Random(currentSeed), false);
            Individual runBest = engine.run();
            long end = System.currentTimeMillis();
            totalTimeArith += (end - start);
            testAccuraciesArith[i] = Metrics.accuracy(runBest, dataset.test);

            if (overallBestArith == null || runBest.fitness > overallBestArith.fitness) {
                overallBestArith = runBest.copy();
                
            }
        }

        System.out.println("\n==================================================");
        System.out.println("STARTING 30 INDEPENDENT RUNS (DECISION TREE GP)");
        System.out.println("==================================================");

        for(int i = 0; i<runs; i++){
            long currentSeed = baseSeed + i;
            System.out.println("\n--- Decision Tree Run " + (i+1) + "/30 (Seed: " + currentSeed + ") ---");
            long start = System.currentTimeMillis();
            GPEngine engine = new GPEngine(200, 100, 0.8, 0.2, 5, dataset, 5, dataset.numFeatures, new Random(currentSeed), true);
            Individual runBest = engine.run();
            long end = System.currentTimeMillis();
            totalTimeLogic += (end - start);
            testAccuraciesLogic[i] = Metrics.accuracy(runBest, dataset.test);

            if (overallBestLogic == null || runBest.fitness > overallBestLogic.fitness) {
                overallBestLogic = runBest.copy();
                
            }
        }

        double arithTrainAcc = Metrics.accuracy(overallBestArith, dataset.train) * 100;
        double arithTestAcc = Metrics.accuracy(overallBestArith, dataset.test) * 100;
        double arithFMeasure = Metrics.fMeasure(overallBestArith, dataset.test);
        double arithAvgRuntime = (totalTimeArith / (double) runs) / 1000.0;

        double dtTrainAcc = Metrics.accuracy(overallBestLogic, dataset.train) * 100;
        double dtTestAcc = Metrics.accuracy(overallBestLogic, dataset.test) * 100;
        double dtFMeasure = Metrics.fMeasure(overallBestLogic, dataset.test);
        double dtAvgRuntime = (totalTimeLogic / (double) runs) / 1000.0;
        double tStat = StatisticsTest.tTest(testAccuraciesLogic,testAccuraciesArith);

        System.out.println("\n=========================================================================");
        System.out.println("Table 2: Comparison of Classification Performance (Best of 30 Runs)");
        System.out.println("=========================================================================");
        System.out.printf("%-18s %-15s %-15s %-12s %-15s\n", "Algorithm", "Training (%)", "Test (%)", "F-measure", "Avg Runtime (s)");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-18s %-15.2f %-15.2f %-12.4f %-15.2f\n", "Decision Tree", dtTrainAcc, dtTestAcc, dtFMeasure, dtAvgRuntime);
        System.out.printf("%-18s %-15.2f %-15.2f %-12.4f %-15.2f\n", "GP Classifier", arithTrainAcc, arithTestAcc, arithFMeasure, arithAvgRuntime);
        System.out.println("=========================================================================\n");

        System.out.printf("T-Test Statistic (Decision Tree vs GP Classifier): %.4f\n", tStat);
        if(Math.abs(tStat) > 1.96){
            System.out.println("Conclusion: There IS a statistically significant difference (alpha=0.05) between the two models.");

        }
        else{
            System.out.println("Conclusion: There is NO statistically significant difference (alpha=0.05) between the two models.");
        }

        System.out.println("\nDone");


    }
}