import data.*;
import gp.*;
import evaluation.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter seed: ");
        long seed = sc.nextLong();
        String path = "../data/";

        Random rng     = new Random(seed);
        Dataset dataset = new Dataset(DataLoader.load(path), rng);

        // Wire up and run — swap parameters for DT variant
        GPEngine engine = new GPEngine(
            200, 100, 0.8, 0.2, 5,
            dataset, 5, dataset.numFeatures, rng
        );

        Individual best = engine.run();

        System.out.println("\n--- Results ---");
        System.out.println("Train accuracy : " + Metrics.accuracy(best, dataset.train));
        System.out.println("Test  accuracy : " + Metrics.accuracy(best, dataset.test));
        System.out.println("F-measure      : " + Metrics.fMeasure(best, dataset.test));
    }
}