package learn.second.app.admin.bulk.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import learn.second.domain.model.Admin;
import learn.second.domain.model.Cards;
import learn.second.domain.model.GameLogs;
import learn.second.domain.model.GameSettings;
import learn.second.domain.model.InitialDataSettings;
import learn.second.domain.model.RePasswordPool;
import learn.second.domain.model.RegisterPool;
import learn.second.domain.model.UserDrawWait;
import learn.second.domain.model.Users;
import learn.second.domain.service.admin.AdminService;
import learn.second.domain.service.cards.CardsService;
import learn.second.domain.service.datasetting.InitialDataSettingsService;
import learn.second.domain.service.gamelogs.GameLogsService;
import learn.second.domain.service.gamesettings.GameSettingsService;
import learn.second.domain.service.pool.RegisterPoolService;
import learn.second.domain.service.repass.RePasswordPoolService;
import learn.second.domain.service.userdraw.UserDrawWaitService;
import learn.second.domain.service.users.UsersService;

@Component
public class ExportExcelView extends AbstractXlsxView {

	@Autowired
	AdminService adminService;
	@Autowired
	CardsService cardsService;
	@Autowired
	GameLogsService gamelogsService;
	@Autowired
	GameSettingsService gameSettingsService;
	@Autowired
	InitialDataSettingsService initialDataSettingsService;
	@Autowired
	UserDrawWaitService userDrawWaitService;
	@Autowired
	UsersService usersService;
	@Autowired
	RePasswordPoolService rePasswordPoolService;
	@Autowired
	RegisterPoolService  registerPoolService;

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook book, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Date date = new Date();
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd hh-dd");

		String title = "CardGame";
		String fileExtension = ".xlsx";
		String exportDate = "_" + sfd.format(date);
		String filename = title + exportDate + fileExtension;

		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		// 出力するデータ
		List<Admin> adminList = adminService.findAll();
		List<Cards> cardsList = cardsService.findAll();
		List<GameLogs> gamelogsList = gamelogsService.findAll();
		List<GameSettings> gameSettingsList = gameSettingsService.findAll();
		List<InitialDataSettings> initialDataSettingsList = initialDataSettingsService.findAll();
		List<UserDrawWait> userDrawWaitList = userDrawWaitService.findAll();
		List<Users> usersList = usersService.findAll();
		List<RePasswordPool> rePasswordPoolList =
				rePasswordPoolService.findAll();
		List<RegisterPool> registerPoolList = registerPoolService.findAll();

		// シート生成
		setModelItem(book, adminList);
		setModelItem(book, cardsList);
		setModelItem(book, gamelogsList);
		setModelItem(book, gameSettingsList);
		setModelItem(book, initialDataSettingsList);
		setModelItem(book, userDrawWaitList);
		setModelItem(book, usersList);
		setModelItem(book, rePasswordPoolList);
		setModelItem(book, registerPoolList);
	}

	private void setModelItem(Workbook book, List<? extends Object> modelList)
			throws FileNotFoundException, IOException {
		Sheet sheet = null;
		Row row;
		int rowNumber;
		Cell cell;
		int colNumber;

		if (modelList.size() > 0) {
			System.out.println(modelList.get(0).getClass());
		}

		try {
			if (modelList.size() > 0) {
				// シート作成
				sheet = book.createSheet();
				int sheetNum = book.getNumberOfSheets() - 1;
				String sheetName = modelList.get(0).getClass().getSimpleName();
				book.setSheetName(sheetNum, sheetName);
				sheet.setDefaultColumnWidth(12);

				// ヘッダ行の作成
				rowNumber = 0;
				colNumber = 0;
				row = sheet.createRow(rowNumber);

				for (Field field : modelList.get(0).getClass().getDeclaredFields()) {
					cell = row.createCell(colNumber++);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(field.getName());
				}

				// レコード作成
				for (int itemNum = 0; itemNum < modelList.size(); itemNum++) {
					rowNumber++;
					colNumber = 0;
					row = sheet.createRow(rowNumber);

					for (Field field : modelList.get(itemNum).getClass().getDeclaredFields()) {

						cell = row.createCell(colNumber++);
						cell.setCellType(CellType.STRING);
						System.out.println(field.get(modelList.get(itemNum)));

						try {
							cell.setCellValue(field.get(modelList.get(itemNum)).toString());
						} catch (NullPointerException e) {
							cell.setCellValue("");
						} catch (Exception e) {
							System.out.println(e);
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}