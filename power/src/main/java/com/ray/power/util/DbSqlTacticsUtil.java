package com.ray.power.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DbSqlTacticsUtil {
	
	
	//添加 策略一(包含)
	//参数： insertCols 为 (col1,col2,...)
	public static String insertContainTactics(DbSqlTactics dbSqlTactics,String insertCols){
		return insertTactics(dbSqlTactics,insertCols,true);
	}
	
	//添加 策略二(不包含)
	//参数： insertNoCols 为 (col1,col2,...)
	public static String insertNotContainTactics(DbSqlTactics dbSqlTactics,String insertNoCols){
		return insertTactics(dbSqlTactics,insertNoCols,false);
	}
	
	//编辑 策略一(包含)
	//参数： updateCols 为 (col1,col2,...)
	public static String updateContainTactics(DbSqlTactics dbSqlTactics,String updateCols){
		return updateTactics(dbSqlTactics,updateCols,true);
	}
	
	//编辑 策略二(不包含)
	//参数： updateNoCols 为 (col1,col2,...)
	public static String updateNotContainTactics(DbSqlTactics dbSqlTactics,String updateNoCols){
		return updateTactics(dbSqlTactics,updateNoCols,false);
	}
	
	//添加
	private static String insertTactics(DbSqlTactics dbSqlTactics,String insertCols,boolean flag){
		StringBuffer buf = new StringBuffer("insert into "),colsBuf = null,jdbcColsBuf = null;
		buf.append(dbSqlTactics.tabCode).append("(");
		if(insertCols==null && dbSqlTactics.colsMap!=null){//默认构造所有
			for(Map.Entry<String, String> entry:dbSqlTactics.colsMap.entrySet()){
				if(colsBuf == null){
					colsBuf = new StringBuffer();
				}else{
					colsBuf.append(",");
				}
				colsBuf.append(entry.getKey());
				if(jdbcColsBuf == null){
					jdbcColsBuf = new StringBuffer();
				}else{
					jdbcColsBuf.append(",");
				}
				jdbcColsBuf.append(getJdbcString(entry,dbSqlTactics.mdlSign));
			}
		}else if(insertCols!=null && dbSqlTactics.colsMap!=null){//构造具体项
			String[] szCols = insertCols.split(",");
			Set<String> colsSet = new HashSet<String>();
			for(String col:szCols){
				colsSet.add(col.toLowerCase());
			}
			for(Map.Entry<String, String> entry:dbSqlTactics.colsMap.entrySet()){
				if(flag){
					if(colsSet.contains(entry.getKey().toLowerCase())){
						if(colsBuf == null){
							colsBuf = new StringBuffer();
						}else{
							colsBuf.append(",");
						}
						colsBuf.append(entry.getKey());
						if(jdbcColsBuf == null){
							jdbcColsBuf = new StringBuffer();
						}else{
							jdbcColsBuf.append(",");
						}
						jdbcColsBuf.append(getJdbcString(entry,dbSqlTactics.mdlSign));
					}
				}else{
					if(colsSet.contains(entry.getKey().toLowerCase())){
						continue;
					}
					if(colsBuf == null){
						colsBuf = new StringBuffer();
					}else{
						colsBuf.append(",");
					}
					colsBuf.append(entry.getKey());
					if(jdbcColsBuf == null){
						jdbcColsBuf = new StringBuffer();
					}else{
						jdbcColsBuf.append(",");
					}
					jdbcColsBuf.append(getJdbcString(entry,dbSqlTactics.mdlSign));
				}
			}
		}else{
			return null;
		}
		if(colsBuf!=null && jdbcColsBuf!=null){
			buf.append(colsBuf.toString()).append(") values (").append(jdbcColsBuf.toString()).append(")");
			return buf.toString();
		}
		return null;
	}
	
	//编辑
	private static String updateTactics(DbSqlTactics dbSqlTactics,String updateCols,boolean flag){
		StringBuffer buf = new StringBuffer("update "),colsBuf = null;
		buf.append(dbSqlTactics.tabCode).append(" set ");
		if(updateCols==null && dbSqlTactics.colsMap!=null){//默认构造所有
			for(Map.Entry<String, String> entry:dbSqlTactics.colsMap.entrySet()){
				if(colsBuf == null){
					colsBuf = new StringBuffer();
				}else{
					colsBuf.append(",");
				}
				colsBuf.append(entry.getKey()).append("=").append(getJdbcString(entry,dbSqlTactics.mdlSign));
			}
		}else if(updateCols!=null && dbSqlTactics.colsMap!=null){//构造具体项
			String[] szCols = updateCols.split(",");
			Set<String> colsSet = new HashSet<String>();
			for(String col:szCols){
				colsSet.add(col.toLowerCase());
			}
			for(Map.Entry<String, String> entry:dbSqlTactics.colsMap.entrySet()){
				if(flag){
					if(colsSet.contains(entry.getKey().toLowerCase())){
						if(colsBuf == null){
							colsBuf = new StringBuffer();
						}else{
							colsBuf.append(",");
						}
						colsBuf.append(entry.getKey()).append("=").append(getJdbcString(entry,dbSqlTactics.mdlSign));
					}
				}else{
					if(colsSet.contains(entry.getKey().toLowerCase())){
						continue;
					}
					if(colsBuf == null){
						colsBuf = new StringBuffer();
					}else{
						colsBuf.append(",");
					}
					colsBuf.append(entry.getKey()).append("=").append(getJdbcString(entry,dbSqlTactics.mdlSign));
				}
			}
		}else{
			return null;
		}
		if(colsBuf!=null){
			buf.append(colsBuf.toString()).append((dbSqlTactics.updateWh!=null)?dbSqlTactics.updateWh:"");
			return buf.toString();
		}
		return null;
	}
	
	
	private static String getJdbcString(Map.Entry<String, String> entry,String mdlSign){
		if(entry == null){
			return null;
		}
		String rStr = null;
		String key = entry.getKey(), val = entry.getValue();
		if(key!=null && val==null){
			rStr = mdlSign+key+"}";
		}else if(key!=null && val!=null){
			if(val.startsWith("#")){
				rStr = (mdlSign + key + ",jdbcType=" + (val.substring(1)).toUpperCase()+"}");
			}else{
				rStr = val;
			}
		}else{
			
		}
		return rStr;
	}

}
