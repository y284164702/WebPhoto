package com.yuan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WebPhotoMapper {

	@Select("select * from t_photo")
	List<Map<String, Object>> getPhotos(Map<String, Object> params);

	@Insert("insert into t_photo (id,name,size,type,address) values (#{uuid},#{fileName},#{fileSize},#{type},#{filePath})")
	int uploadPhoto(Map<String, Object> params);

	

}
