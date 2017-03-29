package com.web.controller;

import java.io.File;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@RequestMapping(value = "/upload.json")  
	@ResponseBody
    public Map<String, Object> upload(String inputFilePath,String libreOfficePath,String outputFilePath) throws ConnectException {  
        File inputFile = null ;
        File outputFile = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try{
        	 inputFile= new File(inputFilePath);
        	 if(!inputFile.exists()){
        		 map.put("code", "目标文件不存在");
        		 return map;
        	 }
        	 }catch(Exception e){
        	System.out.println("inputFile有误");
        }
        if(!outputFilePath.endsWith("pdf")){
        	 map.put("code", "请输入生成pdf文件");
    		 return map;
        }
        //libreoffice
//        String libreOfficePath ="E:/tools/libreoffice";
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        try{
        configuration.setOfficeHome(new File(libreOfficePath));
        }catch(Exception e){
        	e.printStackTrace();
        	map.put("code", "libreOffice路径有误");
        	return map;
        }
        configuration.setPortNumber(8100);
        OfficeManager officeManager = configuration.buildOfficeManager();
        officeManager.start();
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        try{
        outputFile = new File(outputFilePath);
        File outputFileParent = outputFile.getParentFile();
        if(outputFile.exists()){
        	map.put("code", "该文件已存在");
        	return map;
        }
        if(!outputFileParent.exists()){
        	outputFileParent.mkdirs();
        }
        if(!outputFileParent.exists()){
        	map.put("code", "生成文件路径无法创建");
        	return map; 
        }
        }catch(Exception e){
        	e.printStackTrace();
        	map.put("code", "生成文件路径有误");
        	return map;
        }
        try{
        converter.convert(inputFile, outputFile);}catch(Exception e){
        	e.printStackTrace();
        	map.put("code", "文件转换失败");
        	return map;
        }
        officeManager.stop();
        map.put("code", "文件转换成功");
        return map;  
    }  

}
