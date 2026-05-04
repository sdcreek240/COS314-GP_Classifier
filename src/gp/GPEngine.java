package gp;

import tree.*;
import data.*;
import java.util.*;

public class GPEngine {

    private final int populationSize;
    private final int maxGenerations;
    private final double crossoverRate;
    private final double mutationRate;
    private final int maxDepth;

    private final FitnessEvaluator evaluator;
    private final Selection selection;
    private final Operators operators;
    private final TreeFactory factory;
    private final Random rng;

    public GPEngine(int populationSize, int maxGenerations, double crossoverRate,
                    double mutationRate, int maxDepth, Dataset dataset, 
                    int tournamentSize, int numFeatures, Random rng)
    {

        this.populationSize = populationSize;
        this.maxGenerations = maxGenerations;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.maxDepth = maxDepth;
        this.rng = rng;
        this.evaluator = new FitnessEvaluator(dataset);
        this.selection = new Selection(rng, tournamentSize);
        this.operators = new Operators(rng, maxDepth, numFeatures);
        this.factory = new TreeFactory(numFeatures, rng);
    }//END_constr

    public Individual run() {

        List<Individual> pop = new ArrayList<>();

        for (int i=0; i<populationSize; i++){

            boolean useFull = (i%2==0);
            pop.add(new Individual(factory.build(maxDepth, useFull)));
        }//END)i

        Individual best = null;

        for (int gen=0; gen<maxGenerations; gen++){

            for (Individual ind : pop) evaluator.evaluate(ind);

            for (Individual ind : pop) if (best==null || ind.fitness>best.fitness) best = ind.copy();

            System.out.println("Gen " + gen + " | Best fitness: "+best.fitness+" | "+best.tree);

            //new pop
            List<Individual> next = new ArrayList<>();
            int crossoverCount = (int)(populationSize*crossoverRate);

            while (next.size()<crossoverCount) 
                Collections.addAll(next, operators.crossover(selection.select(pop), selection.select(pop)));

            while (next.size()<populationSize)
                next.add(operators.mutate(selection.select(pop)));

            pop = next;
        }//END_gen

        return best;
    }//END_run
}//GPEngine