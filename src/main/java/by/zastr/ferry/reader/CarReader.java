package by.zastr.ferry.reader;

import java.util.List;

import by.zastr.ferry.exception.FerryException;

public interface CarReader {

	List<String> readFile(String file) throws FerryException;

}