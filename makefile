# Make file for JAVA Project
JCC = javac

JFLAGS = -g

default: CreditCardIntro.class CreditCard.class AmexCC.class VisaCC.class MasterCC.class DiscoverCC.class MasterCardHandler.class AmericanExpressHandler.class DiscoverHandler.class VisaHandler.class

CreditCardIntro.class: CreditCardIntro.java
		$(JCC) $(JFLAGS) CreditCardIntro.java

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
