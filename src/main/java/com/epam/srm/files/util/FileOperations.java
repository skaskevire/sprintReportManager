package com.epam.srm.files.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileOperations {

	private static final String TIME_FORMAT = "_HH.MM.SS";

	public static String createOutputFilePath(String fileName, String targetDirectory) {
		return new StringBuilder().append(targetDirectory).append("/").append(fileName)
				.append(".xlsx").toString();
	}

	public static String createOutputFileName(String fileName) {
		return new StringBuilder().append(fileName)
				.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMAT))).toString();
	}
}
