# DuckLib
DuckTyping for Java

# Simple to use
```java
public class MainClass {
  public static void main(String...args) {
    SomeObject someBigObject = new SomeObject();
    
    Quacked quacked = Duck.quack(DoSmthInterface.class, someBigObject);
    quacked.doSmth(); // It works
  }
}

class SomeObject {
  void doSmth() { ... }
  void anotherMethod() { ... }
  void etc () { ... }
}

interface Quacked {
  void doSmth();
}
```
# If you want to get data from object
```java
interface Quacked {
  SomeObject data(); // JUST add this method - it will return SomeObject, whose Quacked Interface was made from.
  doSmth();
}

```
