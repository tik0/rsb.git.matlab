package rsb.matlab;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import rsb.Event;
import rsb.Informer;
import rsb.RSBException;
import rsb.converter.ConverterRepository;
import rsb.converter.ConverterSignature;
import rsb.converter.DefaultConverterRepository;

/**
 * Internal data holder for serialized protocol buffer data from Matlab.
 * 
 * @author jwienke
 */
public class MatlabProtobufData {

	private static ConverterRepository<ByteBuffer> repository = DefaultConverterRepository
			.getDefaultConverterRepository();
	private static Set<String> registeredProtobufKeys = new HashSet<String>();

	private String typeName;
	private byte data[];

	public MatlabProtobufData(final String typeName, final byte data[]) {
		this.setTypeName(typeName);
		this.setData(data);
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte data[]) {
		this.data = data;
	}

	public static void registerSendingConverter() {
		final MatlabProtobufDataConverter converter = new MatlabProtobufDataConverter(
				new ConverterSignature("unknown...", MatlabProtobufData.class));
		repository.addConverter(converter);
	}
	
	public static void registerReceivingConverter(final String protobufKey) {
		final MatlabProtobufDataConverter converter = new MatlabProtobufDataConverter(
				new ConverterSignature(protobufKey, MatlabProtobufData.class));
		repository.addConverter(converter);
	}

	public static Event send(final Informer<Object> informer,
			final String protobufKey, final byte data[]) throws RSBException {
		System.out.println("Informer " + informer + " called with protoufKey "
				+ protobufKey + " and data " + data);
		return informer.send(new MatlabProtobufData(protobufKey, data));
	}

}
