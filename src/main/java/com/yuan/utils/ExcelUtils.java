package com.yuan.utils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONObject;



/**
 * 2019/12/18
 * @author Yuan
 *
 */
public class ExcelUtils {
	

//	/**
//	 * 统计数据解析
//	 * @param response 
//	 * @param headerKeys 		表格列头String数组  例：String[] strings = new String[]{"seq","dydj_name","year"};
//	 * @param jb	
//	 * @param columnsName	columns属性中包含的一个的集合名称的key值
//	 * @param dataName		rows中columnsName集合中需要取数据的key
//	 * @return
//	 */
//	public static Map<String, Object> getExportExcelData_TJ(String[] headerKeys,JSONObject jb,String columnsName,String dataName) {
//		//json数据解析
//		Map<String,Object> dataMap = (Map<String,Object>)jb;
//		Map<String, Object> data = (Map<String, Object>) dataMap.get("data");
//		Map<String, Object> columns = (Map<String, Object>) data.get("columns");
//		List<Map<String, Object>> rows = (List<Map<String, Object>>) data.get("rows");
//		
//		List<String> headerList = new ArrayList<String>();
//		for (int i = 0; i < headerKeys.length; i++) {
//			if (headerKeys[i].equals(columnsName)) {
//				List<Map<String, Object>> list_ = (List<Map<String, Object>>) columns.get(columnsName);
//				for (Map<String, Object> map : list_) {
//					headerList.add(map.get("name").toString());
//				}
//			}else{
//				headerList.add(columns.get(headerKeys[i]).toString() );
//			}
//		}
////		System.out.println(headerList);
////		System.out.println(Arrays.toString(header));
//		
//		List<List<Object>> dataList = new ArrayList<List<Object>>();
//		for (Map<String, Object> rmap : rows) {
//			List<Object> lineList = new ArrayList<Object>();
//			for (int i = 0; i < headerKeys.length; i++) {
//				if (headerKeys[i].equals(columnsName)) {//数据集合
//					List<Map<String, Object>> lsit2 = (List<Map<String, Object>>) rmap.get(columnsName);
//					for (Map<String, Object> map : lsit2) {
//						lineList.add(map.get(dataName) == null? "":map.get(dataName));
//					}
//				}else{
//					lineList.add( rmap.get(headerKeys[i].toString()) );
//				}
//			}
//			dataList.add(lineList);
//		}
////		System.out.println(dataList);
//		Map<String, Object> resMap = new HashMap<String, Object>();
//		resMap.put("headerList", headerList);
//		resMap.put("dataList", dataList);
//		return resMap;
//	}
	
//	/**
//	 * 普通excel导出公共方法
//	 * @param response
//	 * @param headerList 
//	 * @param dataList
//	 * @param tableName
//	 * @return
//	 */
//	public static JSONObject excelExport(HttpServletResponse response,List<String> headerList,List<List<Object>> dataList,String tableName) {
//		
//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet sheet = wb.createSheet(tableName);// 创建shell1表名
//		
//		// 设置excel的字体
//		XSSFFont font = wb.createFont();
//		font.setFontHeightInPoints((short) 12);
//		font.setColor(XSSFFont.COLOR_NORMAL);
//		font.setFontName("微软雅黑");
//		XSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//		style.setFont(font);
//		style.setWrapText(true);// 是否自动换行
//		
//		if (headerList.size() > 0 &&  dataList.size() > 0) {
//			
//			XSSFRow rowHeader = sheet.createRow((int) 0);
////			Object[] excelHeader =  headerList.toArray();			
//			for (int i = 0; i < headerList.size(); i++) {
//				XSSFCell cell = rowHeader.createCell(i);
//				cell.setCellValue(headerList.get(i).toString());
//				cell.setCellStyle(style);
//				sheet.setColumnWidth(i, 5000);
//			}
//			
//			// 将list存储于excel表格中
//			for (int i = 0; i < dataList.size(); i++) {
//				XSSFRow row = sheet.createRow(i+1);// 创建一排
//				List<Object> lineList = dataList.get(i);
//				
//				for (int j = 0; j < lineList.size(); j++) {
//					row.createCell(j).setCellValue(lineList.get(j).toString());
//				}
//				
//				XSSFCell cell = row.createCell(lineList.size());
//				cell.setCellStyle(style);
//			}
//			
//			try {
//				String fileName = tableName+UUIDUtils.getUUID()+".xlsx";
//				String fileName_ = new String(fileName.getBytes(),"ISO8859-1"); 
//				response.setContentType("application/octet-stream;charset=ISO8859-1"); 
//				response.setHeader("Content-Disposition", "attachment;filename="+ fileName_); 
//				response.addHeader("Pargam", "no-cache"); response.addHeader("Cache-Control", "no-cache"); 
//				
//				OutputStream out = response.getOutputStream() ;
//				wb.write(out) ;
//				out.flush();
//				out.close();
//				System.out.println("《"+fileName+"》导出成功");
//				return JsonUtils.tranResultJsonForMap(null, "200", "success");
//				
//			} catch (Exception e) {
//				return JsonUtils.tranResultJsonForMap(null, "402", "导出文件失败");
//			} 
//			
//		}
//		
//		return JsonUtils.tranResultJsonForMap(null, "402", "无数据");
//	}
	
	/**
	 * 查询数据解析
	 * @param response 
	 * @param headerKeys 		表格列头key数组  例：String[] headerKeys = new String[]{"xl_id","xlmc","ds","ywbz","dydj","fxdj","tysj","zt"};
	 * @param headerStrings		表格列头title数组  例：String[] headerStrings = new String[]{"线路ID","线路名称","机构名称","运维班组","电压等级","风险等级","投运时间","状态"};
	 * @param jb				数据接口返回的数据
	 * @return
	 */
	public static Map<String, Object> getExportExcelData_CX(String[] headerKeys,String[] headerStrings,JSONObject jb) {
		//json数据解析
		Map<String,Object> dataMap = (Map<String,Object>)jb;
		Map<String, Object> data = (Map<String, Object>) dataMap.get("data");
		List<Map<String, Object>> rows = (List<Map<String, Object>>) data.get("rows");
		
		List<List<Object>> headerList = new ArrayList<List<Object>>();
		for (int i = 0; i < headerStrings.length; i++) {
			List<Object> list = new ArrayList<Object>();
			list.add(headerStrings[i]);
			headerList.add(list);
		}
		
		System.out.println(headerList);
//		System.out.println(Arrays.toString(header));
		
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		for (Map<String, Object> rmap : rows) {
			List<Object> lineList = new ArrayList<Object>();
			for (int i = 0; i < headerKeys.length; i++) {
				lineList.add( rmap.get(headerKeys[i].toString()) );
			}
			dataList.add(lineList);
		}
		System.out.println(dataList);
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("headerList", headerList);
		resMap.put("dataList", dataList);
		return resMap;
	}
	

	
	/**
	 * 复杂表头数据解析（兼容普通表头）
	 * @param headerKeys  	columns 内的key值集合 	  new String[]{"seq","orgName","monthes","ly_gzslhj","ty_gzslhj"};
	 * @param jb			JSONObject //数据接口返回数据
	 * @param columnsListName 	String  //数据columns内的子集合名称
	 * @param dataName		String[] 数据rows内的取值key集合
	 * @return
	 */
	public static Map<String, Object> getExportExcelData_TJ_2(String[] headerKeys,JSONObject jb,String columnsListName,String[] dataName) {
		//json数据解析
		Map<String,Object> dataMap = (Map<String,Object>)jb;
		Map<String, Object> data = (Map<String, Object>) dataMap.get("data");
		Map<String, Object> columns = (Map<String, Object>) data.get("columns");
		List<Map<String, Object>> rows = (List<Map<String, Object>>) data.get("rows");
		
		List<List<Object>> headerList = new ArrayList<List<Object>>();
		for (int i = 0; i < headerKeys.length; i++) {
			List<Object> headers = new ArrayList<Object>();
			if (headerKeys[i].equals(columnsListName)) {
				List<Map<String, Object>> list_ = (List<Map<String, Object>>) columns.get(columnsListName);
				for (Map<String, Object> map : list_) {
					headers.add(map.get("name").toString());
				}
			}else{
				headers.add( columns.get(headerKeys[i]).toString() );
			}
			headerList.add( headers );
		}
		System.out.println(headerList);
//		System.out.println(Arrays.toString(header));
		
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		for (Map<String, Object> rmap : rows) {
			List<Object> lineList = new ArrayList<Object>();
			for (int i = 0; i < headerKeys.length; i++) {
				if (headerKeys[i].equals(columnsListName)) {//数据集合
					List<Map<String, Object>> list2 = (List<Map<String, Object>>) rmap.get(columnsListName);
//					System.out.println(list2);
					for (Map<String, Object> map : list2) {
						for (int j = 0; j < dataName.length; j++) {
							lineList.add(map.get(dataName[j]) == null? "":map.get(dataName[j]));
						}
					}
				}else{
					lineList.add( rmap.get(headerKeys[i].toString()) );
				}
			}
			dataList.add(lineList);
		}
		System.out.println(dataList);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("headerList", headerList);
		resMap.put("dataList", dataList);
		return resMap;
	}
   
	/**
	 * excel导出方法2
	 * @param response
	 * @param headerList	List<List<Object>>	getExportExcelData_TJ_2返回数据
	 * @param dataList		List<List<Object>>	getExportExcelData_TJ_2返回数据
	 * @param tableName
	 * @param columns		子表头   columns = new String[]{"去年","今年"};    无复杂表头时入参为空
	 * @return
	 */
	public static JSONObject excelExport_2(HttpServletResponse response,List<List<Object>> headerList,List<List<Object>> dataList,String tableName,String[] columns) {
        XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(tableName);// 创建shell1表名
		
		// 设置excel的字体
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setColor(XSSFFont.COLOR_NORMAL);
		font.setFontName("微软雅黑");
		// 设置格式
		XSSFCellStyle style = wb.createCellStyle();
		style.setFont(font);
		style.setWrapText(false);// 是否自动换行
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 设置单元格字体显示居中（左右方向）
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 设置单元格字体显示居中(上下方向)
		
		if (headerList.size() > 0 &&  dataList.size() > 0) {
			int m = 0;
			//合并单元格
			for (List<Object> header : headerList) {
				if (header.size() == 1 ) {
					CellRangeAddress callRangeAddress = new CellRangeAddress(0,1,m,m);//起始行,结束行,起始列,结束列
					sheet.addMergedRegion(callRangeAddress);
					m++;
				}else {
					for (Object object : header) {
						if (columns != null && columns.length != 0) {
							CellRangeAddress callRangeAddress = new CellRangeAddress(0,0,m,m+columns.length-1);//起始行,结束行,起始列,结束列
							sheet.addMergedRegion(callRangeAddress);
							m+=columns.length;
						}else {
							CellRangeAddress callRangeAddress = new CellRangeAddress(0,1,m,m);//起始行,结束行,起始列,结束列
							sheet.addMergedRegion(callRangeAddress);
							m++;
						}
					}
				}
			}
				
			//标题数据插入
			if (columns != null && columns.length != 0) {
				XSSFRow rowHeader = sheet.createRow((int) 0);//第一行
				XSSFRow rowHeader1 = sheet.createRow((int) 1);//第二行
	//			Object[] excelHeader =  headerList.toArray();
				int j =0;
				for (int i = 0; i < headerList.size() ; i++) {
					if (headerList.get(i).size() == 1) {
						XSSFCell cell = rowHeader.createCell(j);
						cell.setCellValue(headerList.get(i).get(0).toString());
						cell.setCellStyle(style);
						sheet.setColumnWidth(j, 3000);
						j++;
					}else {
						j = i;
						for (Object list : headerList.get(j)) {
							XSSFCell cell_ = rowHeader.createCell(j);
							cell_.setCellValue(list.toString());
							cell_.setCellStyle(style);
							sheet.setColumnWidth(j, 3000);
							
							for (int k = 0; k < columns.length; k++) {
								XSSFCell cell2_1 = rowHeader1.createCell(j);
								cell2_1.setCellValue(columns[k]);
								cell2_1.setCellStyle(style);
								sheet.setColumnWidth(j, 3000);
								j++;
							}
						}
					}
					
				}
			}else{
				XSSFRow rowHeader = sheet.createRow((int) 0);//第一行
				int i = 0;
				for (List<Object> header : headerList) {
					for (Object obj : header) {
						XSSFCell cell = rowHeader.createCell(i);
						cell.setCellValue(obj.toString());
						cell.setCellStyle(style);
						sheet.setColumnWidth(i, 3000);
						i++;
					}
				}
			}
			// 将list存储于excel表格中
			for (int i = 0; i < dataList.size(); i++) {
				XSSFRow row = sheet.createRow(i+2);// 创建一排
				List<Object> lineList = dataList.get(i);
				
				for (int jj = 0; jj < lineList.size(); jj++) {
					XSSFCell cell = row.createCell(jj);
					cell.setCellValue(lineList.get(jj) == null? "":lineList.get(jj).toString());
					cell.setCellStyle(style);
					sheet.setColumnWidth(jj, 3000);
				}
			}
			
			try {
				String fileName = tableName+UUIDUtils.getUUID()+".xlsx";
				String fileName_ = new String(fileName.getBytes(),"ISO8859-1"); 
				response.setContentType("application/octet-stream;charset=ISO8859-1"); 
				response.setHeader("Content-Disposition", "attachment;filename="+ fileName_); 
				response.addHeader("Pargam", "no-cache"); response.addHeader("Cache-Control", "no-cache"); 
				
				OutputStream out = response.getOutputStream() ;
				wb.write(out) ;
				out.flush();
				out.close();
				System.out.println("《"+fileName+"》导出成功");
				return JsonUtils.tranResultJsonForMap(null, "200", "success");
				
			} catch (Exception e) {
				return JsonUtils.tranResultJsonForMap(null, "402", "导出文件失败");
			} 
			
		}
		
		return JsonUtils.tranResultJsonForMap(null, "402", "无数据");
	}
	
	
}
