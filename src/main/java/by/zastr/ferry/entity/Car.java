package by.zastr.ferry.entity;

import java.util.concurrent.TimeUnit;

import by.zastr.ferry.logic.ShipLogic;
import by.zastr.ferry.logic.impl.FerryLogic;
import by.zastr.ferry.util.IdGenerator;

public class Car implements Runnable{
	private int id;
	private CarType type;
	private int weight;
	private boolean onBoard = false;
	
	public enum CarType {
		CAR(1),
		TRUCK(4),
		VAN(2);
		private int size;
		private CarType(int size) {
			this.size = size;
		}
		
		public int getSize() {
			return size;
		}
		
	}
	
	public Car() {
		this.id = IdGenerator.generateId();
	}

	public void run() {
		ShipLogic ferryLogic = new FerryLogic();
		while (!onBoard) {
			try {
				TimeUnit.MILLISECONDS.sleep(300);
				ferryLogic.loadCar(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}

	public boolean isOnBoard() {
		return onBoard;
	}

	public void setOnBoard(boolean onBoard) {
		this.onBoard = onBoard;
	}

	public int getId() {
		return id;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id != other.id)
			return false;
		if (type != other.type)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append(", weight=");
		builder.append(weight);
		builder.append("]");
		return builder.toString();
	}

}
