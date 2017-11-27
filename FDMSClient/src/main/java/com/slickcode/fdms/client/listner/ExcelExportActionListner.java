package com.slickcode.fdms.client.listner;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public abstract class ExcelExportActionListner extends FdmsActionListner {

	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;
	private String inputFile = "E:/temp/fd.xls";

	@Override
	public void onSuccess() {
	}

	@Override
	public boolean performAction(ActionEvent e) {
		try {
			File file = new File(inputFile);
			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setLocale(new Locale("en", "EN"));

			WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
			workbook.createSheet("Report", 0);
			WritableSheet excelSheet = workbook.getSheet(0);
			createLabel(excelSheet);
			createContent(excelSheet);	

			workbook.write();
			workbook.close();
		} catch (Exception exception) {
			return false;
		}
		return true;
	}

	private void createLabel(WritableSheet sheet) throws WriteException {
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		times = new WritableCellFormat(times10pt);
		times.setWrap(true);

		WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);

		timesBoldUnderline.setWrap(true);

		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderline);

		List<String> headerList = populateHeaderList();
		int size = headerList.size();
		for (int i = 0; i < size; i++) {
			addCaption(sheet, i, 0, headerList.get(i));
		}
	}

	public abstract void createContent(WritableSheet sheet) throws WriteException;

	public abstract List<String> populateHeaderList();

	public void addCaption(WritableSheet sheet, int column, int row, String s) throws WriteException {
		Label label = new Label(column, row, s, timesBoldUnderline);
		sheet.addCell(label);
	}

	public void addLabel(WritableSheet sheet, int column, int row, String s) throws WriteException {
		Label label = new Label(column, row, s, times);
		sheet.addCell(label);
	}
}
