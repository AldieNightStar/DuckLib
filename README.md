# DuckLib
DuckTyping for Java

# Simple to use
```java
public class MainClass {
  public static void main(String...args) {
    SomeObject someBigObject = new SomeObject();
    DoSmthInterface quacked = Duck.quack(DoSmthInterface.class, someBigObject);
    quacked.doSmth(); // It's works
  }
}

class SomeObject {
  void doSmth() { ... }
  void anotherMethod() { ... }
  void etc () { ... }
}

interface DoSmthInterface {
  void doSmth();
}
```
