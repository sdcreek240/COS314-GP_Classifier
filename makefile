JAVAC = javac
JAVA = java
SRC = src
OUT = out
MAIN = Main
ARGS := $(filter-out run,$(MAKECMDGOALS))

all:
	$(JAVAC) -d $(OUT) $(SRC)/*.java $(SRC)/data/*.java $(SRC)/evaluation/*.java $(SRC)/gp/*.java $(SRC)/tree/*.java 

run:
	$(JAVA) -cp $(OUT) $(MAIN) $(ARGS)

.PHONY: run all clean

# Allow forwarding arbitrary extra goals (e.g. `make run gPrint`) by
# treating unknown goals as no-ops so `$(filter-out run,$(MAKECMDGOALS))`
# picks them up as arguments to the Java program.
%:
	@:

clean:
	rm -rf $(OUT)
















