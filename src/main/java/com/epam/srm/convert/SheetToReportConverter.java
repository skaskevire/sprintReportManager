package com.epam.srm.convert;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import com.epam.srm.entity.Report;
import com.epam.srm.entity.ReportLine;

@Component("sheetConverter")
public class SheetToReportConverter implements Converter<Sheet, Report>{

	@Override
	public Report convert(Sheet sheet, String name) {
		Iterator<Row> iterator = sheet.iterator();
		iterator.next();
		Report report = new Report(name);
		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			ReportLine rl = new ReportLine();
			try
			{
				if(currentRow.getCell(0) == null)
				{
					break;
				}
				rl.setTaskName(currentRow.getCell(0).getStringCellValue());
				rl.setStory(currentRow.getCell(1).getStringCellValue());
				rl.setSprint(currentRow.getCell(2).getStringCellValue());
				rl.setTag(currentRow.getCell(3).getStringCellValue());
				rl.setState(currentRow.getCell(4).getStringCellValue());
				rl.setEstimate(zeroIfBlank(currentRow.getCell(5)));
				rl.setTodo(zeroIfBlank(currentRow.getCell(6)));
				rl.setActual(zeroIfBlank(currentRow.getCell(7)));				
				rl.setOwner(currentRow.getCell(8).getStringCellValue());
	
				rl.setProject(currentRow.getCell(9).getStringCellValue());
				rl.setEstimationVariance(currentRow.getCell(10).getNumericCellValue());
				report.addReportLine(rl);
			}
			catch (NoSuchElementException e)
			{
				break;
			}
		}
		return report;
	}
	
	private double zeroIfBlank(Cell cell)
	{
		double result = 0d;
		if(cell!=null)
		{
			result = cell.getNumericCellValue();
		}

		return result;
	}

}
