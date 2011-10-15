/**
 * 
 */
package rsb.matlab;

import rsb.util.QueueAdapter;
import rst.kinematics.JointAnglesType.JointAngles;

/**
 * @author swrede
 *
 */
public class JointAnglesQueue extends QueueAdapter<JointAngles> {
	
	// TODO extract take into Matlab Queue signature...
	public Object[] take() throws InterruptedException {
		return getQueue().take().getAnglesList().toArray();
	} 	
	
}
