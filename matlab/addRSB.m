function [ factory ] = addRSB()
%ADDRSX Add RSX libraries to Matlab
%   Detailed explanation goes here
javaaddpath /vol/cit/share/java/RSBJava-0.5.0.jar
javaaddpath /opt/local/share/java/protobuf.jar
javaaddpath /vol/cit/share/java/rstsandbox.jar
javaaddpath /vol/cit/share/java/rst.jar
javaaddpath /Users/swrede/Workspace/RSBMatlab/dist/lib/rsb.m-0.5.0.jar
rsb.matlab.ConverterRegistration.register()
end