package week04;

import java.util.ArrayList;

public class CarMenu {
    private ArrayList<Car> cars;

    private KeyboardReader kbr;
    public CarMenu(){
        cars = new ArrayList<Car>();
        kbr = new KeyboardReader();
    }

    public void RunMenu()  {
        String display = "1. Add a new Car" +
                         "\n2. Remove a Car" +
                         "\n3. Display all car details" +
                         " \n4. exit";

        boolean exit = false;

        while (!exit)
        {
            // clear the console - note won`t work in IDE console.
            // see https://www.javatpoint.com/how-to-clear-screen-in-java for OS independent methods
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(display);

            int choice = kbr.getInt("Select a menu option", 1, 4);
            switch (choice)
            {
                case 1:
                    Car car = enterCarDetails();
                    cars.add(car);
                    break;
                case 2:
                    deleteCar();
                    break;
                case 3:
                    displayCars();
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }

    private Car enterCarDetails()
    {
        String name = kbr.getString("Please enter the name of your car");
        String colour = kbr.getString("Please enter the colour of your car");
        int speed = kbr.getInt("Please enter the speed of your car", 0, 200);
        Car car = new Car(name);
        car.addCarDetails(colour, speed);
        return car;
    }

    private void displayCars()
    {
        if (cars.isEmpty())
        {
            System.out.println("There are no cars to display!!");
        }
        else
        {
            for (Car c : cars) {
                System.out.println(c);
            }
        }
        kbr.getString("Press enter to continue");
    }

    private void deleteCar() {
        // Just to be safe ensure there are cars to delete!
        if (cars.isEmpty()) {
            System.out.println("There are no cars to delete!!");
            kbr.getString("Press enter to continue");
            return;
        }

        // Display all cars and allow user to select car to delete
        int index = 1;
        for (Car c : cars) {
            System.out.println(("\t" + index + " " + c));
            index++;
        }
        int remove = kbr.getInt("Select Car to delete", 1, index);
        cars.remove(remove-1);
    }
}
