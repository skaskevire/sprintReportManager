package com.epam.srm.files.write;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epam.srm.convert.Converter;
import com.epam.srm.entity.Report;

@Component("reportWriter")
public class ReportWriter implements Writer<Report> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportWriter.class);

	@Value("${srm.targetDir:.}")
	private String targetDirectory;

	@Autowired
	@Qualifier("reportConverter")
	private Converter<Report, XSSFWorkbook> reportConverter;

	@Override
	public void write(Report report, String filePath) {
		createDirectory(targetDirectory);

		try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
			XSSFWorkbook workbook = reportConverter.convert(report, null);
			workbook.write(outputStream);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		LOGGER.info("File " + filePath + "Successfully created!");
	}

	private void createDirectory(String dir) {
		File directory = new File(dir);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}
}
