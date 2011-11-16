package rsb.matlab;

import java.nio.ByteBuffer;
import java.util.LinkedList;

import rsb.converter.Converter;
import rsb.converter.ConverterRepository;
import rsb.converter.DefaultConverterRepository;
import rsb.converter.ProtocolBufferConverter;

import rst.geometry.TranslationType.Translation;
import rst.geometry.PoseType.Pose;
import rst.geometry.RotationType.Rotation;
import rst.geometry.DoubleMatrixType.DoubleMatrix;
import rst.kinematics.JointAnglesType.JointAngles;
import rst.dynamics.WrenchType.Wrench;
import rst.vision.ImageType.Image;

public class ConverterRegistration {
	
	private static ConverterRepository<ByteBuffer> repository = DefaultConverterRepository.getDefaultConverterRepository();
	
	public ConverterRegistration() {
	}
	
	public static void register() {
		LinkedList<Converter<ByteBuffer>> converter = new LinkedList<Converter<ByteBuffer>>();
		
		converter.add(new ProtocolBufferConverter<DoubleMatrix>(DoubleMatrix.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<JointAngles>(JointAngles.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Translation>(Translation.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Wrench>(Wrench.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Image>(Image.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Pose>(Pose.getDefaultInstance()));
		converter.add(new ProtocolBufferConverter<Rotation>(Rotation.getDefaultInstance()));
		
		// register converters
		for (Converter<ByteBuffer> protocolBufferConverter : converter) {
			repository.addConverter(protocolBufferConverter);
		}	
		
	}
	
}
