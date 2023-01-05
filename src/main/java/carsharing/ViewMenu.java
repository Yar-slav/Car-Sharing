package carsharing;

public class ViewMenu {

    static void showMainMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
    }
    static void showManagerMenu() {
        System.out.println( "\n1. Company list");
        System.out.println( "2. Create a company");
        System.out.println( "0. Back");
    }

    static void showCompanyMenu(){
        System.out.println("\n1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
    }

    static void showCustomerMenu() {
        System.out.println("\n1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
    }
}
