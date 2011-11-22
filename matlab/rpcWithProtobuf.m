clear all;

javaaddpath('/home/languitar/work/RSBJava/dist/lib/rsb-0.6.0.jar')
javaaddpath('/home/languitar/work/RSB.m/dist/lib/rsb.m-0.5.0.jar')
javaaddpath('/usr/share/java/protobuf-java/protobuf-java-2.4.1.jar')
javaaddpath('/home/languitar/work/RST/build/java/rst.jar')
javaaddpath('/home/languitar/work/RST/build/java/rstsandbox.jar')

rsb.matlab.ConverterRegistration.register('rst.generic.MethodCallType', 'MethodCall')

b = rsb.matlab.ProtobufUtils.getBuilder('rst.generic.MethodCallType', 'MethodCall');
b.setName(com.google.protobuf.ByteString.copyFromUtf8('doFoo'));
a1 = b.addArgumentsBuilder();
valueClass = rsb.matlab.ProtobufUtils.getInnerClass('rst.generic.ValueType', 'Value');
typeClass = rsb.matlab.ProtobufUtils.getInnerClass(valueClass, 'Type');
d = rsb.matlab.ProtobufUtils.getEnumConstant(typeClass, 'DOUBLE');
a1.setType(d);
a1.setDouble(12.34)

factory = rsb.Factory.getInstance();
srv = factory.createRemoteServer('/example/server');
srv.activate();
reply = srv.call('echo', b.build());
