Main.class : Main.java StudentCompetition.java TeamCompetition.java CompetitionManager.java StatusBar.class Globals.class
	javac --module-path /usr/lib/jvm/openjfx/ --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Main.java 
	java --module-path /usr/lib/jvm/openjfx/ --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Main

StatusBar.class : StatusBar.java
	javac --module-path /usr/lib/jvm/openjfx/ --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" StatusBar.java 

Globals.class: Globals.java
	javac --module-path /usr/lib/jvm/openjfx/ --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Globals.java

CompetitionManager.class: CompetitionManager.java CompetitionSheetParser.java CompetitionSheetBuilder.java
	javac  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionManager.java 
	# java  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionManager

CompetitionSheetBuilder.class: CompetitionSheetBuilder.java CompetitionSheetParser.class
	javac  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionSheetBuilder.java 
	# java  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionSheetBuilder

CompetitionSheetParser.class: CompetitionSheetParser.java TeamCompetition.java StudentCompetition.java;
	javac  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionSheetParser.java 

TeamCompetition.class: TeamCompetition.java Competition.java Team.java 
	javac TeamCompetition.java

StudentCompetition.class: StudentCompetition.java Competition.java Student.java
	javac StudentCompetition.java

Student.class: Student.java Participant.java
	javac Student.java

Participant.class: Participant.java

Team.class: Team.java Student.java Participant.java
	javac Team.java

Competition.class: Competition.java
	javac Competition.java

clean:
	rm *.class

test:
	javac  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Test.java 
	java -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Test 
