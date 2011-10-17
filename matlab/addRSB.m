function [ factory ] = addRSB()
%ADDRSX Add RSX libraries to Matlab
%   Detailed explanation goes here
javaaddpath /usr/share/java/RSBJava-0.5.0.jar
javaaddpath /usr/share/java/protobuf.jar
javaaddpath /usr/share/java/rstsandbox.jar
javaaddpath /usr/share/java/rst.jar
javaaddpath /public/amarsi_workshop/lib/rsb.m-0.5.0.jar
rsb.matlab.ConverterRegistration.register()
end