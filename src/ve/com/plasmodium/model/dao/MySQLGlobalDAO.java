package ve.com.plasmodium.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import ve.com.plasmodium.util.Utils;

public class MySQLGlobalDAO implements GlobalDAO {

	Connection conn;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	public void initSQLData(){
		conn = MySQLDAOFactory.createConnection();
		preparedStatement = null;
		resultSet = null;
	}

	public void searchParameter(Map<String, String> maps) {
		initSQLData();
		try{
			preparedStatement = conn.prepareStatement(SQLConstant.getParameters);
			logger.debug("Statement a ejecutarse " + preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			logger.debug(resultSet.getRow());
			while(resultSet.next()){
				logger.debug(resultSet.getString("id"));
				logger.debug(resultSet.getString("parameter"));
				maps.put(resultSet.getString("id"),resultSet.getString("parameter"));
			}
		}catch (Exception e){
			logger.error("Exception " +this.getClass().getSimpleName() + " - " + Utils.getMethodName(), e);
			MySQLDAOFactory.closeConection(conn, this.getClass().getSimpleName() + " - " + Utils.getMethodName());
		}
		MySQLDAOFactory.closeConection(conn, this.getClass().getSimpleName() + " - " + Utils.getMethodName());
	}

}

