package tree;

public class TerminalNode extends Node {

    private final boolean isVariable;
    private final int featureIndex;   //when isVariable===true

    private final double constant; 

    public TerminalNode(int featureIndex) {
        this.isVariable   = true;
        this.featureIndex = featureIndex;
        this.constant     = 0.0;//unused
    }

    public TerminalNode(double constant) {
        this.isVariable   = false;
        this.featureIndex = -1;//unused
        this.constant     = constant;
    }

    @Override
    public double evaluate(double[] features) { return isVariable? features[featureIndex] : constant; }

    @Override
    public int depth() { return 1; }

    @Override
    public Node copy() { return isVariable? new TerminalNode(featureIndex) : new TerminalNode(constant); }

    @Override
    public String toString() { return isVariable? ("x" + featureIndex) : String.valueOf(constant);}

    //getters
    public boolean isVariable()    { return isVariable; }
    public int getFeatureIndex()   { return featureIndex; }
    public double getConstant()    { return constant; }
}//TerminalNode