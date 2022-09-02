package com.example.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.example.dao.Test;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class Testcontroller {

	@Autowired
	private Test testMapper;

	private Workbook book;
	private int i;
	private Sheet sheet;
	private Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9, cell10;


	@RequestMapping("/ziduan")
	public JSONObject test(String id, String info) {
		int flag = testMapper.Addnumber(id, info);
		int dnum = testMapper.Selectorder(id);
		JSONObject json = new JSONObject();
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
	public JSONObject rdbcreate(String dbname, String dtb, String linea, String lineb, String lineaty, String linebty, String atype, String btype) {
		int db = testMapper.Gotdb(dbname);
		testMapper.crtt(dbname);
		testMapper.uuu(dtb, linea, lineaty, lineb, linebty, atype, btype);
		testMapper.confirmtya(dtb, linea, lineaty);
		testMapper.confirmtyb(dtb, lineb, linebty);
		JSONObject reald = new JSONObject();
		reald.put("数据库名称", dbname);
		reald.put("表名", dtb);
		reald.put("字段一名称", linea);
		reald.put("字段二名称", lineb);
		reald.put("字段一数据类型", lineaty);
		reald.put("字段二数据类型", linebty);
		return reald;

	}

	@RequestMapping("/creattable")
	public JSONObject crettable(String dbname, String dtb, String linea, String lineb, String lineaty, String linebty, String atype, String btype) {
		testMapper.selectdb(dbname);
		testMapper.crettable(dtb, linea, lineaty, lineb, linebty, atype, btype);
		testMapper.exconfirmtya(dtb, linea, lineaty);
		testMapper.exconfirmtyb(dtb, lineb, linebty);
		JSONObject reald = new JSONObject();
		reald.put("数据库名称", dbname);
		reald.put("表名", dtb);
		reald.put("字段一名称", linea);
		reald.put("字段二名称", lineb);
		reald.put("字段一数据类型", lineaty);
		reald.put("字段二数据类型", linebty);
		return reald;

	}

	@RequestMapping("/newdatabase")
	public JSONObject newdatabase(String db, String tb) {
		testMapper.newdatabase(db);
		testMapper.selectdb(db);
		testMapper.newtable(tb);
		JSONObject reald = new JSONObject();

		return reald;

	}

	@RequestMapping("/readexcel")
	public void save(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, String db, String tb) throws Exception {
		System.out.println(file + db + tb);
		String trueName = file.getOriginalFilename();
		testMapper.selectdb(db);
		String path = "C:/Users/lenovo/Desktop/新建文件夹 (2)/Keith/";
		//String path = "D:/daLongCode/linshi";
		//testMapper.selectdb(exsitdb);


		File file1 = new File(path + trueName);
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


		try {
			book = Workbook.getWorkbook(new File(uploadURL));
			sheet = book.getSheet(0);
			i = 1;
			while (true) {
				cell1 = sheet.getCell(0, i);
				cell2 = sheet.getCell(1, i);
				cell3 = sheet.getCell(2, i);
				cell4 = sheet.getCell(3, i);
				cell5 = sheet.getCell(4, i);
				cell6 = sheet.getCell(5, i);
				cell7 = sheet.getCell(6, i);
				cell8 = sheet.getCell(7, i);
				cell9 = sheet.getCell(8, i);
				cell10 = sheet.getCell(9, i);
				//testMapper.storedata(tb,cell1.getContents(), cell2.getContents(), cell3.getContents(),cell4.getContents(),cell5.getContents(),cell6.getContents(),cell7.getContents(),cell8.getContents(),cell9.getContents(),cell10.getContents());


				if ("".equals(cell1.getContents()) == true) {
					break;
				}
				;
				String alldata = cell1.getContents() + "\t" + cell2.getContents() + "\t" + cell3.getContents() + "\t" + cell4.getContents() + "\t" + cell5.getContents() + "\t" + cell6.getContents() + "\t" + cell7.getContents() + "\t" + cell8.getContents() + "\t" + cell9.getContents() + "\t" + cell10.getContents() + "\t";

				System.out.println(cell1.getContents());
				//testMapper.selectdb(exsitdb);
				//testMapper.storedata(tb,cell1.getContents(), cell2.getContents(), cell3.getContents(),cell4.getContents(),cell5.getContents(),cell6.getContents(),cell7.getContents(),cell8.getContents(),cell9.getContents(),cell10.getContents());
				testMapper.storedata(tb, cell1.getContents(), cell2.getContents(), cell3.getContents(), cell4.getContents(), cell5.getContents(), cell6.getContents(), cell7.getContents(), cell8.getContents(), cell9.getContents(), cell10.getContents());

				i++;
				//testMapper.storedata(tb,cell1.getContents(), cell2.getContents(), cell3.getContents(),cell4.getContents(),cell5.getContents(),cell6.getContents(),cell7.getContents(),cell8.getContents(),cell9.getContents(),cell10.getContents());
			}
			book.close();
			//testMapper.storedata(tb,cell1.getContents(), cell2.getContents(), cell3.getContents(),cell4.getContents(),cell5.getContents(),cell6.getContents(),cell7.getContents(),cell8.getContents(),cell9.getContents(),cell10.getContents());

		} catch (Exception e) {
		}


	}

	@RequestMapping("/login")
	public JSONObject login(String username, String password) {

		Map<String, Object> a = testMapper.login(username, password);


		List<Map<String, Object>> b = testMapper.checkusername(username);

		List<Map<String, Object>> c = testMapper.checkpassword(password);


		String d = "登陆成功";
		int e = 0;
		if (a == null) {
			if (b.size() == 0) {
				d = "您所输入的用户名未注册";
				e = 0;

			} else if (b.size() != 0 && c.size() == 0) {
				d = "密码错误";
				e = 0;
			}
		} else {
			d = "登陆成功";
			e = 1;
		}

		JSONObject logininfo = new JSONObject();
		// logininfo.put("用户名", uname);
		// logininfo.put("密码", psword);
		logininfo.put("code", e);
		logininfo.put("data", a);
		logininfo.put("msg", d);


		return logininfo;

	}

	@RequestMapping("/brandnewdatabase")
	public JSONObject brandnewdatabase(String dbname, String tbname) {
		String c = new String();


		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
		//String dbname = String.valueOf(info.get("数据库名"));
		//String tbname = String.valueOf(info.get("表名"));
		int i1 = testMapper.selectdb("intern");
		List<Map<String, Object>> a = testMapper.checkdbname(dbname);
		List<Map<String, Object>> b = testMapper.checktbname(dbname, tbname);

		if (a.size() == 0) {
			testMapper.storedinfobiao(dbname, tbname, dateFormat.format(date));
			testMapper.storedinfoku(dbname, dateFormat.format(date));
			testMapper.newdatabase(dbname);
			testMapper.selectdb(dbname);
			testMapper.creattable(tbname);


			c = "写入成功";
		} else if (a.size() != 0 && b.size() == 0) {
			testMapper.storedinfobiao(dbname, tbname, dateFormat.format(date));
			testMapper.selectdb(dbname);
			testMapper.creattable(tbname);

			c = "已存在数据库，自动为您在此数据库下创建表";
		} else if (a.size() != 0 && b.size() != 0) {
			c = "创建失败,在此数据库下已有重名的数据表";
		}


		//testMapper.selectdb(db);Map<String,Object> info
		//testMapper.newtable(tb);
		JSONObject reald = new JSONObject();
		reald.put("库", dbname);
		reald.put("表", tbname);
		reald.put("创建时间", dateFormat.format(date));
		reald.put("存入状态", c);

		return reald;

	}

	@RequestMapping("/realdb0")
	public List<String> recreate(@RequestBody List<JSONObject> dat, String kuming, String biaoming) {
		List<String> a = new ArrayList<String>();
		List<String> b = new ArrayList<String>();

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");

		testMapper.newdatabase(kuming);
		testMapper.setattritable();
		testMapper.creattable(biaoming);


		try {

			i = 1;
			while (true) {
				Map<String, Object> e = dat.get(i);

				String ziduan = String.valueOf(e.get("ziduan"));
				String leixing = String.valueOf(e.get("leixing"));
				String yingwen = String.valueOf(e.get("yingwen"));


				testMapper.writein(biaoming, ziduan, leixing, yingwen, dateFormat.format(date));
				testMapper.editrealtable(biaoming, ziduan, leixing);
				a.add(ziduan);
				b.add(leixing);


				if ("".equals(dat.get(i)) == true) {
					break;
				}
				;

				i++;
			}


		} catch (Exception e) {
		}

		//a= testMapper.readziduan();
		//d=testMapper.readtype();


		return a;

	}

	@RequestMapping(path = "/unitconversion")
	public JSONObject unitconversion(@RequestBody JSONObject a) {

		String wanttype = a.getString("wanttype"); //英制单位
		String origintype = a.getString("origintype"); //美制单位
		String originunit = a.getString("originunit");  //cm/m/kg
		Double originvalue = a.getDouble("originvalue"); //1.2
		String finalunit = "";
		double finalvalue=0.01;
		//String wantrate = wanttype.substring(0, 2);
		//String originrate = origintype.substring(0, 2);


		if (wanttype.equals(origintype)) {
			finalunit = "前后单位相同,无需转换";
			finalvalue=0;
		} else {

			finalunit= testMapper.finalunit(wanttype,origintype,originunit);

			if (origintype.contains("国际") == false) {

				Double toguoji = testMapper.changetoorigin(origintype.substring(0, 2), origintype, originunit);

				if (wanttype.contains("国际") == true) {  //其他制度转国际制

					finalvalue= originvalue*toguoji;
				}

				else {

					Double toother = testMapper.findotherrate(finalunit,wanttype.substring(0, 2),wanttype);

					finalvalue =(originvalue*toguoji)/toother; //其他制度互相转化，则要先转为国际制然后转为想要的其他制度
				}
			}

			else { //国际制转其他制度

				finalunit=testMapper.unitcongor(originunit,wanttype);

				Double ww= testMapper.ratecongor(originunit,wanttype.substring(0, 2));

				finalvalue= originvalue/ww;
			}



		}
		JSONObject reald = new JSONObject();
		reald.put("原值", originvalue);
		reald.put("转换前类型", origintype);
		reald.put("转换前单位", originunit);
		reald.put("转换后类型", wanttype);
		reald.put("转换后单位", finalunit);
		reald.put("转换后的值", finalvalue);

		return reald;
	}
}