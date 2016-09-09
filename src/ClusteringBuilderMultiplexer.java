
public final class ClusteringBuilderMultiplexer implements ClusteringBuilder {

    private final ClusteringBuilder a;
    private final ClusteringBuilder b;


    public ClusteringBuilderMultiplexer(final ClusteringBuilder a, final ClusteringBuilder b) {
        this.a = a;
        this.b = b;
    }

    public void merge(final int i, final int j, final double dissimilarity) {
        a.merge(i, j, dissimilarity);
        b.merge(i, j, dissimilarity);
    }

}
