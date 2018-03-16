package com.epam.srm.files.read;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.epam.srm.convert.Converter;
import com.epam.srm.entity.Report;
import com.epam.srm.files.filter.Filter;

@Component
public class ReportReader implements Reader<List<Report>> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportReader.class);

	@Autowired
	private Filter<List<File>> cachingXlsxFileFilter;

	@Value("${srm.sourceSheetName:.}")
	private String sourceSheetName;

	@Autowired
	@Qualifier("sheetConverter")
	private Converter<Sheet, Report> sheetConverter;

	@Override
	public List<Report> read(String direcroryPath) {
		File folder = new File(direcroryPath);
		List<File> filteredFiles = cachingXlsxFileFilter.filter(Arrays.asList(folder.listFiles()));
		List<Report> reports = new ArrayList<>();
		for (File f : filteredFiles) {
			try (FileInputStream inputStream = new FileInputStream(f);
					XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
				reports.add(sheetConverter.convert(workbook.getSheet(sourceSheetName), f.getName()));
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

		return reports;
	}

}
