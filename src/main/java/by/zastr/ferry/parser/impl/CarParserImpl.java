package by.zastr.ferry.parser.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.ferry.entity.Car;
import by.zastr.ferry.entity.Car.CarType;
import by.zastr.ferry.exception.FerryException;
import by.zastr.ferry.parser.CarParser;

public class CarParserImpl implements CarParser {
	private static final String DELIMETER = "\s+";
	private static Logger logger=LogManager.getLogger();
	
	@Override
	public List<Car> buildListCar(List<String> stringList) throws FerryException {
		if (stringList == null) {
			logger.log(Level.ERROR, "StringList is null");
            throw new FerryException("StringList is null");
        }
		List<Car> listCar = new ArrayList<Car>();
		for (int i = 0; i < stringList.size(); i++) {
			String[] carParameter = stringList.get(i).split(DELIMETER);
			Car car = buildCar(carParameter);
			listCar.add(car);
		}
		logger.log(Level.INFO, "List car build successful");
		return listCar;
	}
	 private Car buildCar (String[] carParameter){
		 CarType type = CarType.valueOf(carParameter[0].toUpperCase());
		 int weight = Integer.parseInt(carParameter[1]);
		 Car car = new Car();
		 car.setType(type);
		 car.setWeight(weight);
		 return car;
	 }

}
