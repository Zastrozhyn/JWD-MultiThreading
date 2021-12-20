package by.zastr.ferry.util;

public class IdGenerator {
	private static int currentId=0;
	private IdGenerator() {}
	public static int generateId() {
		currentId++;
		return currentId;
	}


}
