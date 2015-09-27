package ru.todo100.activer.controller;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.lang.InterruptedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.todo100.activer.util.DBRequest;


@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/mysql")
public class Mysql {
	
	@Autowired
	DBRequest dBRequest;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws InterruptedException {
		return "mysql/index";
	}
	
	@RequestMapping(value = "/sqlfile", method = RequestMethod.GET)
	public String mysqlGet(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws InterruptedException {
		return "mysql/sqlfile";
	}

	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	public String tables(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model) throws InterruptedException, SQLException {
		ResultSet rs = dBRequest.query("SHOW TABLES;");
		ArrayList<String> tables = new ArrayList<String>();
		try {
			while(rs.next()) {
				tables.add(rs.getString(0));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("tables", tables);
		
		return "mysql/tables";
	}
	
	@RequestMapping(value = "/sqlfile", method = RequestMethod.POST)
	public String mysqlPost(HttpServletResponse response,HttpServletRequest request,Locale locale,Model model, Part sqlfile) throws InterruptedException,IOException, SQLException {
		response.setContentType("text/plain");
		ArrayList<String> sqlCommands = dBRequest.getSqlFromInputStream(sqlfile.getInputStream());
		for (String command: sqlCommands) {
			response.getWriter().println(command);
			response.getWriter().println("--------");
			dBRequest.query(command);
		}
		//return "mysql/index";
		return null;
	}
}

