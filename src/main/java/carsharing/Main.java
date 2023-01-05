package carsharing;

import carsharing.dao.Implementation.CarDaoImpl;
import carsharing.dao.Implementation.CompanyDaoImpl;
import carsharing.dao.Implementation.CustomerDaoImpl;

public class Main {

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        Menu menu = new Menu(
                dbConnection,
                new CompanyDaoImpl(dbConnection),
                new CarDaoImpl(dbConnection),
                new CustomerDaoImpl(dbConnection));
        menu.mainMenu();
    }
}