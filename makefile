# Make file for JAVA Project
JCC = javac

JFLAGS = -g

default: CreditCardIntro.class CreditCard.class MasterCardHandler.class AmericanExpressHandler.class DiscoverHandler.class VisaHandler.class

CreditCardIntro.class: CreditCardIntro.java
		$(JCC) $(JFLAGS) CreditCardIntro.java

CreditCard.class: CreditCard.java
		$(JCC) $(JFLAGS) CreditCard.java

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
