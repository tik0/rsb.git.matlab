/**
 * 
 */
package rsb.matlab;

import java.util.concurrent.TimeUnit;
import java.util.*;

import rsb.util.QueueAdapter;
import rst.math.MatrixDoubleType.MatrixDouble;

/**
 * Simple queuing callback adapting generic push-style RSBJava
 * callback interface to a simple, specialized and Matlab-
 * compatible pull-style interface.
 * 
 * @author cemmeric
 *
 */
public class MatrixDoubleQueue extends QueueAdapter<MatrixDouble> {

	/**
	 * Delegate function to return queue objects as a list.
	 * It blocks until a result is available. Returned objects
	 * are removed from the underlying blocking queue.
	 * 
	 * @return 2-dim double array of matrix entries, see rst.math.MatrixDouble
	 * @throws Exception
	 */	
	public double[][] take(int ms) throws Exception  {
		MatrixDouble matrix = getQueue().poll(ms, TimeUnit.MILLISECONDS);
	
		if (matrix==null) return null;

		// check dimensions
		int M = matrix.getSize().getM();
		int N = matrix.getSize().getN();
		if (matrix.getData().getValueCount() != M*N) {
			throw new Exception("Dimension missmatch between expected M*N values and provided number of values");
		}

		Iterator<Double> it = matrix.getData().getValueList().iterator();
		// create double array 
		double[][] retval = new double[M][N];
		for (int m = 0; m < M; m++) {
			for (int n = 0; n < N; n++) {
				retval[m][n] = it.next();
			}
		}

		return retval;
	}
	
}
