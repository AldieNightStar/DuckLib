# DuckLib
DuckTyping for Java

# Simple to use
```java
public class MainClass {
  public static void main(String...args) {
    var someBigObject = new SomeClass();
    var quacked = Duck.quack(DoSmthInterface.class, someBigObject);
    quacked.doSmth(); // It's works
  }
}

class SomeClass {
  void doSmth() { ... }
  void anotherMethod() { ... }
  void etc () { ... }
}

interface DoSmthInterface {
  void doSmth();
}
```
