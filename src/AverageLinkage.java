




public final class AverageLinkage implements AgglomerationMethod {

    public double computeDissimilarity(final double dik, final double djk, final double dij, final int ci, final int cj, final int ck) {
        return (ci*dik+cj*djk)/(ci+cj);
    }

    public String toString() {
        return "Average";
    }

}
