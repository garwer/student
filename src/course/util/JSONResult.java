package course.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 用于将结果集转化为json的工具类
 * @author HJB
 */
public class JSONResult {
	
	private static final Logger log = Logger.getLogger(JSONResult.class);

	public static JSONArray toJSON(Connection conn, String sql) throws SQLException {	
		return toJSON(conn, sql, null);
	}

	public static JSONArray toJSON(Connection conn, String sql, Object param) throws SQLException {		
		return toJSON(conn, sql, new Object[] {param});
	}
	
	public static JSONArray toJSON(Connection conn, String sql, Object[] params) throws SQLException {
		return JSONArray.fromObject(new QueryRunner().query(conn, sql, params, new MapListHandler()));
	}
	

	public static JSONArray toJSON(ResultSet rs) throws SQLException {
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		ResultSetMetaData metaData = rs.getMetaData();
		jsonArray = new JSONArray();
		while (rs.next()) {
			jsonObject = new JSONObject();			
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				jsonObject.put(metaData.getColumnLabel(i), StringUtils
						.isEmpty(rs.getString(i)) ? "" : rs.getString(i));
			}			
			jsonArray.add(jsonObject);
		}		
		return jsonArray;
	}
	
}
