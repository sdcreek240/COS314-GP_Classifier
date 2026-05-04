package data;

import java.util.*;

public class Dataset{

    public final List<Instance> train;
    public final List<Instance> test;
    public final int numFeatures;


    public Dataset(List<Instance> train, List<Instance> test) {

        this.train       = train;
        this.test        = test;
        this.numFeatures = train.get(0).features.length;
    }//Dataset
}//Dataset