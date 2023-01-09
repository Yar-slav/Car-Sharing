package carsharing.dao;

import carsharing.dto.Company;
import java.util.List;
import java.util.Map;

public interface CompanyDao {

    boolean createCompany(String name);

    List<Company> getAllCompany();

    boolean ifCompanyAlreadyExist(String name);
}
