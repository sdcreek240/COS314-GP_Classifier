package data;

public class Instance {
    public final double[] features;
    public final int label;

    public Instance(double[] features, int label) {
        this.features = features;
        this.label = label;
    }
}