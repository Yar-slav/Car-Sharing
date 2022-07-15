package carsharing.dao;

import carsharing.dto.Company;

import java.util.List;

public interface CompanyDao {
    void createCompany(String name);
    List<Company> getAllCompany();
}
