javac -d ./bin -sourcepath ./src src/adapter/EnumerateTest/EnumerateTest.java
java -cp ./bin adapter.EnumerateTest.EnumerateTest

javac -d ./bin -sourcepath ./src src/adapter/IterateTest/IterateTest.java
java -cp ./bin adapter.IterateTest.IterateTest

javac -d ./bin -sourcepath ./src src/adapter/PlanetClient/PlanetClient.java
java -cp ./bin adapter.PlanetClient.PlanetClient

javac -d ./bin -sourcepath ./src src/adapter/PlanetClientNoAdapter/PlanetClientNoAdapter.java
java -cp ./bin adapter.PlanetClientNoAdapter.PlanetClientNoAdapter

javac -d ./bin -sourcepath ./src src/adapter/TestAdapter/TestAdapter.java
java -cp ./bin adapter.TestAdapter.TestAdapter


javac -d ./bin -sourcepath ./src src/facade/MakeTea/MakeTea.java
java -cp ./bin facade.MakeTea.MakeTea

javac -d ./bin -sourcepath ./src src/facade/MakeTeaInParallel/MakeTeaInParallel.java
java -cp ./bin facade.MakeTeaInParallel.MakeTeaInParallel


javadoc -d docs -sourcepath src -classpath bin -author -package -use -splitIndex -version -windowtitle 'Adapter' -doctitle 'Adapter' src/adapter/*/*.java

javac -d ./bin -sourcepath ./src src/adapter/IterateTestAdapter/EnumerateTest.java
java -cp ./bin adapter.IterateTestAdapter.EnumerateTest

