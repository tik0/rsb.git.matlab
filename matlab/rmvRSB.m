function [] = rmvRSB()
%RMVRSX Remove RSX libraries from Java path
%   Detailed explanation goes here
javarmpath /Users/swrede/Workspace/RSB.m/dist/lib/rsb.m-0.5.0.jar
javarmpath /vol/cit/share/java/rst.jar
javarmpath /vol/cit/share/java/rstsandbox.jar
javarmpath /vol/cit/share/java/RSBJava-0.5.0.jar
javarmpath /opt/local/share/java/protobuf.jar
clear java
end

