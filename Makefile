run: compile
	java StudentApp
compile:
	javac SortedCollectionInterface.java
	javac RedBlackTree.java
	javac StudentData.java
	javac StudentLoader.java
	javac StudentBackEnd.java
	javac StudentFrontEnd.java
	javac TextUITester.java
	javac StudentApp.java
	javac -cp .:junit5.jar StudentTests.java 


test: compile
	java -jar junit5.jar --class-path . --scan-classpath