
public final class SingleLinkage implements AgglomerationMethod {

	public double computeDissimilarity(final double dik, final double djk, final double dij, final int ci, final int cj, final int ck) {
		return Math.min(dik, djk);
	}
	
    public String toString() {
		return "Single";
	}

}
