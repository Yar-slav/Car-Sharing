package carsharing;

import carsharing.dao.CompanyDao;
import carsharing.dao.CustomerDao;
import carsharing.dto.Company;
import carsharing.dto.Customer;

public class Verification {
   private final CustomerDao customerDao;
   private final CompanyDao companyDao;

    public Verification(CustomerDao customerDao, CompanyDao companyDao) {
        this.customerDao = customerDao;
        this.companyDao = companyDao;
    }

    boolean isExistCustomer(String customerName) {
        for (Customer customer : customerDao.getAllCustomers()) {
            if (customerName.equals(customer.getName())) {
                System.out.println("Customer with this name has already exist");
                return true;
            }
        }
        return false;
    }

    boolean isExistCompany(String companyName) {
        for (Company company : companyDao.getAllCompany()) {
            if (companyName.equals(company.getName())) {
                System.out.println("Company has already exist");
                return true;
            }
        }
        return false;
    }
}
