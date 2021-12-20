package by.zastr.ferry.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class RiverFerry {
	
	public static final int MAX_WEIGHT_KG = 50000;
	public static final int MAX_PLACE = 30;
	public static final double LOADING_FACTOR = 0.9;
	private FerryStatus status;
	private int currentWeight;
	private int currentLoad;
	private int carsOnBoard; 
    private final List<Car> listCarOnBoard = new ArrayList<Car>();
	private static RiverFerry instance;
    private static final AtomicBoolean create = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
			 
	public enum FerryStatus {
		LOADING,
		UNLOADING,
		SHIPPING,
		FULL;
	}
	
	private RiverFerry() {
		this.currentLoad = 0;
		this.currentWeight = 0;
		this.status = FerryStatus.LOADING;
	}
	
	public static RiverFerry getInstance() {
		if (!create.get()) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new RiverFerry();
					create.set(true);
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
	
	public void unloadAllCar() {
		listCarOnBoard.removeAll(listCarOnBoard);
		carsOnBoard = 0;
		currentLoad = 0;
		currentWeight = 0;
	} 

	public FerryStatus getStatus() {
		return status;
	}

	public void setStatus(FerryStatus status) {
		this.status = status;
	}

	public int getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}

	public int getCurrentLoad() {
		return currentLoad;
	}

	public void setCurrentLoad(int currentLoad) {
		this.currentLoad = currentLoad;
	}
	
	public void addCarOnBoard() {
		carsOnBoard++;
	}
	
	public int getCarsOnBoard() {
		return carsOnBoard;
	}

	public void setCarsOnBoard(int carsOnBoard) {
		this.carsOnBoard = carsOnBoard;
	}

	public List<Car> getListCarOnBoard() {
		return listCarOnBoard;
	}
	
}
