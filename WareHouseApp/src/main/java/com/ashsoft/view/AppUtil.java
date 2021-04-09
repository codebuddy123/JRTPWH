package com.ashsoft.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AppUtil {
	
	public static Map<Integer, String> convertToMap(List<Object[]> list)
	{
		Map<Integer, String> map = new HashMap<>();
		
		for(Object[] ob:list)
		{
			map.put(Integer.parseInt(ob[0].toString()), ob[1].toString());
		}
		return map;
	}
}
