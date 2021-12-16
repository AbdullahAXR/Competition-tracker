Main.class : Main.java StudentCompetition.class TeamCompetition.class CompetitionManager.class StatusBar.class Globals.class CompetitionView.class CompetitionList.class
	javac --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls --add-modules javafx.web -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Main.java
	java  -Dcom.sun.webkit.useHTTP2Loader=false --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls --add-modules javafx.web -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Main

CompetitionList.class: CompetitionList.java ParticipantTablePane.class
	javac --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionList.java 

ParticipantTablePane.class: ParticipantTablePane.java
	javac --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" ParticipantTablePane.java 

CompetitionView.class : CompetitionView.java Emailer.class CompetitionBrowser.class EditableRadioButton.class
	javac --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionView.java 

EditableRadioButton.class: EditableRadioButton.java
	javac --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" EditableRadioButton.java

CompetitionBrowser.class : CompetitionBrowser.java Competition.class
	javac --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.web --add-modules javafx.controls CompetitionBrowser.java

StatusBar.class : StatusBar.java Competition.class
	javac --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" StatusBar.java 

Emailer.class : Emailer.java Competition.class
	javac Emailer.java

Globals.class: Globals.java
	javac --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Globals.java

CompetitionManager.class: CompetitionManager.java CompetitionSheetParser.java CompetitionSheetBuilder.java
	javac  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionManager.java 
	# java  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionManager

CompetitionSheetBuilder.class: CompetitionSheetBuilder.java CompetitionSheetParser.class
	javac  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionSheetBuilder.java 
	# java  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionSheetBuilder

CompetitionSheetParser.class: CompetitionSheetParser.java TeamCompetition.java StudentCompetition.java;
	javac  -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" CompetitionSheetParser.java 

TeamCompetition.class: TeamCompetition.java Competition.class Team.class 
	javac TeamCompetition.java

StudentCompetition.class: StudentCompetition.java Competition.class Student.class
	javac StudentCompetition.java

Student.class: Student.java Participant.class
	javac Student.java

Participant.class: Participant.java
	javac Participant.java

CompetitionCell.class: CompetitionCell.java
	javac --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls CompetitionCell.java

Team.class: Team.java Student.class Participant.class
	javac Team.java

Competition.class: Competition.java CompetitionListener.class 
	javac Competition.java

CompetitionListener.class: CompetitionListener.java
	javac CompetitionListener.java

clean:
	rm *.class

test:
	javac --module-path /usr/lib/jvm/openjfx17/lib  --add-modules javafx.web --add-modules javafx.controls -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Test.java
	java --module-path /usr/lib/jvm/openjfx17/lib   --add-modules  javafx.web --add-modules  javafx.controls -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Test

run:
	java  -Dcom.sun.webkit.useHTTP2Loader=false --module-path /usr/lib/jvm/openjfx17/lib --add-modules javafx.controls --add-modules javafx.web -cp ".:../lib/*:../lib/ooxml-lib/*:../lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Main
