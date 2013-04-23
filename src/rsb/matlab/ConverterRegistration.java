package rsb.matlab;

import java.nio.ByteBuffer;
import java.util.LinkedList;

import com.google.protobuf.GeneratedMessage;

import rsb.converter.Converter;
import rsb.converter.ConverterRepository;
import rsb.converter.DefaultConverterRepository;
import rsb.converter.ProtocolBufferConverter;

import rst.geometry.TranslationType.Translation;
import rst.geometry.PoseType.Pose;
import rst.geometry.RotationType.Rotation;
import rst.math.MatrixDoubleType.MatrixDouble;
import rst.math.Vec2DFloatType.Vec2DFloat;
import rst.kinematics.JointAnglesType.JointAngles;
import rst.dynamics.WrenchType.Wrench;
import rst.vision.ImageType.Image;
import rst.geometry.PointCloud3DFloatType.PointCloud3DFloat;
import rst.geometry.BoundingBox3DFloatSetType.BoundingBox3DFloatSet;

/**
 * @author swrede
 */
public class ConverterRegistration {

	private static ConverterRepository<ByteBuffer> repository = DefaultConverterRepository
			.getDefaultConverterRepository();

	public ConverterRegistration() {
	}

	/**
	 * Registers a converter for a protobuf type encapsulated in the outer class
	 * with name <code>typeName</code> and the first-level message of name
	 * <code>messageName</code>.
	 * 
	 * @param typeName
	 *            full name of the protobuf generated outer class (with
	 *            packages)
	 * @param messageName
	 *            name of the first-level inner class in <code>typeName</code>
	 *            without packages
	 * @throws Throwable
	 *             something went wrong...
	 */
	public static void register(final String typeName, final String messageName)
			throws Throwable {
		GeneratedMessage instance = ProtobufUtils.getDefaultInstance(typeName,
				messageName);
		repository.addConverter(new ProtocolBufferConverter<GeneratedMessage>(
				instance));

	}

	/**
	 * Registers some default converters from RST...
	 */
	public static void register() {
		LinkedList<Converter<ByteBuffer>> converter = new LinkedList<Converter<ByteBuffer>>();

		converter.add(new ProtocolBufferConverter<MatrixDouble>(MatrixDouble
				.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<JointAngles>(JointAngles
				.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Translation>(Translation
				.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Wrench>(Wrench
				.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Image>(Image
				.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Pose>(Pose
				.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Rotation>(Rotation
				.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Vec2DFloat>(Vec2DFloat
				.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<PointCloud3DFloat>(PointCloud3DFloat
				.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<BoundingBox3DFloatSet>(BoundingBox3DFloatSet
				.getDefaultInstance()));

		// register converters
		for (Converter<ByteBuffer> protocolBufferConverter : converter) {
			repository.addConverter(protocolBufferConverter);
		}

	}

}
