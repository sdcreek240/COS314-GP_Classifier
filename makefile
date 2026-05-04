JAVAC = javac
JAVA = java
SRC = src
OUT = out
MAIN = Main

all:
	$(JAVAC) -d $(OUT) $(SRC)/*.java $(SRC)/data/*.java $(SRC)/evaluation/*.java $(SRC)/gp/*.java $(SRC)/tree/*.java 

run:
	$(JAVA) -cp $(OUT) $(MAIN)

clean:
	rm -rf $(OUT)
