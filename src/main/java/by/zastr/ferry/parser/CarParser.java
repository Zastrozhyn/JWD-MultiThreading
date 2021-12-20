package by.zastr.ferry.parser;

import java.util.List;

import by.zastr.ferry.entity.Car;
import by.zastr.ferry.exception.FerryException;

public interface CarParser {

	List<Car> buildListCar(List<String> stringList) throws FerryException;

}