package carsharing;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import carsharing.dao.Connection;
import carsharing.dto.Company;
import carsharing.dto.Customer;
import java.util.List;
import org.junit.jupiter.api.Test;

class VerificationTest extends Connection {

    @Test
    void isExistCustomer() {
        Verification verification = new Verification(customerDao, companyDao);
        customerDao.createCustomer("Customer 1");
        customerDao.createCustomer("Customer 2");
        customerDao.createCustomer("Customer 3");
        List<Customer> allCustomers = customerDao.getAllCustomers();
        Customer customer = allCustomers.get(0);

        assertTrue(verification.isExistCustomer(customer.getName()));
    }

    @Test
    void isNotExistCustomer() {
        Verification verification = new Verification(customerDao, companyDao);
        customerDao.createCustomer("Customer 1");
        customerDao.createCustomer("Customer 2");

        assertFalse(verification.isExistCustomer("Customer 3"));
    }

    @Test
    void isExistCompany() {
        Verification verification = new Verification(customerDao, companyDao);
        companyDao.createCompany("Company 1");
        companyDao.createCompany("Company 2");
        List<Company> allCompany = companyDao.getAllCompany();
        Company company = allCompany.get(0);

        assertTrue(verification.isExistCompany(company.getName()));
    }

    @Test
    void isNotExistCompany() {
        Verification verification = new Verification(customerDao, companyDao);
        companyDao.createCompany("Company 1");
        companyDao.createCompany("Company 2");

        assertFalse(verification.isExistCompany("Company 3"));
    }
}