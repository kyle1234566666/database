package com.example.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.example.dao.Test;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;



@RestController
@RequestMapping("/api")
public class Testcontroller {

	@Autowired
    private Test testMapper;
	private Workbook book;
	private int i;
	private Sheet sheet;
	private Cell cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10;

	
	@RequestMapping("/ziduan")	
	public JSONObject test(String id, String info) {
		int flag =testMapper.Addnumber(id,info);
		int dnum= testMapper.Selectorder(id);
		JSONObject json =new JSONObject();
		json.put("id", id);
		json.put("flag", flag);
		json.put("order", dnum);
		json.put("like", info);
		json.put("status", 200);
		return json;	
	}
	
	
//	@RequestMapping("/dbcreate")
//	public JSONObject dbcreate(String tbname, String liena, String lienb, String line1typ, String line2typ){
	//	testMapper.Createtb(tbname, liena, lienb,line1typ,line2typ);
	//	JSONObject dbn= new JSONObject();
	//	dbn.put("数据表名称", tbname);
	//	dbn.put("字段1名称", liena);
	//	dbn.put("字段2名称", lienb);
	//	dbn.put("字段1类型", line1typ);
	//	dbn.put("字段2类型", line2typ);
	//	return dbn;
		
	//}
	
	@RequestMapping("/realdb")
	public JSONObject rdbcreate(String dbname, String dtb, String linea, String lineb, String lineaty, String linebty, String atype, String btype){
		int db=testMapper.Gotdb(dbname);
		testMapper.crtt(dbname);
		testMapper.uuu(dtb,linea,lineaty,lineb,linebty,atype,btype);
		testMapper.confirmtya(dtb,linea,lineaty);
		testMapper.confirmtyb(dtb,lineb,linebty);
		JSONObject reald= new JSONObject();
		reald.put("数据库名称", dbname);
		reald.put("表名", dtb);
		reald.put("字段一名称", linea);
		reald.put("字段二名称", lineb);
		reald.put("字段一数据类型", lineaty);
		reald.put("字段二数据类型", linebty);
		return reald;
		
	}
	
	@RequestMapping("/creattable")
	public JSONObject crettable(String dbname, String dtb, String linea, String lineb, String lineaty, String linebty, String atype, String btype){
		testMapper.selectdb(dbname);
		testMapper.crettable(dtb,linea,lineaty,lineb,linebty,atype,btype);
		testMapper.exconfirmtya(dtb,linea,lineaty);
		testMapper.exconfirmtyb(dtb,lineb,linebty);
		JSONObject reald= new JSONObject();
		reald.put("数据库名称", dbname);
		reald.put("表名", dtb);
		reald.put("字段一名称", linea);
		reald.put("字段二名称", lineb);
		reald.put("字段一数据类型", lineaty);
		reald.put("字段二数据类型", linebty);
		return reald;
		
	}
	
	@RequestMapping("/newdatabase")
	public JSONObject newdatabase(String db, String tb){
		testMapper.newdatabase(db);
		testMapper.selectdb(db);
		testMapper.newtable(tb);
		JSONObject reald= new JSONObject();

		return reald;
		
	}
	
	@RequestMapping("/readexcel")
	public void save(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request,String db,String tb) throws Exception {
		    System.out.println(file+db+tb);
	        String trueName = file.getOriginalFilename();
	       testMapper.selectdb(db);
	        String path = "C:/Users/lenovo/Desktop/新建文件夹 (2)/Keith/";
	        //String path = "D:/daLongCode/linshi";
	        //testMapper.selectdb(exsitdb);
	        

	        File file1 = new File(path+trueName);
	        String uploadURL = path + trueName;
	        
	        
//	  
	      //  if (!file1.getParentFile().exists()) {
	         //   file1.mkdirs();
	       // }
	        try {
	            file.transferTo(file1);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        
	        try{
	        	book=Workbook.getWorkbook(new File(uploadURL));
	        	sheet=book.getSheet(0);
	        	i=1;
	        	while(true){
	        		cell1=sheet.getCell(0,i);
	        		cell2=sheet.getCell(1,i);
	        		cell3=sheet.getCell(2,i);
	        		cell4=sheet.getCell(3,i);
	        		cell5=sheet.getCell(4,i);
	        		cell6=sheet.getCell(5,i);
	        		cell7=sheet.getCell(6,i);
	        		cell8=sheet.getCell(7,i);
	        		cell9=sheet.getCell(8,i);
	        		cell10=sheet.getCell(9,i);
	        		//testMapper.storedata(tb,cell1.getContents(), cell2.getContents(), cell3.getContents(),cell4.getContents(),cell5.getContents(),cell6.getContents(),cell7.getContents(),cell8.getContents(),cell9.getContents(),cell10.getContents());
	        		
	        		
	        		if ("".equals(cell1.getContents())==true){break;};
	        		String alldata=cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()+"\t"+cell4.getContents()+"\t"+cell5.getContents()+"\t"+cell6.getContents()+"\t"+cell7.getContents()+"\t"+cell8.getContents()+"\t"+cell9.getContents()+"\t"+cell10.getContents()+"\t";
	        		
	        		System.out.println(cell1.getContents());
	        		//testMapper.selectdb(exsitdb);
	        		//testMapper.storedata(tb,cell1.getContents(), cell2.getContents(), cell3.getContents(),cell4.getContents(),cell5.getContents(),cell6.getContents(),cell7.getContents(),cell8.getContents(),cell9.getContents(),cell10.getContents());
					testMapper.storedata(tb,cell1.getContents(), cell2.getContents(), cell3.getContents(),cell4.getContents(),cell5.getContents(),cell6.getContents(),cell7.getContents(),cell8.getContents(),cell9.getContents(),cell10.getContents());

					i++;
	        		//testMapper.storedata(tb,cell1.getContents(), cell2.getContents(), cell3.getContents(),cell4.getContents(),cell5.getContents(),cell6.getContents(),cell7.getContents(),cell8.getContents(),cell9.getContents(),cell10.getContents());
	        	}
				book.close();
	        	//testMapper.storedata(tb,cell1.getContents(), cell2.getContents(), cell3.getContents(),cell4.getContents(),cell5.getContents(),cell6.getContents(),cell7.getContents(),cell8.getContents(),cell9.getContents(),cell10.getContents());
	        	
	        }
	        
	        catch(Exception e){}
	        
	        
	        
	        
	        
	    }
	
	   @RequestMapping("/")
      
 
			
	}
