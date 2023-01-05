package carsharing.dao;

import carsharing.dto.Car;
import java.util.List;

public interface CarDao {

    boolean createCar(String name, int company_id);

    List<Car> getAllCars(int companyId);
}
