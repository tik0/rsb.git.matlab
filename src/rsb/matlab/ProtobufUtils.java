package rsb.matlab;

import java.lang.reflect.Method;

import com.google.protobuf.GeneratedMessage;

/**
 * Utilities to handle protocol buffers types in Matlab, as e.g. accessing
 * builders is not directly possible because they are inner classes.
 * 
 * @author jwienke
 */
public class ProtobufUtils {

	/**
	 * Do not allow construction for utility class with static methods.
	 */
	private ProtobufUtils() {
	}

	/**
	 * A utility method to access builder instances of first-level messages in
	 * proto files.
	 * 
	 * @param typeName
	 *            name of the declared outer class for the proto file
	 * @param messageName
	 *            name (without package) of the message inside the file to get
	 *            the builder for
	 * @return the new builder instance
	 * @throws Throwable
	 *             something went wrong with all the name lookup
	 */
	public static Object getBuilder(final String typeName,
			final String messageName) throws Throwable {
		Class<?> desiredClass = getMessageClass(typeName, messageName);

		Method method = desiredClass.getDeclaredMethod("newBuilder",
				(Class[]) null);

		return method.invoke(null, (Object[]) null);
	}

	private static Class<?> getMessageClass(final String typeName,
			final String messageName) throws ClassNotFoundException {
		Class<?> innerClasses[] = Class.forName(typeName).getClasses();
		Class<?> desiredClass = null;
		for (Class<?> c : innerClasses) {
			if (c.getName().equals(typeName + "$" + messageName)) {
				desiredClass = c;
				break;
			}
		}
		if (desiredClass == null) {
			throw new ClassNotFoundException(messageName);
		}
		return desiredClass;
	}

	/**
	 * A utility method to access builder instances of first-level messages in
	 * proto files.
	 * 
	 * @param typeName
	 *            name of the declared outer class for the proto file
	 * @param messageName
	 *            name (without package) of the message inside the file to get
	 *            the builder for
	 * @return the new builder instance
	 * @throws Throwable
	 *             something went wrong with all the name lookup
	 */
	public static GeneratedMessage getDefaultInstance(final String typeName,
			final String messageName) throws Throwable {
		Class<?> desiredClass = getMessageClass(typeName, messageName);

		Method method = desiredClass.getDeclaredMethod("getDefaultInstance",
				(Class[]) null);

		return (GeneratedMessage) method.invoke(null, (Object[]) null);
	}

}
