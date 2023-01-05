package carsharing.model;

public class RentCar {
    private String carName;
    private String companyName;

    public RentCar(String carName, String companyName) {
        this.carName = carName;
        this.companyName = companyName;
    }

    public RentCar() {
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
