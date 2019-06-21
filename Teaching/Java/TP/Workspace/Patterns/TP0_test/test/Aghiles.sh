javac -d ./bin -sourcepath ./test:./src -cp ./lib/junit-4.11.jar ./test/AllTests.java
java -cp ./bin:./lib/junit-4.11.jar:./lib/hamcrest-core-1.3.jar AllTests

javac -d ./bin -sourcepath ./test:./src -cp ./lib/junit-4.11.jar ./test/MaClasseTest.java
java -cp ./bin:./lib/junit-4.11.jar:./lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MaClasseTest

javac -d ./bin -sourcepath ./test:./src -cp ./lib/junit-4.11.jar ./test/TestHelloWorld.java
java -cp ./bin:./lib/junit-4.11.jar:./lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore TestHelloWorld
