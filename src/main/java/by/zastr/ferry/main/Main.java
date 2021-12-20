package by.zastr.ferry.main;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import by.zastr.ferry.entity.Car;
import by.zastr.ferry.exception.FerryException;
import by.zastr.ferry.parser.CarParser;
import by.zastr.ferry.parser.impl.CarParserImpl;
import by.zastr.ferry.reader.CarReader;
import by.zastr.ferry.reader.impl.CarReaderImpl;

public class Main {

	public static void main(String[] args) throws FerryException {
		CarReader reader = new CarReaderImpl();
		CarParser parser = new CarParserImpl();
		String fileString = "resources\\car";
		List<Car> listCar = parser.buildListCar(reader.readFile(fileString));
		ExecutorService executor = Executors.newScheduledThreadPool(listCar.size());
		for (int i = 0; i < listCar.size(); i++) {
			Thread carThread = new Thread(listCar.get(i));
			executor.submit(carThread);
		}
		executor.shutdown();
	}

}
