package carsharing;

import carsharing.dto.Company;
import carsharing.dto.Customer;

public class Verification {
    static boolean isExistCustomer(String customerName) {
        for (Customer customer: Menu.customerDao.getAllCustomers()){
            if(customerName.equals(customer.getName())) {
                System.out.println("Customer with this name has already exist");
                return true;
            }
        }
        return false;
    }

    static boolean isExistCompany(String companyName) {
        for (Company company: Menu.companyDao.getAllCompany()){
            if(companyName.equals(company.getName())) {
                System.out.println("Company has already exist");
                return true;
            }
        }
        return false;
    }

    static boolean isIncorrectInputEntry(String line) {
        try{
            Integer.parseInt(line);
        }catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            return true;
        }
        return false;
    }

    static boolean isInputBack (String line) {
        if (line.equals("0")) {
            return true;
        }
        return false;
    }
}
