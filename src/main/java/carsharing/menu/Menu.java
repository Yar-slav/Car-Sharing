package carsharing.menu;

import carsharing.DBConnection;
import carsharing.Verification;
import carsharing.dao.*;

import carsharing.dao.Implementation.CarDaoImpl;
import carsharing.dao.Implementation.CompanyDaoImpl;
import carsharing.dao.Implementation.CustomerDaoImpl;
import java.util.Scanner;

public class Menu{

    Scanner scanner = new Scanner(System.in);
    private final DBConnection dbConnection;
    private final CompanyDao companyDao;
    private final CarDao carDao;
    private final CustomerDao customerDao;
    private final ManagerMenu managerMenu;
    private final CustomerMenu customerMenu;
    private final CompanyMenu companyMenu;

    public Menu(DBConnection dbConnection, CompanyDao companyDao, CarDao carDao, CustomerDao customerDao) {
        this.dbConnection = dbConnection;
        this.companyDao = companyDao;
        this.carDao = carDao;
        this.customerDao = customerDao;
        this.managerMenu = new ManagerMenu();
        this.customerMenu = new CustomerMenu();
        this.companyMenu = new CompanyMenu();
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public CarDao getCarDao() {
        return carDao;
    }

    public ManagerMenu getManagerMenu(){
        return managerMenu;
    }

    public CompanyMenu getCompanyMenu(){
        return companyMenu;
    }

    public CustomerMenu getCustomerMenu(){
        return customerMenu;
    }


    public void mainMenu() {
        ViewMenu.showMainMenu();
        String line = scanner.nextLine();
        switch (line) {
            case "0": dbConnection.exit();
            case "1": managerMenu.managerMenu(this);
            case "2": customerMenu.customerList(this);
            case "3": customerMenu.createCustomers(this);
            default: {
                System.out.println();
                mainMenu();
            }
        }
    }
}
