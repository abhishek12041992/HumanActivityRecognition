
public final class ClusteringMatrixBuilder implements ClusteringBuilder {

    static final int INVALID = -1;
    
    final int[][] clustering;
     int currentStep;


    public ClusteringMatrixBuilder(final int nObservations) {
        final int nSteps = nObservations;
        clustering = new int[nSteps][nObservations];
        for (int observation = 0; observation<nObservations; observation++) {
            // initialize original step (each observation is its own cluster)
            clustering[0][observation] = observation;
            // initialize subsequent steps to "invalid"
            for (int step = 1; step<nSteps; step++) {
                clustering[step][observation] = INVALID;
            }
        }
        currentStep = 0;
    }

    public void merge(final int i, final int j, final double dissimilarity) {
        final int previousStep = currentStep;
        currentStep++;
        for (int observation = 0; observation<clustering.length; observation++) {
            final int previousCluster = clustering[previousStep][observation];
            if (previousCluster==j) {
                clustering[currentStep][observation] = i;
            } else {
                clustering[currentStep][observation] = previousCluster;
            }
        }
    }

    public int[][] getClustering() {
        return clustering;
    }

}
