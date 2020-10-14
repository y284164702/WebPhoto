package com.yuan.utils;

import java.util.HashMap;
import java.util.Map;

public class EnhanceMap<K, V> {

	public Builder<K, V> bd;
	
	public Map<K, V> map(){
		return bd.map;
	}
	
	public static class Builder<K, V>{
		public Map<K,V> map;
		
		public Builder(){
			map = new HashMap<K,V>();
		}
		
		public Builder<K, V> put(K k, V v){
			map.put(k, v);
			return this;
		}
	}

}
