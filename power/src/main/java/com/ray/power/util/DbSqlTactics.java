package com.ray.power.util;

import java.util.Map;

public class DbSqlTactics {
	
	public String tabCode = null;//表
	//public String tabCodeSeqSql = null;//序列SQL
	public String mdlSign = "#{mdl.";
	public String updateWh = " where id=#{mdl.id,jdbcType=NUMERIC}";//更新主键
	public Map<String,String> colsMap = null;//表列项及属性  例子： colsMap.put("id","NUMERIC");   NUMERIC ：jdbcType
	public String colsStr = null;

}
