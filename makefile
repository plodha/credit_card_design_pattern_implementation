# Make file for JAVA Project
JCC = javac

JFLAGS = -g

default: main.class CreditCard.class AmexCC.class VisaCC.class MasterCC.class DiscoverCC.class MasterCardHandler.class AmericanExpressHandler.class DiscoverHandler.class VisaHandler.class csv.class json.class xml.class

main.class: main.java
		$(JCC) $(JFLAGS) main.java

csv.class: csv.java
		$(JCC) $(JFLAGS) csv.java

json.class: json.java
		$(JCC) $(JFLAGS) json.java

xml.class: xml.java
		$(JCC) $(JFLAGS) xml.java

CreditCard.class: CreditCard.java
		$(JCC) $(JFLAGS) CreditCard.java

AmexCC.class: AmexCC.java
		$(JCC) $(JFLAGS) AmexCC.java

VisaCC.class: VisaCC.java
		$(JCC) $(JFLAGS) VisaCC.java

MasterCC.class: MasterCC.java
		$(JCC) $(JFLAGS) MasterCC.java

DiscoverCC.class: DiscoverCC.java
				$(JCC) $(JFLAGS) DiscoverCC.java

MasterCardHandler.class: MasterCardHandler.java
		$(JCC) $(JFLAGS) MasterCardHandler.java

AmericanExpressHandler.class: AmericanExpressHandler.java
		$(JCC) $(JFLAGS) AmericanExpressHandler.java

DiscoverHandler.class: DiscoverHandler.java
		$(JCC) $(JFLAGS) DiscoverHandler.java

VisaHandler.class: VisaHandler.java
		$(JCC) $(JFLAGS) VisaHandler.java



clean:
				$(RM) *.class
