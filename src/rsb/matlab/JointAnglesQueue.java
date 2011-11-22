package rsb.matlab;

import java.util.concurrent.TimeUnit;

import rsb.util.QueueAdapter;
import rst.kinematics.JointAnglesType.JointAngles;

/**
 * Simple queuing callback adapting generic push-style RSBJava
 * callback interface to a simple, specialized and Matlab-
 * compatible pull-style interface.
 * 
 * @author swrede
 */
public class JointAnglesQueue extends QueueAdapter<JointAngles> {
	
	/**
	 * Delegate function to return queue objects as a list.
	 * It blocks until a result is available. Returned objects
	 * are removed from the underlying blocking queue.
	 * 
	 * @return List of angles, see rst.kinematics.JointAngles
	 * @throws InterruptedException
	 */
	private Object[] take() throws InterruptedException {
		return getQueue().take().getAnglesList().toArray();
	} 

	/**
	 * Delegate function to return queue objects as a list.
	 * It blocks until a result is available. Returned objects
	 * are removed from the underlying blocking queue.
	 * 
	 * @return List of angles, see rst.kinematics.JointAngles
	 * @throws InterruptedException
	 */	
	public Object[] take(int ms) throws InterruptedException {
		JointAngles result = getQueue().poll(ms, TimeUnit.MILLISECONDS);
		if (result==null) return null;
		// else just return the object list
		return result.getAnglesList().toArray();
	}
	
}
