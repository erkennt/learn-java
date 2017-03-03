package controller;

import java.util.List;
import java.util.Map;

import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * DDL
     * CREATE TABLE `sample` (
     *   `id` int(11) NOT NULL AUTO_INCREMENT,
     *   `name` varchar(20) DEFAULT NULL,
     *   PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
     *
     * DML
     * insert into sample values(1,'test');
     */
    @RequestMapping(value = "/UpdateController", method = RequestMethod.POST)
    public String getDetails(
    	    @RequestParam(value="upId", required=true) String Id,
    	        @RequestParam(value="upName", required=false) String Name,
    	        	Model model){
    					updateTable(Id,Name);
    					selectTable(model);

    			        model.addAttribute("message", "Update successful");
    					return "home";
    }

    private void updateTable(String Id, String Name){
    	jdbcTemplate.update("Update table1 SET name = \""+Name + "\" Where Id = \""+ Id + "\"");
    }

    private void selectTable(Model model){
        List<Map<String, Object>>  list = jdbcTemplate.queryForList("select * from table1");

        model.addAttribute("data", list);
    }

}