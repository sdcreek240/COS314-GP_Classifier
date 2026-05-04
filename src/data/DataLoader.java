package data;

import java.util.*;
import java.io.*;

public class DataLoader {

    public static List<Instance> load(String fp) throws IOException {

        List<Instance> instances = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fp));
        String line;

        while ((line=br.readLine()) != null) {

            String[] parts = line.split(",");
            double[] features = new double[parts.length-1];

            for (int i=0; i<features.length; i++){

                features[i] = Double.parseDouble(parts[i].trim());
                int label = (int) Double.parseDouble(parts[parts.length-1].trim());
                instances.add(new Instance(features, label));
            }//END_i
        }//END_while

        br.close();
        return instances;
    }//END_load
}//DataLoader