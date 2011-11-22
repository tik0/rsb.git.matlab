package rsb.matlab;

import java.lang.reflect.Method;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.ProtocolMessageEnum;

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
		Class<?> desiredClass = getInnerClass(typeName, messageName);

		Method method = desiredClass.getDeclaredMethod("newBuilder",
				(Class[]) null);

		return method.invoke(null, (Object[]) null);
	}

	public static Class<?> getInnerClass(final String typeName,
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

	public static ProtocolMessageEnum getEnumConstant(
			final Class<? extends ProtocolMessageEnum> clazz,
			final String constantName) {
		ProtocolMessageEnum constants[] = clazz.getEnumConstants();
		for (ProtocolMessageEnum e : constants) {
			if (e.getValueDescriptor().getName().equals(constantName)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Unknown enum constant '"
				+ constantName + "' in enum " + clazz.getCanonicalName());

	}

	public static Class<?> getInnerClass(final Class<?> parentClass,
			final String messageName) throws ClassNotFoundException {
		Class<?> innerClasses[] = parentClass.getClasses();
		Class<?> desiredClass = null;
		for (Class<?> c : innerClasses) {
			if (c.getName().equals(parentClass.getName() + "$" + messageName)) {
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
		Class<?> desiredClass = getInnerClass(typeName, messageName);

		Method method = desiredClass.getDeclaredMethod("getDefaultInstance",
				(Class[]) null);

		return (GeneratedMessage) method.invoke(null, (Object[]) null);
	}

}
