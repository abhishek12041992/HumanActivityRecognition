
public final class CentroidLinkage implements AgglomerationMethod {

    public double computeDissimilarity(final double dik, final double djk, final double dij, final int ci, final int cj, final int ck) {
        return (ci*dik+cj*djk-ci*cj*dij/(ci+cj))/(ci+cj);
    }

    public String toString() {
        return "Centroid";
    }

}
