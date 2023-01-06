package carsharing.menu;

import carsharing.dto.Car;
import java.util.List;

public class ManagerMenu {

    public void managerMenu(Menu menu) {
        ViewMenu.showManagerMenu();
        String line = menu.scanner.nextLine();
        switch (line) {
            case "0": {
                System.out.println();
                menu.mainMenu();
            }
            case "1":
                menu.getCompanyMenu().managerCompanyList(menu);
            case "2":
                menu.getCompanyMenu().createCompany(menu);
            default:
                managerMenu(menu);
        }
    }

    void managerCarList(int companyId, Menu menu) {
        System.out.println();
        List<Car> cars = menu.getCarDao().getAllCars(companyId);
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!\n");
            menu.getCompanyMenu().companyMenu(companyId, menu);
        } else {
            System.out.println("Car list:");
            int i = 1;
            for (Car car : cars) {
                System.out.println(i++ + ". " + car.getName());
            }
        }
        menu.getCompanyMenu().companyMenu(companyId, menu);
    }
}
