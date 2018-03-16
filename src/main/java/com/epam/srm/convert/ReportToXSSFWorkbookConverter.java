package com.epam.srm.convert;

import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.epam.srm.entity.Report;
import com.epam.srm.entity.ReportLine;

@Component("reportConverter")
public class ReportToXSSFWorkbookConverter implements Converter<Report, XSSFWorkbook> {
	private static final String SOURCE_TABLE_AREA_REF_PATTERN = "A1:%s%s";
	private static final String TARGET_PIVOT_TABLE_START_POINT = "A1";

	@Override
	public XSSFWorkbook convert(Report report, String name) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet valueSheet = workbook.createSheet(report.getName() + "_Values");
		int rowNum = 0;
		Row row = valueSheet.createRow(rowNum++);
		addCellWithHeaders(row);
		for (ReportLine reportLine : report.getReportLines()) {
			row = valueSheet.createRow(rowNum++);
			addCellWithValues(row, reportLine);
		}

		XSSFSheet pivotTableSheet = workbook.createSheet(report.getName() + "_PivotTable");

		AreaReference source = new AreaReference(
				String.format(SOURCE_TABLE_AREA_REF_PATTERN, "K", (report.getReportLines().size() + 1)));
		CellReference position = new CellReference(TARGET_PIVOT_TABLE_START_POINT);

		XSSFPivotTable pivotTable = pivotTableSheet.createPivotTable(source, position, valueSheet);

		pivotTable.addRowLabel(2);
		pivotTable.addRowLabel(3);
		pivotTable.addRowLabel(1);
		pivotTable.addRowLabel(0);

		pivotTable.addColumnLabel(DataConsolidateFunction.SUM, 5, "Sum Of Estimate");
		pivotTable.addColumnLabel(DataConsolidateFunction.SUM, 6, "Sum Of To Do");
		pivotTable.addColumnLabel(DataConsolidateFunction.SUM, 7, "Sum Of Actual");
		pivotTable.addColumnLabel(DataConsolidateFunction.AVERAGE, 10,
				"Average of Estimation variance((act+todo)/estimated)");

		return workbook;
	}

	private void addCellWithHeaders(Row row) {
		int colNum = 0;
		Cell cell = row.createCell(colNum++);
		cell.setCellValue("Name");

		cell = row.createCell(colNum++);
		cell.setCellValue("Work Product");

		cell = row.createCell(colNum++);
		cell.setCellValue("Iteration");

		cell = row.createCell(colNum++);
		cell.setCellValue("Tags");

		cell = row.createCell(colNum++);
		cell.setCellValue("Scheduled State");

		cell = row.createCell(colNum++);
		cell.setCellValue("Estimate");

		cell = row.createCell(colNum++);
		cell.setCellValue("To Do");

		cell = row.createCell(colNum++);
		cell.setCellValue("Actuals");

		cell = row.createCell(colNum++);
		cell.setCellValue("Owner");

		cell = row.createCell(colNum++);
		cell.setCellValue("Project");

		cell = row.createCell(colNum++);
		cell.setCellValue("Estimation variance ((act+todo)/estimated)");
	}

	private void addCellWithValues(Row row, ReportLine p) {
		int colNum = 0;
		Cell cell = row.createCell(colNum++);
		cell.setCellValue(p.getTaskName());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getStory());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getSprint());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getTag());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getState());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getEstimate());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getTodo());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getActual());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getOwner());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getProject());

		cell = row.createCell(colNum++);
		cell.setCellValue(p.getEstimationVariance());
	}

}
