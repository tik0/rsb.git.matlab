package rsb.matlab;

import java.util.concurrent.TimeUnit;

import rsb.util.QueueAdapter;
import rst.geometry.BoundingBox3DFloatSetType.BoundingBox3DFloatSet;

/**
 * Simple queuing callback adapting generic push-style RSBJava callback
 * interface to a simple, specialized and Matlab- compatible pull-style
 * interface.
 * 
 * @author cemmeric
 */
public class BoundingBox3DFloatSetQueue extends QueueAdapter<BoundingBox3DFloatSet> {

	/**
	 * Delegate function to return queue objects as a list. It blocks until a
	 * result is available. Returned objects are removed from the underlying
	 * blocking queue.
	 * 
	 * @return BoundingBox3DFloatSet, see rst.geometry.BoundingBox3DFloatSet
	 * @throws InterruptedException
	 */
	public BoundingBox3DFloatSet take(int ms) throws InterruptedException {
		return getQueue().poll(ms, TimeUnit.MILLISECONDS);
	}

	public BoundingBox3DFloatSetQueue(final int capacity, final boolean discardOldest) {
		super(capacity, discardOldest);
	}
	
}
