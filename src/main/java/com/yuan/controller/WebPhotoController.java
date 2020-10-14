package com.yuan.controller;

import java.io.File;
import java.io.InputStream;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.xmlrpc.base.Data;
import com.yuan.service.WebPhotoService;
import com.yuan.utils.CommonUtils;
import com.yuan.utils.JsonUtils;
import com.yuan.utils.UUIDUtils;


@Controller
@RequestMapping("api/webPhoto/")
public class WebPhotoController {

	@Autowired
	private WebPhotoService webPhotoService;
	
	@RequestMapping(value = "uploadPhoto" ,method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadPhoto(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "token", required = false) String token,
			MultipartFile file) {

		try {
			String uuid = UUIDUtils.getUUID();
			String dirPath = "D://uploadPhoto/";
			String fileName = file.getOriginalFilename();
			Long fileSize = file.getSize()/1000;
	        String type = fileName.substring(fileName.lastIndexOf("."), fileName.length());
	        String localFileName =	uuid + type;
	        String filePath = dirPath + File.separator + localFileName;
	        File localFile = new File(filePath);
	        File imagePath = new File(dirPath);
	        if (!imagePath.exists()) {
	            imagePath.mkdirs();
	        }
	        file.transferTo(localFile);
			
			// 读取IO流文件
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("fileName",fileName);
			params.put("type",type);
			params.put("localFileName",localFileName);
			params.put("filePath",filePath);
			params.put("fileSize",fileSize);
			params.put("uuid",uuid);
			System.out.println(params);
			return webPhotoService.uploadPhoto(params);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.tranResultJson(null, "400", e.getMessage());
		}
	}
	
	@RequestMapping(value = "getPhotos" ,method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getPhotos(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "token", required = false) String token,
			@RequestParam(value = "curPage", required = false) String curPage,
			@RequestParam(value = "pageSize", required = false) String pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		//判断必要参数是否为为空
		JSONObject jb = CommonUtils.paramsEmptyVerify(params);
		params.put("token", token);
		params.put("curPage", curPage);
		params.put("pageSize", pageSize);
		if (jb != null) return jb;
		try {
			return webPhotoService.getPhotos(params);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtils.tranResultJson(null, "400", e.getMessage());
		}
	}
}
