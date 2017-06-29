package learn.second.app.admin.bulk.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BulkExportController {



	@RequestMapping(value = "/admin/bulk/export/all",params= "excel", method = { RequestMethod.GET, RequestMethod.POST })
	public String sample(Locale locale, Model model, HttpSession session) throws FileNotFoundException, IOException {

	        return "exportExcelView";
	}

}