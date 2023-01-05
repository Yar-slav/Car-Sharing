package carsharing.dao;

import carsharing.dto.Company;
import java.util.List;

public interface CompanyDao {

    boolean createCompany(String name);

    List<Company> getAllCompany();
}
