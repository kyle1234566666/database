package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jxl.Cell;

@Mapper
public interface Test {
	
	int Addnumber(String aid,String extra);
	
    int Selectorder(String order);
    
    void Createtb(@Param("tableName") String tableName,
            @Param("name1")String name1,
            @Param("name2")String name2,
            @Param("line1typ")String line1typ,
            @Param("line2typ")String line2typ);
    
    int Gotdb(@Param("tableName") String tableName);
    
    int crtt(@Param ("dbname") String dbname);
    
    int uuu(@Param("dtb") String dtb, 
            @Param ("linea") String linea,@Param ("lineaty") String lineaty, 
            @Param("lineb")String lineb,@Param ("linebty") String linebty,
            @Param("atype")String atype,@Param("btype")String btype);
    
    int confirmtya(@Param("dtb") String dtb, 
            @Param ("linea") String linea,@Param ("lineaty") String lineaty);
    
    int confirmtyb(@Param("dtb") String dtb, 
            @Param("lineb")String lineb,@Param ("linebty") String linebty);
    
    int selectdb(@Param ("dbname") String dbname);
    
    int crettable(@Param("dtb") String dtb, 
            @Param ("linea") String linea,@Param ("lineaty") String lineaty, 
            @Param("lineb")String lineb,@Param ("linebty") String linebty,
            @Param("atype")String atype,@Param("btype")String btype);
    
    int exconfirmtya(@Param("dtb") String dtb, 
            @Param ("linea") String linea,@Param ("lineaty") String lineaty);
    
    int exconfirmtyb(@Param("dtb") String dtb, 
            @Param("lineb")String lineb,@Param ("linebty") String linebty);

	int newdatabase(@Param("db") String db);

	void newtable(@Param("tb") String tb);
	
	int selectdatabase(@Param ("db") String db);

	int storedata(@Param("param1")String contents,@Param("param2") String contents2, @Param("param3")String contents3,@Param("param4") String contents4,@Param("param5") String contents5,
			@Param("param6")String contents6, @Param("param7")String contents7,@Param("param8") String contents8, @Param("param9")String contents9, @Param("param10")String contents10,@Param("param11") String contents11);
    
    
    }