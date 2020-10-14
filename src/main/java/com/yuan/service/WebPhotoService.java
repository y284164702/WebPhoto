package com.yuan.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface WebPhotoService {

	JSONObject uploadPhoto(Map<String, Object> params) throws Exception;

	JSONObject getPhotos(Map<String, Object> params) throws Exception;

}
