TeamCompetition.class: TeamCompetition.java Competition.java Team.java 
	javac TeamCompetition.java

StudentCompetition.class: StudentCompetition.java Competition.java Student.java
	javac StudentCompetition.java

Student.class: Student.java
	javac Student.java

Team.class: Team.java Student.java
	javac Team.java

Competition.class: Competition.java
	javac Competition.java

clean:
	rm *.class

test:
	javac  -cp ".:/home/saher/uni/swe/swe-pro/lib/*:/home/saher/uni/swe/swe-pro/lib/ooxml-lib/*:/home/saher/uni/swe/swe-pro/lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Test.java 
	java -cp ".:/home/saher/uni/swe/swe-pro/lib/*:/home/saher/uni/swe/swe-pro/lib/ooxml-lib/*:/home/saher/uni/swe/swe-pro/lib/apache-log4j-2.14.1-bin/log4j-core-2.14.1.jar" Test 
