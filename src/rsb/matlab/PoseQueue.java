package rsb.matlab;

import java.util.concurrent.TimeUnit;

import rsb.util.QueueAdapter;
import rst.geometry.PoseType.Pose;

/**
 * Simple queuing callback adapting generic push-style RSBJava callback
 * interface to a simple, specialized and Matlab- compatible pull-style
 * interface.
 * 
 * @author sfojtu
 */
public class PoseQueue extends QueueAdapter<Pose> {

	/**
	 * Delegate function to return queue objects as a list. It blocks until a
	 * result is available. Returned objects are removed from the underlying
	 * blocking queue.
	 * 
	 * @return raw image data, see rst.vision.Image
	 * @throws InterruptedException
	 */
	public Pose take(int ms) throws InterruptedException {
		return getQueue().poll(ms, TimeUnit.MILLISECONDS);
	}

	public PoseQueue(final int capacity, final boolean discardOldest) {
		super(capacity, discardOldest);
	}
	
}
