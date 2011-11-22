package rsb.matlab;

import java.util.concurrent.TimeUnit;

import rsb.util.QueueAdapter;
import rst.vision.ImageType.Image;

/**
 * Simple queuing callback adapting generic push-style RSBJava
 * callback interface to a simple, specialized and Matlab-
 * compatible pull-style interface.
 * 
 * @author swrede
 */
public class ImageQueue extends QueueAdapter<Image> {


	/**
	 * Delegate function to return queue objects as a list.
	 * It blocks until a result is available. Returned objects
	 * are removed from the underlying blocking queue.
	 * 
	 * @return raw image data, see rst.vision.Image
	 * @throws InterruptedException
	 */	
	public byte[] take(int ms) throws InterruptedException {
		Image result = getQueue().poll(ms, TimeUnit.MILLISECONDS);
		if (result==null) return null;
		// else just return the raw data 
		return result.getData().toByteArray();
	}
	
}
