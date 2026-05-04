package gp;

import java.util.*;

public class Selection {

    private final Random rng;
    private final int tournamentSize;

    public Selection(Random rng, int tournamentSize){
        this.rng = rng;
        this.tournamentSize = tournamentSize;
    }//constr

    public Individual select(List<Individual> pop){

        Individual best = null;

        for (int i=0; i<tournamentSize; i++){

            Individual candidate = pop.get(rng.nextInt(pop.size()));
            if (best==null || candidate.fitness>best.fitness) best = candidate;
        }//END_i

        return best;
    }//tournament acc to txtbook
}//Selection