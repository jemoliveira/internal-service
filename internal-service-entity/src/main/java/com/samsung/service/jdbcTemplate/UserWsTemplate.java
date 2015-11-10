package com.samsung.service.jdbcTemplate;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import com.samsung.service.jdbcTemplate.mapper.UserWsTemplateMapper;
import com.samsung.service.util.DataSourceConfig;
import com.samsung.service.vo.UserWsVO;

public class UserWsTemplate {

	private static Logger logger = Logger.getLogger(UserWsTemplate.class);
	private static JdbcTemplate jdbcTemplate;
	private static String SELECT = "SELECT * FROM T_WS_USER_MT_V2";
	private static String SELECT_COUNT = "select count(*) from ("+SELECT+")";
	private static String INSERT = "INSERT INTO T_WS_USER_MT_V2 VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static String UPDATE = "UPDATE T_WS_USER_MT_V2 SET "
			+ "WSUSER = ?, WSPWD = ?, ASCCODE = ?, CORPCODE = ?, DEV = ?, FSC = ?, PRD = ?, TOKEN = ? "
			+ "WHERE WSUSER = ?";
	private static String DELETE = "DELETE FROM T_WS_USER_MT_V2 WHERE WSUSER = ?";

	@Autowired
	public static void getDataSource() {
		jdbcTemplate = new JdbcTemplate(DataSourceConfig.getDataSource());
	}
	
	public static Page<UserWsVO> findByNameLike(Pageable pageable, String name) {
		
		String like = " WHERE WSUSER LIKE '%"+name+"%'";

		if (jdbcTemplate == null)		
			getDataSource();

		long total = jdbcTemplate.queryForLong(SELECT_COUNT);
		List<UserWsVO> list = jdbcTemplate.query(SELECT+like, new UserWsTemplateMapper());

		int toIndex = pageable.getOffset() + pageable.getPageSize() > list.size() ? list.size() : 
			pageable.getOffset() + pageable.getPageSize();

		Page<UserWsVO> page = new PageImpl<UserWsVO>(list.subList(pageable.getOffset(),toIndex), 
				pageable, total);
		
		return page;
	}

	public static String insert(UserWsVO vo) {
		
		mountCheckBox(vo);
		
		int result;

		try {
			if (jdbcTemplate == null)		
				getDataSource();
			
			logger.info("INSERT INTO T_WS_USER_MT_V2 VALUES ('"+vo.getAscCode()+ "', '"
					+vo.getCorpCode()+ "', '"
					+vo.getDev()+ "', '"
					+vo.getFsc()+ "', '"
					+vo.getPrd()+ "', '"
					+vo.getToken()+ "', '"
					+vo.getWsPwd()+ "', '"
					+vo.getWsUser()+ "')");

			result = jdbcTemplate.update(INSERT, new Object[] { 
					vo.getAscCode(), 
					vo.getCorpCode(), 
					vo.getDev(), 
					vo.getFsc(),
					vo.getPrd(),
					vo.getToken(),
					vo.getWsPwd(),
					vo.getWsUser()
			});

			if (result == 1) {
				logger.info("Successfully Inserted!");
				return "Successfully Inserted!";
			}

		} catch (Exception e) {			
			logger.error("## " + e.toString());
			return e.getCause().getMessage();
		}

		return String.valueOf(result);
	}
	
	public static String update(UserWsVO vo) {
		
		mountCheckBox(vo);
		
		int result;

		try {
			if (jdbcTemplate == null)		
				getDataSource();
			
			logger.info("UPDATE T_WS_USER_MT_V2 SET WSUSER = '"+vo.getWsUser()+ "', "
					+ "WSPWD = '" + vo.getWsPwd()+ "', "
					+ "ASCCODE = '"+ vo.getAscCode()+ "', "
					+ "CORPCODE = '" + vo.getCorpCode()+ "', "
					+ "DEV = " + vo.getDev()+ ", "
					+ "FSC = " + vo.getFsc()+ ", "
					+ "PRD = " + vo.getPrd()+ ", "
					+ "TOKEN = " + vo.getToken()+ " WHERE WSUSER = '" + vo.getWsUser() + "'");

			result = jdbcTemplate.update(UPDATE, new Object[] { 
					vo.getWsUser(), 
					vo.getWsPwd(), 
					vo.getAscCode(), 
					vo.getCorpCode(),
					vo.getDev(),
					vo.getFsc(),
					vo.getPrd(),
					vo.getToken(),
					vo.getWsUser()
			});

			if (result == 1) {
				logger.info("Successfully Updated!");
				return "Successfully Updated!";
			}

		} catch (Exception e) {			
			logger.error("## " + e.toString());
			return e.getCause().getMessage();
		}

		return String.valueOf(result);
	}
	
	public static String delete(String wsUser) {
		
		int result;
		
		try {
			if (jdbcTemplate == null)		
				getDataSource();
			
			logger.info("DELETE FROM T_WS_USER_MT_V2 WHERE WSUSER = '"+ wsUser + "'");
			
			result = jdbcTemplate.update(DELETE, new Object[] {wsUser});
			
			if (result == 1) {
				logger.info("Successfully Deleted!");
				return "Successfully Deleted!";
			}
			
		} catch (Exception e) {
			logger.error("## " + e.toString());
			return e.getCause().getMessage();
		}
		
		return  String.valueOf(result);
	}

	public static void mountCheckBox(UserWsVO vo) {
		if (vo.getDev() == null || vo.getDev().equals("false")) {
			vo.setDev("0");
		} else {
			vo.setDev("1");
		}
		
		if (vo.getPrd() == null || vo.getPrd().equals("false")) {
			vo.setPrd("0");
		} else {
			vo.setPrd("1");
		}
		
		if (vo.getToken() == null || vo.getToken().equals("false")) {
			vo.setToken("0");
		} else {
			vo.setToken("1");
		}
		
		if (vo.getFsc() == null || vo.getFsc().equals("false")) {
			vo.setFsc("0");
		} else {
			vo.setFsc("1");
		}
	}

	public static Page<UserWsVO> findAll(Pageable pageable) {

		if (jdbcTemplate == null)		
			getDataSource();

		long total = jdbcTemplate.queryForLong(SELECT_COUNT);
		List<UserWsVO> list = jdbcTemplate.query(SELECT, new UserWsTemplateMapper());

		int toIndex = pageable.getOffset() + pageable.getPageSize() > list.size() ? list.size() : 
			pageable.getOffset() + pageable.getPageSize();

		Page<UserWsVO> page = new PageImpl<UserWsVO>(list.subList(pageable.getOffset(),toIndex), 
				pageable, total);
		return page;
	}
}