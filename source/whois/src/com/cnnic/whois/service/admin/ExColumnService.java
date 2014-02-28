package com.cnnic.whois.service.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnnic.whois.dao.admin.ExColumnDAO;
import com.cnnic.whois.execption.ManagementException;

@Service
public class ExColumnService {
	
	@Autowired
	private ExColumnDAO exColumnDao;

	/**
	 * Add the extension field
	 * 
	 * @param tableName
	 * @param columnMap
	 * @throws ManagementException
	 */
	public void addCoulumn(String tableName, Map<String, String> columnMap)
			throws ManagementException {
		exColumnDao.addCoulumn(tableName, columnMap);
		setColumnExtendList(tableName);
	}

	/**
	 * List extension field
	 * 
	 * @param tableName
	 * @return map collection
	 * @throws ManagementException
	 */
	public Map<String, String> listCoulumn(String tableName)
			throws ManagementException {
		return exColumnDao.listCoulumn(tableName);
	}

	/**
	 * Update the extension field
	 * 
	 * @param tableName
	 * @param oldColumnName
	 * @param newCloumnName
	 * @param columnLength
	 * @throws ManagementException
	 */
	public void updateCoulumn(String tableName, String oldColumnName,
			String newCloumnName, String columnLength)
			throws ManagementException {
		exColumnDao.updateCoulumn(tableName, oldColumnName, newCloumnName,
				columnLength);
		setColumnExtendList(tableName);

	}

	/**
	 * Delete the extension field
	 * 
	 * @param tableName
	 * @param columnName
	 * @throws ManagementException
	 */
	public void deleteCoulumn(String tableName, String columnName)
			throws ManagementException {
		exColumnDao.deleteCoulumn(tableName, columnName);
		setColumnExtendList(tableName);
	}

	/**
	 * When the extension field after the update is complete, call the method
	 * will be updated to add data to the ColumnCache cache
	 * 
	 * @param tableName
	 */
	private void setColumnExtendList(String tableName) { }
}
