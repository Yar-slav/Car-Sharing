package carsharing;

import carsharing.model.RentCar;

public class RentCarMenu {
    Menu menu = new Menu();
    void rentCar(RentCar rentCar, int customerId){
        if(rentCar == null) {
            menu.customerCompanyList(customerId);
        } else {
            System.out.println("You've already rented a car!");
            menu.customerMenu(customerId);
        }
    }

     void returnCar(RentCar rentCar, int customerId) {
        if (rentCar == null) {
            System.out.println("You didn't rent a car!");
            menu.customerMenu(customerId);
        } else {
            menu.customerDao.returnedCar(customerId);
            System.out.println("You've returned a rented car!");
            menu.customerMenu(customerId);
        }
    }

     void myRentedCar(RentCar rentCar, int customerId) {
        if(rentCar == null) {
            System.out.println("You didn't rent a car!");
            menu.customerMenu(customerId);
        } else {
            System.out.println("Your rented car:");
            System.out.println(rentCar.getCarName());
            System.out.println("Company:");
            System.out.println(rentCar.getCompanyName());
            menu.customerMenu(customerId);
        }
    }
}
