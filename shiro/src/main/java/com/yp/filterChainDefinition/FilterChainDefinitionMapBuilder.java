package com.yp.filterChainDefinition;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {
	
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
		map.put("/index.jsp*", "anon");
		map.put("/login.jsp*", "anon");
		map.put("/shiro/login", "anon");
		map.put("/**", "authc");
		map.put("/*.*", "authc");
		
		return map;
	}
}
