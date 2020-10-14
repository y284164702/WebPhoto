package com.yuan.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class CommonUtils {

	/**
	 * 根据数据库类型判断使用的SQL语句版本
	 * 
	 * @param mysqlSql
	 * @param oracleSql
	 * @return
	 */
	public static String getSql(String mysqlSql, String oracleSql) {
		String sql = "";
		if ("mysql".equals(Constant.DATASOURCE))
			sql = mysqlSql;
		if ("oracle".equals(Constant.DATASOURCE))
			sql = oracleSql;
		return sql;
	}

	/**
	 * 将List<Map<String, Object>>集合中的key大写转换为小写
	 * 
	 * @param list
	 * @return
	 */
	public static List<Map<String, Object>> transformUpperCaseByList(List<Map<String, Object>> list) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		if (list == null || list.isEmpty()) {
			return resultList;
		}

		for (Map<String, Object> map : list) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Set<String> keySet = map.keySet();
			for (String key : keySet) {
				String newKey = key.toLowerCase();
				resultMap.put(newKey, map.get(key));
			}
			resultList.add(resultMap);
		}
		return resultList;
	}

	/**
	 * 将Map集合中的key大写转换为小写
	 * 
	 * @param list
	 * @return
	 */
	public static Map<String, Object> transformUpperCaseByMap(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (map == null || map.isEmpty()) {
			return resultMap;
		}

		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			String newKey = key.toLowerCase();
			resultMap.put(newKey, map.get(key));
		}
		return resultMap;
	}

	public static List<String> getKeysOfMap(Map<String, String[]> map) {
		List<String> list = new ArrayList<String>();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public boolean isEmpty(String value) {
		return StringUtils.isEmpty(value);
	}

	public boolean greaterThanLengthMax(String value, Integer max) {
		if (null == value) {
			value = "";
		}
		return Integer.valueOf(value.length()) <= max;
	}

	public boolean lessThanLengthMin(String value, Integer min) {
		if (null == value) {
			value = "";
		}
		return Integer.valueOf(value.length()) >= min;
	}

	/**
	 * 判断是否匹配指定文件后缀
	 *
	 * @param fileSuffix  文件后缀
	 * @param matchSuffix 指定文件后缀
	 * @return true为匹配
	 */
	public boolean matchFileSuffix(String fileSuffix, String matchSuffix) {
		return matchSuffix.indexOf(fileSuffix) != -1;
	}

	/**
	 * 判断是否超出指定文件大小范围
	 *
	 * @param fileSize 文件大小，单位为字节
	 * @param maxSize  指定最大文件大小
	 * @return true为不超过
	 */
	public boolean outMaxSize(BigInteger fileSize, BigInteger maxSize) {
		return fileSize.compareTo(maxSize) == -1;
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];

			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}

	public static String makeRandomLetter(int number) {
		Random r = new Random();
		StringBuffer s = new StringBuffer();
		int ni = 0;
		while (true) {
			ni = r.nextInt(27);
			if (ni == 0) {
				continue;
			}
			s.append((char) (ni + 64));
			if (s.length() == number) {
				break;
			}
		}
		return s.toString();
	}

	public static BufferedImage makeBufferedImage(int width, int height, String code) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获得画布
		Graphics g = bi.getGraphics();
		// 设置背影色
		setBackGround(g, width, height);
		// 设置边框
		setBorder(g, width, height);
		// 画干扰线
		drawRandomLine(g, width, height);
		// 写随机数
		drawRandomNum((Graphics2D) g, code);
		return bi;
	}

	/**
	 * 设置背景色
	 * 
	 * @param g
	 */
	private static void setBackGround(Graphics g, int width, int height) {
		// 设置颜色
		g.setColor(new Color(249, 249, 249));
		// 填充区域
		g.fillRect(0, 0, width, height);

	}

	/**
	 * 设置边框
	 * 
	 * @param g
	 */
	private static void setBorder(Graphics g, int width, int height) {
		// 设置边框颜色
		// RGB
		g.setColor(new Color(210, 209, 205));
		// 边框区域
		g.drawRect(1, 1, width - 2, height - 2);
	}

	/**
	 * 画随机线条
	 * 
	 * @param g
	 */
	private static void drawRandomLine(Graphics g, int width, int height) {
		// 设置颜色
		g.setColor(Color.GRAY);
		// 设置线条个数并画线
		for (int i = 0; i < 5; i++) {
			int x1 = new Random().nextInt(width);
			int y1 = new Random().nextInt(height);
			int x2 = new Random().nextInt(width);
			int y2 = new Random().nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 画随机汉字
	 * 
	 * @param g
	 * @return
	 */
	private static void drawRandomNum(Graphics2D g, String code) {
		// 设置字体
		g.setFont(new Font("宋体", Font.BOLD, 20));
		int x = 5;
		String[] arrCode = code.split("");
		// 控制字数
		for (int i = 0, size = arrCode.length; i < size; i++) {
			// 生成0-255的随机数
			int R = new Random().nextInt(256);
			int G = new Random().nextInt(256);
			int B = new Random().nextInt(256);
			// 设置每个随机数的颜色
			g.setColor(new Color(R, G, B));
			// 设置字体旋转角度
			int degree = new Random().nextInt() % 30;
			// 正向角度
			g.rotate(degree * Math.PI / 180, x, 20);
			g.drawString(arrCode[i], x, 20);
			// 反向角度
			g.rotate(-degree * Math.PI / 180, x, 20);
			x += 30;
		}
	}

	/**
	 * 判断字符是否是正则表达式的特殊符号
	 * 
	 * @param ca
	 * @return
	 */
	public static boolean isConvert(String ca) {
		String[] charArr = new String[] { "\\", "^", "$", "*", "+", "?" };
		boolean isConvert = false;
		for (int i = 0, size = charArr.length; i < size; i++) {
			if (charArr[i].equals(ca)) {
				isConvert = true;
				break;
			}
		}
		return isConvert;
	}

	/**
	 * 获取10-100的随机整数
	 * 
	 * @return
	 */
	public static int getNum() {
		Random random = new Random();
		return random.nextInt(90) + 10;
	}

	/**
	 * 获取startNum-endNum的随机整数
	 * 
	 * @param startNum
	 * @param endNum
	 * @return
	 */
	public static int getNum(int startNum, int endNum) {
		Random random = new Random();
		return random.nextInt(endNum - startNum) + startNum;
	}

	/**
	 * 获取分页对象
	 * 
	 * @param dataList
	 * @param params
	 * @return
	 */
	public static Map<String, Object> getPage(List<Map<String, Object>> dataList, Map<String, Object> params) {
		String curPage = params.get("curPage") == null ? "" : params.get("curPage").toString();
		String pageSize = params.get("pageSize") == null ? "" : params.get("pageSize").toString();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("total", dataList.size());
		pageMap.put("curPage", curPage);

		if (!curPage.equals("") && curPage != null && !pageSize.equals("") && pageSize != null) {
			int curpage = Integer.parseInt(curPage);
			int pagesize = Integer.parseInt(pageSize);
			// 从第几条数据开始
			int firstIndex = (curpage - 1) * pagesize;
			// 到第几条数据结束
			int lastIndex = curpage * pagesize;
			if (lastIndex > dataList.size()) {
				lastIndex = dataList.size();
			}
			dataList = dataList.subList(firstIndex, lastIndex); // 直接在list中截取
		}

		pageMap.put("rows", dataList);
		return pageMap;
	}

	/**
	 * 截取分页数据集合
	 * 
	 * @param dataList
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> getPageList(List<Map<String, Object>> dataList,
			Map<String, Object> params) {
		String curPage = params.get("curPage").toString();
		String pageSize = params.get("pageSize").toString();
		if (!curPage.equals("") && curPage != null && !pageSize.equals("") && pageSize != null) {
			int curpage = Integer.parseInt(curPage);
			int pagesize = Integer.parseInt(pageSize);
			// 从第几条数据开始
			int firstIndex = (curpage - 1) * pagesize;
			// 到第几条数据结束
			int lastIndex = curpage * pagesize;
			if (lastIndex > dataList.size()) {
				lastIndex = dataList.size();
			}
			dataList = dataList.subList(firstIndex, lastIndex); // 直接在list中截取
		}
		return dataList;
	}

	/**
	 * 参数空值校验
	 * 
	 * @return null(空校验通过）
	 */
	public static JSONObject paramsEmptyVerify(Map<String, Object> params) {
		for (Map.Entry<String, Object> a : params.entrySet()) {
//		  System.out.print("键是"+a.getKey());
//		  System.out.println("值是"+a.getValue());
			if (a.getValue() == null || a.getValue().equals("")) {
				return JsonUtils.tranResultJsonForMap(null, "412", "缺少参数" + a.getKey());
			}
		}
		return null;
	}

	/**
	 * 判断文件是否符合条件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean checkFileName(String fileName) {
		String[] allowTypes = new String[] { "PDF", "JPG", "BMP", "JPEG", "PNG", "GIF", "pdf", "jpg", "bmp", "jpeg",
				"png", "gif" };
		fileName = fileName.toUpperCase();
		int splitIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(splitIndex + 1);

		if (!isValid(fileType, allowTypes)) {
			return false;
		}
		return true;
	}

	private static boolean isValid(String contentType, String... allowTypes) {
		if (null == contentType || "".equals(contentType)) {
			return false;
		}
		for (String type : allowTypes) {
			if (contentType.indexOf(type) > -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 树结构
	 * 
	 * @param list [{"id":"","title":"","pid":""}]
	 * @param pid
	 * @return
	 */
	public static List<Map<String, Object>> listTreeResult(List<Map<String, Object>> list, String pid) {
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			if (map.get("pid").equals(pid)) {
				Map<String, Object> newmap = new HashMap<String, Object>();
				List<Map<String, Object>> result = listTreeResult(list, map.get("id").toString());
				newmap.put("id", map.get("id"));
				newmap.put("title", map.get("title"));
//	    	  newmap.put("icon", map.get("icon"));
//	    	  newmap.put("spread",  map.get("spread"));
				newmap.put("href", map.get("href"));
				if (result.size() != 0) {
					newmap.put("children", result);
				}
				children.add(newmap);
			}
		}
		return children;
	}


}
