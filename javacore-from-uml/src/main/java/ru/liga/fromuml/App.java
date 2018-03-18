package ru.liga.fromuml;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

abstract class Room{
   private double length;
   private double width;
    double area()
    {
      throw new NotImplementedException();
    }
   abstract double repairPrice();
}
 class Bedroom extends Room{
     double repairPrice(){
         throw new NotImplementedException();
     }
}
class Kitchen extends Room{
    double repairPrice(){
        throw new NotImplementedException();
    }
}
class Batthroom extends Room{
    double repairPrice(){
        throw new NotImplementedException();
    }
}
class Flat{
    private List<Room> rooms;
    int floor;
}

class RepairInvoice{
    List<Flat> flats;
    String customer;
  BigDecimal wholePrice()
  {
      throw new NotImplementedException();
  }
}