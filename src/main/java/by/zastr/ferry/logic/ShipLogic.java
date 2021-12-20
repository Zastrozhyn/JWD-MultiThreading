package by.zastr.ferry.logic;

import by.zastr.ferry.entity.Car;

public interface ShipLogic {

	void loadCar(Car car);

	void ship();

	void unload();

	void comeBack();

}