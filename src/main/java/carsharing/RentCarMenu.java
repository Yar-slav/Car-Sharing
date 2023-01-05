package carsharing;

import carsharing.dao.CustomerDao;
import carsharing.model.RentCar;

public class RentCarMenu {
    private final Menu menu;
    private final CustomerDao customerDao;

    public RentCarMenu(CustomerDao customerDao, Menu menu) {
        this.customerDao = customerDao;
        this.menu = menu;
    }

    boolean isCarRented(RentCar rentCar, int customerId){
        if(rentCar != null) {
            System.out.println("You've already rented a car!");
            menu.customerMenu(customerId);
            return true;
        }
        return false;
    }

     boolean isCarReturned(RentCar rentCar, int customerId) {
        if (rentCar != null) {
            customerDao.returnedCar(customerId);
            System.out.println("You've returned a rented car!");
            menu.customerMenu(customerId);
            return true;
        }
        return false;
    }

     boolean showMyRentedCar(RentCar rentCar, int customerId) {
        if (rentCar != null) {
            System.out.println("Your rented car:");
            System.out.println(rentCar.getCarName());
            System.out.println("Company:");
            System.out.println(rentCar.getCompanyName());
            menu.customerMenu(customerId);
            return true;
        }
        return false;
    }
}
