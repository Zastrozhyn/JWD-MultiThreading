package by.zastr.ferry.logic.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.ferry.entity.Car;
import by.zastr.ferry.entity.RiverFerry;
import by.zastr.ferry.entity.RiverFerry.FerryStatus;
import by.zastr.ferry.logic.ShipLogic;

public class FerryLogic implements ShipLogic {
	private RiverFerry ferry = RiverFerry.getInstance();
    private static ReentrantLock lock = new ReentrantLock();
	private static final Logger logger = LogManager.getLogger();
	private static final int TIMETOWAITCAR = 1;
		
	
	@Override
	public void loadCar (Car car){
		if (ferry.getStatus().equals(FerryStatus.LOADING)) {
			lock.lock();
			try {
				if (ferry.getCurrentLoad() + car.getType().getSize() < RiverFerry.MAX_PLACE 
						&& ferry.getCurrentWeight() + car.getWeight() < RiverFerry.MAX_WEIGHT_KG) {
					ferry.setCurrentLoad(car.getType().getSize() + ferry.getCurrentLoad());
					ferry.setCurrentWeight(car.getWeight() + ferry.getCurrentWeight());
					ferry.getListCarOnBoard().add(car);
					ferry.addCarOnBoard();
					car.setOnBoard(true);
				}
				if (ferry.getCurrentLoad() >= RiverFerry.MAX_PLACE * RiverFerry.LOADING_FACTOR 
					|| ferry.getCurrentWeight() >= RiverFerry.MAX_WEIGHT_KG * RiverFerry.LOADING_FACTOR) {
					ferry.setStatus(FerryStatus.FULL);
					ship();
				}
				if (lock.getQueueLength() == 0) {
					try {
						TimeUnit.SECONDS.sleep(TIMETOWAITCAR);
						if (lock.getQueueLength() == 0) {
							ship();
						}
					} catch (InterruptedException e) {
						logger.warn("Warn!" + e);
					}
				}
			} finally {
				lock.unlock();
			}
		}
	}
	
	@Override
	public void ship() {
		ferry.setStatus(FerryStatus.SHIPPING);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			logger.warn("Warn!" + e);
		}
        logger.log(Level.INFO, "Ferry load= " +ferry.getCurrentWeight() + " Cars on board= " 
		+ ferry.getCarsOnBoard() + " Cars:" + ferry.getListCarOnBoard().toString());
        unload();
	}
	
	@Override
	public void unload() {
		ferry.setStatus(FerryStatus.UNLOADING);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			logger.warn("Warn!" + e);
		}
        logger.log(Level.INFO, "Cars delivered");
        ferry.unloadAllCar();
        comeBack();
	}
	
	@Override
	public void comeBack() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			logger.warn("Warn!" + e);
		}
		logger.log(Level.INFO, "Ferry ready to load");
		ferry.setStatus(FerryStatus.LOADING);
	}
}
