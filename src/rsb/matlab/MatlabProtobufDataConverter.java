package rsb.matlab;

import java.nio.ByteBuffer;

import rsb.converter.ConversionException;
import rsb.converter.Converter;
import rsb.converter.ConverterSignature;
import rsb.converter.UserData;
import rsb.converter.WireContents;

/**
 * Converter for {@link MatlabProtobufData}.
 * 
 * @author jwienke
 */
public class MatlabProtobufDataConverter implements Converter<ByteBuffer> {

	private ConverterSignature signature;

	public MatlabProtobufDataConverter(ConverterSignature signature) {
		this.signature = signature;
	}

	@Override
	public WireContents<ByteBuffer> serialize(Class<?> typeInfo, Object obj)
			throws ConversionException {
		MatlabProtobufData data = (MatlabProtobufData) obj;
		return new WireContents<ByteBuffer>(ByteBuffer.wrap(data.getData()),
				"." + data.getTypeName());
	}

	@Override
	public UserData deserialize(String wireSchema, ByteBuffer buffer)
			throws ConversionException {
		throw new ConversionException("Not implemented yet");
	}

	@Override
	public ConverterSignature getSignature() {
		return signature;
	}

}
