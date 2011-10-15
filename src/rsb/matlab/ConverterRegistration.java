package rsb.matlab;

import rsb.converter.DefaultConverterRepository;
import rsb.converter.ProtocolBufferConverter;

import rst.geometry.TranslationType.Translation;
import rst.kinematics.JointAnglesType.JointAngles;

public class ConverterRegistration {
	
	public ConverterRegistration() {
		
	}
	
	public static void register() {
		// Instantiate generic ProtocolBufferConverter with SimpleImage exemplar
		ProtocolBufferConverter<JointAngles> jointAngles = new ProtocolBufferConverter<JointAngles>(JointAngles.getDefaultInstance());
		ProtocolBufferConverter<Translation> translation = new ProtocolBufferConverter<Translation>(Translation.getDefaultInstance());
		
		// register converter for SimpleImage's
		DefaultConverterRepository.getDefaultConverterRepository().addConverter(jointAngles);		
		DefaultConverterRepository.getDefaultConverterRepository().addConverter(translation);		
		
	}
	
}
