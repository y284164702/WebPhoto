package com.yuan.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

	/**
	 * 转换查询结果成需要的json格式
	 * @param obj
	 * @param status                     状态（状态参考状态表）
	 * @param message                    消息
	 * @return json
	 */
	public static JSONObject tranResultJson(Object obj, String status, String message) {
		JSONObject jo = new JSONObject();
		jo.put("status", status);
		jo.put("message", message);
		if (obj != null) {
			String classNameString = obj.getClass().toString();
			if (classNameString.equals("class java.util.HashMap")) {
				
				Map<String, Object> map0 = (Map<String, Object>) obj;
				Map<String, Object> resultMap = new HashMap<String, Object>();

				Set<String> keySet = map0.keySet();
				for (String key : keySet) {
					Object object = map0.get(key);
					if (object == null)  object = "";
					resultMap.put(key, object);
				}
				jo.put("data", resultMap);
				
			} else if (classNameString.equals("class java.util.ArrayList")) {
				
				String jsonStr = JSON.toJSON(obj).toString();
				jsonStr = jsonStr.toString().replaceAll("null", "\"\"");
				obj = JSONObject.parseArray(jsonStr);
				jo.put("data", obj);
				
			} else {
				
				jo.put("data", obj);
				
			}

		}
		return jo;
	}

	/**
	 * 转换查询结果成需要的json格式
	 * 
	 * @param 这里的list其实是List<Map<String, Object>>格式的，为了方便才定义成object
	 * @param status                     状态（状态参考状态表）
	 * @param message                    消息
	 * @return json
	 */
	public static JSONObject tranResultJsonForList(Object list, String status, String message) {
		JSONObject jo = new JSONObject();
		jo.put("status", status);
		jo.put("message", message);
		if (list != null) {
			String jsonStr = JSON.toJSON(list).toString();
			jsonStr = jsonStr.toString().replaceAll("null", "\"\"");
			list = JSONObject.parseArray(jsonStr);
			jo.put("data", list);
		}
		return jo;
	}

	/**
	 * 返回 data是对象{}
	 * 
	 * @param list
	 * @param status
	 * @param message
	 * @return
	 */
	public static JSONObject tranResultJsonForMap(Object map, String status, String message) {
		JSONObject jo = new JSONObject();
		jo.put("status", status);
		jo.put("message", message);
		if (map != null) {
			Map<String, Object> map0 = (Map<String, Object>) map;
			Map<String, Object> resultMap = new HashMap<String, Object>();

			Set<String> keySet = map0.keySet();
			for (String key : keySet) {
				Object object = map0.get(key);
				if (object == null)  object = "";
				resultMap.put(key, object);
			}
			jo.put("data", resultMap);
		}
		return jo;
	}

	/**
	 * 获取两个日期之间的所有日期
	 * 
	 * @param startTime 开始日期
	 * @param endTime   结束日期
	 * @return
	 */
	public static List<String> getDays(String startTime, String endTime) {
		// 返回的日期集合
		List<String> days = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start = dateFormat.parse(startTime);
			Date end = dateFormat.parse(endTime);
			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(start);
			Calendar tempEnd = Calendar.getInstance();
			tempEnd.setTime(end);
			tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
			while (tempStart.before(tempEnd)) {
				days.add(dateFormat.format(tempStart.getTime()));
				tempStart.add(Calendar.DAY_OF_YEAR, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

}
