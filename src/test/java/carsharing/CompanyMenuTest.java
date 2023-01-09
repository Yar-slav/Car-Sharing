package carsharing;

import carsharing.dao.Connection;
import carsharing.dto.Company;
import carsharing.menu.Menu;
import com.ginsberg.junit.exit.ExpectSystemExit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CompanyMenuTest extends Connection {

    @Test
    @ExpectSystemExit
    void companyMenuManagerCarListEmpty() {
        String input = "1\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        menu.getCompanyMenu().companyMenu(company.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void companyMenuDefault() {
        String input = "incorrect\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        menu.getCompanyMenu().companyMenu(5, menu);
    }

    @Test
    @ExpectSystemExit
    void companyMenuManagerCarList() {
        String input = "1\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);
        carDao.createCar("Car 1", company.getId());
        carDao.createCar("Car 2", company.getId());

        menu.getCompanyMenu().companyMenu(company.getId(), menu);
    }

    @Test
    @ExpectSystemExit
    void companyMenuCreateCar() {
        String input = "2\nCar\n0\n0\n0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Menu menu = new Menu(new DBConnection(), companyDao, carDao, customerDao);
        companyDao.createCompany("Company 1");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        menu.getCompanyMenu().companyMenu(company.getId(), menu);
    }
}
