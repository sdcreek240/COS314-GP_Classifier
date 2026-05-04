package data;

import java.util.*;

public class Dataset{

    public final List<Instance> train;
    public final List<Instance> test;
    public final int numFeatures;

    //80/20
    public Dataset(List<Instance> all, Random rng){

        Collections.shuffle(all, rng);
        int split = (int)(all.size()*0.8);
        this.train = all.subList(0, split);
        this.test = all.subList(split, all.size());
        this.numFeatures = all.get(0).features.length;
    }//Constr
}//Dataset