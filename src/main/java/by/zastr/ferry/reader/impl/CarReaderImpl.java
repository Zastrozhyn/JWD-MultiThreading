package by.zastr.ferry.reader.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.ferry.exception.FerryException;
import by.zastr.ferry.reader.CarReader;


public class CarReaderImpl implements CarReader {
	private static Logger logger=LogManager.getLogger();
	@Override
	public List<String> readFile(String file) throws FerryException {
		if (file == null || file.isBlank()) {
			logger.log(Level.ERROR, "File name is null or empty");
            throw new FerryException("File name is null or empty");
        }
        List<String> carList;
        Path dataFile = Paths.get(file);
        try (Stream<String> dataStream = Files.lines(dataFile)){
            carList = dataStream.collect(Collectors.toList());
        } catch (IOException e) {
        	logger.log(Level.ERROR, "Read file error"+ e);
            throw new FerryException(String.format("Error during reading file %s", dataFile.getFileName()), e);
        }
        logger.log(Level.INFO, "Read file is successful");
        return carList;
	}
		

}
