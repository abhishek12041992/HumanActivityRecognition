
public interface ClusteringBuilder {

    /**
     * Merge two clusters.
     * @param i the smaller of the two cluster indices
     * @param j the larger of the two cluster indices
     * @param dissimilarity between the two merged clusters
     */
    public void merge(int i, int j, double dissimilarity);

}
