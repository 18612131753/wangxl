package com.ray.power.base;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SpringMvc 日期类型转换类
 * @author lixy
 *
 */
public class DateEditor extends PropertyEditorSupport {
	
	/**
	 * string --> date
	 */
	@Override
	public void setAsText(String text) {
		if(text == null || text.equals("")) {
			setValue(null);
		} else if (text.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")){
			setValue(parse(text, "yyyy-MM-dd HH:mm:ss"));
		} else if (text.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			setValue(parse(text, "yyyy-MM-dd"));
		} else {
			try{
				super.setAsText(text);
			}catch(Exception e){
				setValue(null);
			}
		}
	}
	
	/**
	 * date --> string
	 */
	@Override
	public String getAsText() {
		Object obj = getValue();
		if (obj != null && (obj instanceof Date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(obj);
		} else {
			return super.getAsText();
		}
	}
	
	private Date parse(String str, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
