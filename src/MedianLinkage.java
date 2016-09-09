
public final class MedianLinkage implements AgglomerationMethod {

    public double computeDissimilarity(final double dik, final double djk, final double dij, final int ci, final int cj, final int ck) {
        return 0.5*dik+0.5*djk-0.25*dij;
    }

    public String toString() {
        return "Median";
    }

}
