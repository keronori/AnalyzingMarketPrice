#! /bin/sh
#java -cp .:jsoup-1.13.1.jar src/*.java
javac -cp .:lib/jsoup-1.13.1.jar -d ./class/ src/*.java
cd class/
java -cp .:../lib/jsoup-1.13.1.jar Analysis

