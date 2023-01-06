package carsharing.menu;

import carsharing.RentCar;

public class RentCarMenu {

    public boolean isCarRented(RentCar rentCar, int customerId, Menu menu) {
        if (rentCar != null) {
            System.out.println("You've already rented a car!");
            menu.getCustomerMenu().customerMenu(customerId, menu);
            return true;
        }
        return false;
    }

    public boolean isCarReturned(RentCar rentCar, int customerId, Menu menu) {
        if (rentCar != null) {
            menu.getCustomerDao().returnedCar(customerId);
            System.out.println("You've returned a rented car!");
            menu.getCustomerMenu().customerMenu(customerId, menu);
            return true;
        }
        return false;
    }

    public boolean showMyRentedCar(RentCar rentCar, int customerId, Menu menu) {
        if (rentCar != null) {
            System.out.println("Your rented car:");
            System.out.println(rentCar.getCarName());
            System.out.println("Company:");
            System.out.println(rentCar.getCompanyName());
            menu.getCustomerMenu().customerMenu(customerId, menu);
            return true;
        }
        return false;
    }

    void createCar(int companyId, Menu menu) {
        System.out.println("\nEnter the car name:");
        String carName = menu.scanner.nextLine();
        menu.getCarDao().createCar(carName, companyId);
        System.out.println("The car was added!\n");
        menu.getCompanyMenu().companyMenu(companyId, menu);
    }
}
