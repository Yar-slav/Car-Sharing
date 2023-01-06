package carsharing.menu;

import carsharing.Verification;
import carsharing.ViewList;
import carsharing.dto.Company;
import java.util.List;

public class CompanyMenu {

    public void companyMenu(int companyId, Menu menu) {
        RentCarMenu rentCarMenu = new RentCarMenu();
        ViewMenu.showCompanyMenu();
        String line = menu.scanner.nextLine();
        switch (line) {
            case "0":
                menu.getManagerMenu().managerMenu(menu);
            case "1":
                menu.getManagerMenu().managerCarList(companyId, menu);
            case "2":
                rentCarMenu.createCar(companyId, menu);
            default: {
                System.out.println();
                companyMenu(companyId, menu);
            }
        }
    }

    public void managerCompanyList(Menu menu) {
        System.out.println();
        List<Company> companies = menu.getCompanyDao().getAllCompany();
        if (!ViewList.showCompanyListIfNotEmpty(companies)) {
            System.out.println("The company list is empty!");
            menu.getManagerMenu().managerMenu(menu);
        }

        int companyIndex = 0;
        try {
            companyIndex = Integer.parseInt(menu.scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not correct input entry");
            managerCompanyList(menu);
        }
        if (companyIndex == 0) {
            menu.getManagerMenu().managerMenu(menu);
        } else if (companyIndex > companies.size()) {
            managerCompanyList(menu);
        }
        Company company = companies.get(companyIndex - 1);
        System.out.println("\n'" + company.getName() + "' company:");
        companyMenu(company.getId(), menu);
    }

    void createCompany(Menu menu) {
        Verification verification = new Verification(menu.getCustomerDao(), menu.getCompanyDao());
        System.out.println("\nEnter the company name:");
        String companyName = menu.scanner.nextLine();
        if (verification.isExistCompany(companyName)) {
            createCompany(menu);
        }
        menu.getCompanyDao().createCompany(companyName);
        System.out.println("The company was created!");
        menu.getManagerMenu().managerMenu(menu);
    }
}
