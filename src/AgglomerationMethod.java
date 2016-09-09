




public interface AgglomerationMethod {
	
	/**
	 * Compute the dissimilarity between the 
	 * newly formed cluster (i,j) and the existing cluster k.
	 * 
	 * @param dik dissimilarity between clusters i and k
	 * @param djk dissimilarity between clusters j and k
	 * @param dij dissimilarity between clusters i and j
	 * @param ci cardinality of cluster i
	 * @param cj cardinality of cluster j
	 * @param ck cardinality of cluster k
	 * 
	 * @return dissimilarity between cluster (i,j) and cluster k.
	 */
	public double computeDissimilarity(double dik, double djk, double dij, int ci, int cj, int ck);

}
