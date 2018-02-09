
public class DynamicBindingTest {
    public static void main(String args[]) {

        Car car = new Car();
        car.start();

        Vehicle vehicle = new Car(); //here Type is vehicle but object will be Car
        vehicle.start();//Cars start called because start() is overridden method
    }
}

class Vehicle {

    public void start() {
        System.out.println("Inside start method of Vehicle");
    }
}

class Car extends Vehicle {

    @Override
    public void start() {
        System.out.println("Inside start method of Car");
    }
}
