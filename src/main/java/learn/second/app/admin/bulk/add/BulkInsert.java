package learn.second.app.admin.bulk.add;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
public class BulkInsert {

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

	// Workbook book = new HSSFWorkbook(file.getInputStream());
	private static Workbook wb = null;

	// テーブルデータの格納
	private static Map<String, List<?>> items = new HashMap<String, List<?>>();

	// Insertに失敗した行を格納する
	private static Map<String, List<Integer>> errorsMap = new LinkedHashMap<String, List<Integer>>();

	private static final Map<String, Class> builtInMap = new HashMap<String, Class>();
	{
		builtInMap.put("int", Integer.class);
		builtInMap.put("long", Long.class);
		builtInMap.put("double", Double.class);
		builtInMap.put("float", Float.class);
		builtInMap.put("bool", Boolean.class);
		builtInMap.put("char", Character.class);
		builtInMap.put("byte", Byte.class);
		builtInMap.put("void", Void.class);
		builtInMap.put("short", Short.class);
	}

	public void insertBulk(MultipartFile file) throws EncryptedDocumentException, InvalidFormatException, IOException {
		wb = WorkbookFactory.create(file.getInputStream());
		loadSheetData();
		insertSheetData();
	}

	public Map<String, List<Integer>> getErrors() {

		return errorsMap;
	}

	public void loadSheetData() throws IOException {
		// シートごとにデータを取得する
		for (Sheet sheet : wb) {
			// シート名
			String sheetName = sheet.getSheetName();

			// カラムチェック用のモデル
			Object columnModel = getInstance(sheetName);

			// モデルを格納するList
			List<Object> modelList = new ArrayList<Object>();

			// モデルの変数を配列化(記述した順に依存するためテーブルと同じ順序でBeanに記述する必要あり）
			Field[] field = columnModel.getClass().getDeclaredFields();

			// ヘッダーRowのチェック
			int head = sheet.getFirstRowNum();
			for (Cell cell : sheet.getRow(0)) {
				if (!cell.getStringCellValue().equals(field[head].getName().toString())) {
					// ヘッダー項目が正しくない
					break;
				}
				head++;
			}

			// レコードをリストに挿入する
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				int cellNum = 0;

				Object somethingModel = getInstance(sheetName);
				for (Cell cell : sheet.getRow(i)) {
					try {
						// 対象セルに対応するBean変数のGetter/Setterを取得する
						PropertyDescriptor nameProp = new PropertyDescriptor(field[cellNum].getName().toString(),
								somethingModel.getClass());
						Method nameGetter = nameProp.getReadMethod();
						Method nameSetter = nameProp.getWriteMethod();

						String columnType = nameGetter.getReturnType().getName();

						// セルの値をキャスト
						Object cellValue = castCellValue(columnType, cell.getStringCellValue());

						// Beanへ格納する
						nameSetter.invoke(somethingModel, cellValue);
						cellNum++;
					} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException | ClassNotFoundException
							| ParseException e) {
						e.printStackTrace();
						cellNum++;
						break;
					}
				}
				modelList.add(somethingModel);
			}
			// モデルのListを格納する
			System.out.println(sheetName);
			items.put(sheetName, modelList);
		}

		wb.close();
	}

	public void insertSheetData() {

		// テーブル制約に基いてモデルを順次Insertさせる
		// GameLogs
		if (items.containsKey(GameLogs.class.getSimpleName())) {

			errorsMap.put(GameLogs.class.getSimpleName(),
					gamelogsService.insertGameLogsList((List<GameLogs>) items.get(GameLogs.class.getSimpleName())));
		}

		// InitialDataSettings
		if (items.containsKey(InitialDataSettings.class.getSimpleName())) {

			errorsMap.put(InitialDataSettings.class.getSimpleName(),
					initialDataSettingsService.insertInitialDataSettingsList(
							(List<InitialDataSettings>) items.get((InitialDataSettings.class.getSimpleName()))));
		}

		// Admin
		if (items.containsKey(Admin.class.getSimpleName())) {

			errorsMap.put(Admin.class.getSimpleName(),
					adminService.insertAdminList((List<Admin>) items.get(Admin.class.getSimpleName())));
		}

		// RegisterPool
		if (items.containsKey(RegisterPool.class.getSimpleName())) {

			errorsMap.put(RegisterPool.class.getSimpleName(),
					registerPoolService.inserRegisterPoolList((List<RegisterPool>) items.get(RegisterPool.class.getSimpleName())));
		}


		// RePasswordPool
		if (items.containsKey(RegisterPool.class.getSimpleName())) {

			errorsMap.put(RePasswordPool.class.getSimpleName(),
					rePasswordPoolService.inserRePasswordPoolList((List<RePasswordPool>) items.get(RePasswordPool.class.getSimpleName())));
		}

		// UserDrawWait
		if (items.containsKey(UserDrawWait.class.getSimpleName())) {

			errorsMap.put(UserDrawWait.class.getSimpleName(),
					userDrawWaitService.inserUserDrawWaitList((List<UserDrawWait>) items.get(UserDrawWait.class.getSimpleName())));
		}


		// GameSettings
		if (items.containsKey(GameSettings.class.getSimpleName())) {

			errorsMap.put(GameSettings.class.getSimpleName(), gameSettingsService
					.insertGameSettingsList((List<GameSettings>) items.get(GameSettings.class.getSimpleName())));
		}

		// Users
		if (items.containsKey(Users.class.getSimpleName())) {

			errorsMap.put(Users.class.getSimpleName(),
					usersService.insertUsersList((List<Users>) items.get(Users.class.getSimpleName())));
		}
		// Cards
		if (items.containsKey(Cards.class.getSimpleName())) {

			errorsMap.put(Cards.class.getSimpleName(),
					cardsService.insertCardsList((List<Cards>) items.get(Cards.class.getSimpleName())));
		}

	}

	private Object castCellValue(String className, String cellValue) throws ClassNotFoundException, ParseException {
		Class<?> clazz = null;

		if (builtInMap.containsKey(className)) {
			clazz = builtInMap.get(className);
		} else {
			clazz = Class.forName(className);
		}
		Object obj = null;

		if (StringUtils.isEmpty(cellValue)) {
			return null; // 空文字列はNULLを返す
		} else if (clazz == Long.class) {
			obj = Long.valueOf(cellValue);
		} else if (clazz == String.class) {
			obj = cellValue;
		} else if (clazz == Integer.class) {
			obj = Integer.valueOf(cellValue);
		} else if (clazz == Timestamp.class) {
			obj = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cellValue).getTime());
		} else if (clazz == Date.class) {
			obj = new Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cellValue).getTime());
		} else if (clazz == Boolean.class) {
			obj = Boolean.valueOf(cellValue);
		}

		return obj;
	}

	private Object getInstance(String sheetName) {
		try {

			Class<?> c;
			c = Class.forName("learn.second.domain.model." + sheetName);
			try {
				return c.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


}
