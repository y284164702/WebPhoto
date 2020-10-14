package com.yuan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yuan.mapper.WebPhotoMapper;
import com.yuan.service.WebPhotoService;
import com.yuan.utils.CommonUtils;
import com.yuan.utils.JsonUtils;

@Service
public class WebPhotoServiceImpl implements WebPhotoService{

	@Autowired
	private WebPhotoMapper webPhotoMapper;
	
	@Transactional
	@Override
	public JSONObject uploadPhoto(Map<String, Object> params) throws Exception {
		int n = webPhotoMapper.uploadPhoto(params);
		return JsonUtils.tranResultJson(null, "0", "ok");
	}

	@Transactional
	@Override
	public JSONObject getPhotos(Map<String, Object> params) throws Exception {
		List<Map<String, Object>> photos = webPhotoMapper.getPhotos(params);
		photos = CommonUtils.transformUpperCaseByList(photos);
		return JsonUtils.tranResultJson(photos, "0", "ok");
	}

}
