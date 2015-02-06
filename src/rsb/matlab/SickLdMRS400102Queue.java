package rsb.matlab;

import java.util.concurrent.TimeUnit;
import java.util.*;

import rsb.util.QueueAdapter;
import rst.claas.SickLdMRS400102Type.SickLdMRS400102;

/**
 * Simple queuing callback adapting generic push-style RSBJava callback
 * interface to a simple, specialized and Matlab- compatible pull-style
 * interface.
 * 
 * @author tkorthals
 */
public class SickLdMRS400102Queue extends QueueAdapter<SickLdMRS400102> {

  /**
   * Delegate function to return queue objects as a list. It blocks until a
   * result is available. Returned objects are removed from the underlying
   * blocking queue.
   * 
   * @return Data from the Sick_LD_MRS400102, see rst.claas.SickLdMRS400102
   * @throws Exception
   */
  public SickLdMRS400102 take(int ms) throws Exception {
    return getQueue().poll(ms, TimeUnit.MILLISECONDS);
  }
  
  public SickLdMRS400102Queue(final int capacity, final boolean discardOldest) {
    super(capacity, discardOldest);
  }

}
