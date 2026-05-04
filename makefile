JAVAC = javac
JAVA = java
SRC = src
OUT = out
MAIN = Main

all:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)/*.java

run:
	$(JAVA) -cp $(OUT) $(MAIN)

clean:
	rm -rf $(OUT)
