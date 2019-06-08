javac -d ./bin -sourcepath ./src src/TemplateTester/*.java
java -cp ./bin TemplateTester.TemplateTester

javac -d ./bin -sourcepath ./src src/MyFrame/MyFrame.java
java -cp ./bin MyFrame.MyFrame

javac -d ./bin -sourcepath ./src src/EatBreakfast/*.java
java -cp ./bin EatBreakfast.EatBreakfast

javac -d ./bin -sourcepath ./src src/EatBreakfastTemplate/*.java
java -cp ./bin EatBreakfastTemplate.EatBreakfastTemplate

javac -d ./bin -sourcepath ./src src/EatBreakfastTemplateWithHook/*.java
java -cp ./bin EatBreakfastTemplateWithHook.EatBreakfastTemplateWithHook

javac -d ./bin -sourcepath ./src src/TemplateTesterWithHook/*.java
java -cp ./bin TemplateTesterWithHook.TemplateTesterWithHook

javac -d ./bin -sourcepath ./src src/ClockApplet/ClockApplet.java
java -cp ./bin ClockApplet.ClockApplet

javadoc -docletpath ~/Téléchargements/UmlGraph.jar -doclet org.umlgraph.doclet.UmlGraph -package -attributes -operations -types src/ClockApplet/*.java
dot -Tpng -o UML/ClockApplet.png graph.dot

javadoc -docletpath ~/Téléchargements/UmlGraph.jar -doclet org.umlgraph.doclet.UmlGraph -package -attributes -operations -types src/EatBreakfast/*.java
dot -Tpng -o UML/EatBreakfast.png graph.dot

javadoc -docletpath ~/Téléchargements/UmlGraph.jar -doclet org.umlgraph.doclet.UmlGraph -package -attributes -operations -types src/MyFrame/*.java
dot -Tpng -o UML/MyFrame.png graph.dot

javadoc -docletpath ~/Téléchargements/UmlGraph.jar -doclet org.umlgraph.doclet.UmlGraph -package -attributes -operations -types src/TemplateTester/*.java
dot -Tpng -o UML/TemplateTester.png graph.dot

javadoc -docletpath ~/Téléchargements/UmlGraph.jar -doclet org.umlgraph.doclet.UmlGraph -package -attributes -operations -types src/TemplateTesterWithHook/*.java
dot -Tpng -o UML/TemplateTesterWithHook.png graph.dot

sed -i '1iLigne1' test.txt
