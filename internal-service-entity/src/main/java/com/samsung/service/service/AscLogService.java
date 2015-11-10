package com.samsung.service.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;

import com.samsung.service.util.MonitorValidator;
import com.samsung.service.vo.MonitorVO;

public class AscLogService {
	private static Logger logger = Logger.getLogger(AscLogService.class);

	private static final String driverClassName = "oracle.jdbc.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@105.1.11.181:1521:GSPNB";
	private static final String dbUsername = "sagspn";
	private static final String dbPassword = "tlwkd12#";
	private static final long K = 1024;
	private static final long M = K * K;
	private static final long G = M * K;
	private static final long T = G * K;

	@Bean
	public static DataSource dataSource() {
		DataSource dataSource = null;
		JndiTemplate jndi = new JndiTemplate();
		try {
			dataSource = (DataSource) jndi.lookup("jdbc/gspn");
		} catch (NamingException e) {
			logger.error("NamingException for java:comp/env/jdbc/gspn", e);
		}
		return dataSource;
	}

	public static DataSource dataSourceTest() {
		return getDataSource();
	}

	public static DriverManagerDataSource getDataSource() { // NO_UCD (unused
															// code)

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}
	
	public List<MonitorVO> listLastRegisters(int right, String tableName,
			List<MonitorVO> empList, String company) {
		// JDBC Code - Start
		
		String query = "SELECT * FROM "+tableName+" WHERE ROWNUM < 3";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(AscLogService.getDataSource());
		String tabela = tableName;
		Map<String, Object> mapa =(Map<String, Object>)jdbcTemplate.queryForMap(queryMontada(tableName));
		
		List<Map<String, Object>> empRows = jdbcTemplate.queryForList(query);
		Float space =  ( (BigDecimal) mapa.get("total_mb") ).floatValue(); 
		Long rowNum = ( (BigDecimal)   mapa.get("num_rows") ).longValue();
		if (empRows.size() != 0) {
			int count = 0;
			MonitorVO log = new MonitorVO();

			for (Map<String, Object> empRow : empRows) {

				if (count == 0) {
					
					if (tableName.equals("WS_TOKEN_UPLOAD")) {
						tabela = tableName+"_ROW";
						mapa = (Map<String, Object>)jdbcTemplate.queryForMap(queryMontada(tabela));
						space += ( (BigDecimal) mapa.get("total_mb") ).floatValue();
						rowNum += ( (BigDecimal)   mapa.get("num_rows") ).longValue();
						tableName = "TOKEN UPLOAD";
					}

					if (tableName.equals("WS_DO_ADD_FILE")) {
						tabela = tableName+"_ROW";
						mapa =  (Map<String, Object>)jdbcTemplate.queryForMap(queryMontada(tabela));
						space += ( (BigDecimal) mapa.get("total_mb") ).floatValue();
						rowNum += ( (BigDecimal)   mapa.get("num_rows") ).longValue();
						tableName = "DO ADD FILE";
					}

					if (tableName.equals("WS_INVENTORY_SAVE_STOCK")) {
						tabela = tableName+"_ROW";
						mapa =  (Map<String, Object>)jdbcTemplate.queryForMap(queryMontada(tabela));
						space += ( (BigDecimal) mapa.get("total_mb") ).floatValue();
						rowNum += ( (BigDecimal)   mapa.get("num_rows") ).longValue();
						tableName = "INV. SAVE STOCK";
					}

					if (tableName.equals("WS_INVENTORY_SAVE_GR")) {
						tabela = tableName+"_ROW";
						mapa =  (Map<String, Object>)jdbcTemplate.queryForMap(queryMontada(tabela));
						space += ( (BigDecimal) mapa.get("total_mb") ).floatValue();
						rowNum += ( (BigDecimal)   mapa.get("num_rows") ).longValue();
						tableName = "INV. SAVE GR";
					}

					if (tableName.equals("WS_UPDATE_REPAIR_TICKET")) {
						tabela = tableName+"_ROW";
						mapa =  (Map<String, Object>)jdbcTemplate.queryForMap(queryMontada(tabela));
						space += ( (BigDecimal) mapa.get("total_mb") ).floatValue();
						rowNum += ( (BigDecimal)   mapa.get("num_rows") ).longValue();
						tableName = "ASC Interface II";
					}

					if (tableName.equals("WS_INVENTORY_SAVE_GI")) {
						tabela = tableName+"_ROW";
						mapa =  (Map<String, Object>)jdbcTemplate.queryForMap(queryMontada(tabela));
						space += ( (BigDecimal) mapa.get("total_mb") ).floatValue();
						rowNum += ( (BigDecimal)   mapa.get("num_rows") ).longValue();
						tableName = "INV. SAVE GI";
					}
					if (tableName.equals("WS_CREATE_REPAIR_TICKET_LATIN")) {
						tableName = "CREATE REPAIR TICKET";
					}

					if (tableName.equals("WS_UPDATE_POST_TAT_LATIN")) {
						tableName = "UPDATE POST TAT";
					}
					if( tableName.equals("WS_DO_DELETE_FILE")){
						tableName = "DO DELETE FILE";
					}
					
					log.setCompany(company);
					if(empRow.get("RUNTIME") != null){
						log.setlUpdate(String.valueOf(empRow.get("RUNTIME")).substring(0, 19));
					}
					else{
						log.setlUpdate("");
					}
					log.setSuccess(String.valueOf(empRow.get("ERRCODE")));
					log.setMessage(String.valueOf(empRow.get("ERRDESC")));
					log.setUser(String.valueOf( empRow.get("WSUSERID") == null ? "" : empRow.get("WSUSERID") ).trim());
					log.setName(tableName);
					space = space / 1024 / 1024/ 1024;
					log.setSpace(new DecimalFormat("#,##0.#").format(space)+" GB");
					log.setRowNum(rowNum.toString()+" Linhas");
				}
				if(empRow.get("RUNTIME") != null){
					if (count == 1) {
						log.setpUpdate(String.valueOf(empRow.get("RUNTIME"))
								.substring(0, 19));
					}
				}else{
					log.setpUpdate("");
				}

				count++;
			}
			if( "ASC Interface II".equals(tableName) ){
				log.setSituation( MonitorValidator.monitorValidateMinute(log.getlUpdate(),right) );
			}else{
				log.setSituation( "run" );
			}
			empList.add(log);
		}

		return empList;
	}
	public static String convertToStringRepresentation(final long value){
	    final long[] dividers = new long[] { T, G, M, K, 1 };
	    final String[] units = new String[] { "TB", "GB", "MB", "KB", "B" };
	    if(value < 1)
	        throw new IllegalArgumentException("Invalid file size: " + value);
	    String result = null;
	    for(int i = 0; i < dividers.length; i++){
	        final long divider = dividers[i];
	        if(value >= divider){
	            result = format(value, divider, units[i]);
	            break;
	        }
	    }
	    return result;
	}

	private static String format(final long value,
	    final long divider,
	    final String unit){
	    final double result =
	        divider > 1 ? (double) value / (double) divider : (double) value;
	    return new DecimalFormat("#,##0.#").format(result) + " " + unit;
	}
	private String queryMontada(String tableName ){
	return	"select table_name, num_rows, data_mb, indx_mb, lob_mb, total_mb "
			+"from "
			+"(select data.table_name, "
			+"num_rows, "
			+"nvl(data_mb,0) data_mb, "
			+"nvl(indx_mb,0) indx_mb, "
			+"nvl(lob_mb,0) lob_mb,  "
			+"nvl(data_mb,0) + nvl(indx_mb,0) + nvl(lob_mb,0) total_mb "
			+"from "
			+"( select table_name, nvl(min(num_rows),0) num_rows, round(sum(data_mb),2) data_mb " 
			+"from "
			+"(select table_name, num_rows, data_mb from (select a.table_name, a.num_rows, b.bytes as data_mb from user_tables a, user_segments b where a.table_name = '"+tableName+"')) " 
			+"group by table_name) data, "
			+"( select a.table_name, sum(b.bytes) as indx_mb " 
			+"from user_indexes a, user_segments b "  
			+"where a.index_name = b.segment_name "
			+"group by a.table_name) indx, ( select a.table_name, sum(b.bytes) as lob_mb from user_lobs a, user_segments b where a.segment_name = b.segment_name group by a.table_name) lob, user_part_tables part where data.table_name = indx.table_name(+) and data.table_name = lob.table_name(+) and data.table_name = part.table_name(+)) " 
			+"order by data_mb desc";
	}
}
