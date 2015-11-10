package com.samsung.service.jdbcTemplate.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.samsung.service.vo.UserWsVO;

/**
 * Created with IntelliJ IDEA.
 * User: tw
 * Date: 04.09.13
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
public class UserWsTemplateMapper implements RowMapper<UserWsVO> {

	@Override
	public UserWsVO mapRow(ResultSet empRow, int rowNum) throws SQLException {
		UserWsVO vo = new UserWsVO();
		
		vo.setAscCode(empRow.getString("ASCCODE"));
		vo.setCorpCode(empRow.getString("CORPCODE"));
		vo.setDev(returnYesOrNo(empRow.getString("DEV")));
		vo.setDev(returnYesOrNo(empRow.getString("DEV")));
		vo.setFsc(returnYesOrNo(empRow.getString("FSC")));
		vo.setPrd(returnYesOrNo(empRow.getString("PRD")));
		vo.setToken(returnYesOrNo(empRow.getString("TOKEN")));
		vo.setWsPwd(empRow.getString("WSPWD"));
		vo.setWsUser(empRow.getString("WSUSER"));
		
		return vo;
	}
	
	private static String returnYesOrNo(String value) {
		
		if (value == null || value.equals("0")) {
			return "Não";
		} else {
			return "Sim";
		}
			
			
		/*if (value.equals("1")) {
			return "Sim";
		} else {
			return "Não";
		}*/
	}
}