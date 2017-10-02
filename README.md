# Console canvas

Console canvas application is implemented in Scala language with SBT build tool. 
 - In order to build a runnable jar use:

`sbt oneJar`

It will be then created in `console-canvas\target\scala-2.12` folder

 - in order to run it use next command:

`java -jar console-canvas_2.12-1.0-one-jar.jar`


Project has a number of unit tests (written with ScalaTest & ScalaSpec) and expectation tests (written with Cucumber). In order to run tests use:

`sbt test`

### Functional details

 - commands which operate with coordinates outside created canvas will be ignored
 - commands issues before creation of canvas will be ignored
 - fill area command works in 2 'modes' right now: if pixel which it was pointed to was not set to element (i.e. not 'x') it will then fill the area as requested by task.
 If there was something (e.g. element with 'x' or some previous colour\element) then it will fill the border of color, e.g.
 
 ```
	 ----------------------
	 |               xxxxx|
	 |xxxxxx         x   x|
	 |     x         xxxxx|
	 |     x              |
	 ----------------------
	 
	 enter command: B 6 3 w
	 
	 ----------------------
	 |               xxxxx|
	 |wwwwww         x   x|
	 |     w         xxxxx|
	 |     w              |
	 ----------------------
 ```

### Implementation details

 - even though all implementation classes for interfaces are hardcoded (e.g. for CommandInput and CanvasOutput) it should be pretty easy to change if required.
 - in order to add a new command not only a new class should be created but also CommandResolver must be updated. 
 That is a trade-off in order to easy switch\change\add another source of commands.
 
