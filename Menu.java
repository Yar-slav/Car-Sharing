package carsharing;

import carsharing.dao.*;
import carsharing.dto.Car;
import carsharing.dto.Company;
import carsharing.dto.Customer;
import carsharing.model.RentCar;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static DBConnection dbConnection;
    public static Scanner scanner;
    public static CompanyDao companyDao;
    public static CarDao carDao;
    public static CustomerDao customerDao;

    public Menu() {
        dbConnection = new DBConnection();
        scanner = new Scanner(System.in);
        companyDao = new CompanyDaoImpl();
        carDao = new CarDaoImpl();
        customerDao = new CustomerDaoImpl();
    }

    static void mainMenu() {
        ViewMenu.showMainMenu();
        String line = scanner.nextLine();

        if(line.equals("1")) {
            managerMenu();
        }else if(line.equals("2")) {
            customerList();
        }else if(line.equals("3")) {
            createCustomers();
        }
        else if(line.equals("0")) {
            dbConnection.exit();
        } else {
            System.out.println();
            mainMenu();
        }
    }

    static void managerMenu() {
        ViewMenu.showManagerMenu();
        String line = scanner.nextLine();

        if(line.equals("0")){
            System.out.println();
            mainMenu();
        } else if (line.equals("1")) {
            managerCompanyList();
        } else if(line.equals("2")) {
            createCompany();
        } else {
            managerMenu();
        }
    }

    static void companyMenu(int companyId) {
        ViewMenu.showCompanyMenu();

        String line = scanner.nextLine();
        if(line.equals("0")){
            managerMenu();
        } else if (line.equals("1")) {
            managerCarList(companyId);
        } else if(line.equals("2")) {
            createCar(companyId);
        } else {
            System.out.println();
            companyMenu(companyId);
        }
    }

    static void customerMenu(int customerId) {
        RentCarMenu rentCarMenu = new RentCarMenu();
        RentCar rentCar = customerDao.getRentedCar(customerId);
        ViewMenu.showCustomerMenu();

        String line = scanner.nextLine();
        System.out.println();
        if(line.equals("0")){
            mainMenu();
        } else if (line.equals("1")) {
            rentCarMenu.rentCar(rentCar, customerId);
        } else if(line.equals("2")) {
            rentCarMenu.returnCar(rentCar, customerId);
        } else if(line.equals("3")) {
            rentCarMenu.myRentedCar(rentCar, customerId);
        } else {
            System.out.println();
            customerMenu(customerId);
        }
    }

    private static void customerList() {
        System.out.println();
        List<Customer> customers = customerDao.getAllCustomers();
        ViewList.showCustomerList(customers);
        int customerId = 0;
        try{
            customerId = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            customerList();
        }
        if(customerId == 0) {
            mainMenu();
        } else if (customerId > customers.size()) {
            customerList();
        }
        customerMenu(customerId);
    }

    private static void managerCompanyList() {
        System.out.println();
        List<Company> companies = companyDao.getAllCompany();
        ViewList.showManagerCompanyList(companies);
        int companyId = 0;
        try{
            companyId = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            managerCompanyList();
        }
        if(companyId == 0) {
            managerMenu();
        } else if (companyId > companies.size()) {
            managerCompanyList();
        }
        Company company = companies.get(companyId - 1);
        System.out.println("\n'" + company.getName() + "' company:");
        companyMenu(company.getId());
    }

    void customerCompanyList(int customerId) {
        System.out.println();
        List<Company> companies = companyDao.getAllCompany();
        ViewList.showCustomerCompanyList(companies);

        int companyIndex = 0;
        try{
            companyIndex = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            customerCompanyList(customerId);
        }
        if(companyIndex == 0) {
            customerMenu(customerId);
        } else if (companyIndex > companies.size()) {
            customerCompanyList(customerId);
        }
        Company company = companies.get(companyIndex - 1);
        customerCarList(customerId, company.getId());
    }

    private static void managerCarList(int companyId) {
        System.out.println();
        List<Car> cars = carDao.getAllCars(companyId);
        if(cars.isEmpty()) {
            System.out.println("The car list is empty!\n");
            companyMenu(companyId);
        } else {
            System.out.println("Car list:");
            int i = 1;
            for (Car car : cars) {
                System.out.println(i++ + ". " + car.getName());
            }
        }
        System.out.println();
        companyMenu(companyId);
    }

    private void customerCarList(int customerId, int companyId) {
        System.out.println();
        List<Car> cars = carDao.getAllCars(companyId);
        ViewList.showCustomerCarList(cars, companyId);

        int carIndex = 0;
        try{
            carIndex = Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            managerCompanyList();
        }
        if(carIndex == 0) {
            customerCompanyList(customerId);
        }

        Car car = cars.get(carIndex - 1);
        String rentedCarName = car.getName();
        customerDao.addCar(customerId, companyId,  car.getId());

        System.out.println("\nYou rented '" + rentedCarName + "'");
        customerMenu(customerId);
    }

    private static void createCompany() {
        System.out.println("\nEnter the company name:");
        String companyName = scanner.nextLine();
        if(Verification.isExistCompany(companyName)) {
            createCompany();
        }
        companyDao.createCompany(companyName);
        System.out.println("The company was created!");
        managerMenu();
    }

    private static void createCar(int companyId) {
        System.out.println("\nEnter the car name:");
        String carName = scanner.nextLine();
        carDao.createCar(carName, companyId);
        System.out.println("The car was added!\n");
        companyMenu(companyId);
    }

    private static void createCustomers() {
        System.out.println("\nEnter the customer name:");
        String customerName = scanner.nextLine();
        if(Verification.isExistCustomer(customerName)) {
            createCustomers();
        }
        customerDao.createCustomer(customerName);
        System.out.println("The customer was added!\n");
        mainMenu();
    }

}
