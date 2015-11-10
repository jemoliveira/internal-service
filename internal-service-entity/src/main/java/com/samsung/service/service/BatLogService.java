package com.samsung.service.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samsung.service.helper.Email;
import com.samsung.service.model.BatLogBean;
import com.samsung.service.model.UserNotifiesBean;
import com.samsung.service.repository.BatLogRepository;
import com.samsung.service.repository.UserNotifiesRepository;
import com.samsung.service.util.GenericsDateUtil;
import com.samsung.service.util.MonitorUtil;
import com.samsung.service.util.MonitorValidator;
import com.samsung.service.util.Sftp;
import com.samsung.service.vo.MonitorListVO;
import com.samsung.service.vo.MonitorVO;

@Service
@Transactional
public class BatLogService {

	private static Logger logger = Logger.getLogger(BatLogService.class);

	@Autowired
	@Qualifier("mySQLServerDataSource")
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	private BatLogRepository batLogRepository;

	@Autowired
	private UserNotifiesRepository userNotifiesRepository;

	@Transactional(readOnly = true)
	public MonitorListVO findAll(int page, int maxResults) {
		Page<BatLogBean> result = executeQueryFindAll(page, maxResults);

		if(shouldExecuteSameQueryInLastPage(page, result)){
			int lastPage = result.getTotalPages() - 1;
			result = executeQueryFindAll(lastPage, maxResults);
		}

		return buildResult(result);
	}

	public void save(BatLogBean vo) {
		batLogRepository.save(vo);
	}

	@Secured("ROLE_ADMIN")
	public void delete(int id) {
		batLogRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public MonitorListVO findByNameLike(int page, int maxResults, String name) {
		Page<BatLogBean> result = executeQueryFindByName(page, maxResults, name);

		if(shouldExecuteSameQueryInLastPage(page, result)){
			int lastPage = result.getTotalPages() - 1;
			result = executeQueryFindByName(lastPage, maxResults, name);
		}

		return buildResult(result);
	}

	private boolean shouldExecuteSameQueryInLastPage(int page, Page<BatLogBean> result) {
		return isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result);
	}

	private Page<BatLogBean> executeQueryFindAll(int page, int maxResults) {
		final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNameASC());

		return batLogRepository.findAll(pageRequest);
	}

	private Sort sortByNameASC() {
		return new Sort(Sort.Direction.ASC, "name");
	}

	private MonitorListVO buildResult(Page<BatLogBean> result) {
		//return new MonitorListVO(result.getTotalPages(), result.getTotalElements(), result.getContent());
		return new MonitorListVO();
	}

	private Page<BatLogBean> executeQueryFindByName(int page, int maxResults, String name) {
		final PageRequest pageRequest = new PageRequest(page, maxResults, sortByNameASC());

		return batLogRepository.findByNameLike(pageRequest, "%" + name + "%");
	}

	private boolean isUserAfterOrOnLastPage(int page, Page<BatLogBean> result) {
		return page >= result.getTotalPages() - 1;
	}

	private boolean hasDataInDataBase(Page<BatLogBean> result) {
		return result.getTotalElements() > 0;
	}

	public List<MonitorVO> listLastRegisters(int right, String batchName, List<MonitorVO> empList, String company) {
		
/*		String situation = "";

		situation = MonitorValidator.checkWebService("http://64.22.74.79:8090/ws-tracking/ConsultaRequest?wsdl", "");*/
		

		//JDBC Code - Start
		String query = "select top 2 Company, RunDateTime, Success_Y_N, Message, id_user from BAT_LOG "
				+ "where BATCH_NAME = '"+batchName+"' AND COMPANY = '"+company+"' order by RunDateTime desc";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String,Object>> empRows = jdbcTemplate.queryForList(query);
		
		if (empRows.size() != 0) {
			int count = 0;
			MonitorVO log = new MonitorVO();

			for(Map<String,Object> empRow : empRows) {

				if (count == 0) {

					if (batchName.equals("ALL_T")) {
						batchName = "All Transaction";
					}

					if (batchName.equals("SAW_Risk")) {
						batchName = "SAW Report";
					}

					if (batchName.equals("TMS")) {
						batchName = "TMS Report";
					}

					if (batchName.equals("PERCEPTION")) {
						batchName = "Perception Report";
					}

					if (batchName.equals("POST_TAT")) {
						batchName = "DHL POST TAT";
					}

					if (batchName.equals("IVOY_TAT")) {
						batchName = "IVOY POST TAT";
					}

					if (batchName.equals("PARTS_TRACKING")) {
						batchName = "PARTS TRACKING";
					}
					
					if (batchName.equals("CORREIOS_TRACKING")) {
						batchName = "CORREIOS TRACKING";
					}

					log.setCompany(String.valueOf(empRow.get("Company")));
					log.setlUpdate(String.valueOf(empRow.get("RunDateTime")).substring(0, 19));
					log.setSuccess(String.valueOf(empRow.get("Success_Y_N")));
					log.setMessage(String.valueOf(empRow.get("Message")));
					log.setUser(String.valueOf(empRow.get("id_user")).trim());	
					log.setName(batchName);
				}

				if (count == 1) {
					log.setpUpdate(String.valueOf(empRow.get("RunDateTime")).substring(0, 19));				
					
				}

				count++;
			}
			
			log.setSituation(MonitorValidator.monitorValidate(log.getlUpdate(), right));

			empList.add(log);
		}

		return empList;
	}
	
	public List<MonitorVO> createLogToAscInterface(List<MonitorVO> empList, String url) {

		MonitorVO log = new MonitorVO();
		String situation = "";

		situation = MonitorValidator.checkWebService(url, "saGSPNdev");
		logger.info("Updated saGSPNdev");

		situation = MonitorValidator.checkWebService("http://latin.samsungasc.com/samsungws/saGSPNPRD.jws?WSDL=", "saGSPNPRD");
		logger.info("Updated saGSPNPRD");

		log.setName("ASC Interface");
		log.setlUpdate(GenericsDateUtil.convertDateToString());
		log.setSituation(situation);				
		log.setCompany("ALL");
		
		sendEmailAndSMS(log);

		empList.add(log);

		return empList;		
	}

	private void sendEmailAndSMS(MonitorVO log) {

		if (log.getSituation().equals("stop")) {
			
			logger.info("## " + log.getName() + " is stopped");

			List<UserNotifiesBean> list = (List<UserNotifiesBean>) userNotifiesRepository.findAll();

			for (UserNotifiesBean bean : list) {

				String time = String.valueOf(new Date().getHours())+":"+String.valueOf(new Date().getMinutes());

				for (String schedule : MonitorUtil.getSchedules()) {
					
					logger.info("## if current time" + time + " equals schedule time : " + schedule);
					logger.info(time.equals(schedule));

					if (time.equals(schedule)) {
						
						String message = "\n## ERROR ## \nJOB: "+ log.getName() + " \nCompany: " + log.getCompany() + 
								"\nLatest Updates: \n" + log.getpUpdate() + "\n" + log.getlUpdate();
						
						logger.info("## This is the message: " + message);
												
						logger.info("## Send Email");
						Email.sendEmail(bean.getEmail(), bean.getUsername(), message);
					}
				}
			}			
		}	
	}

	public List<MonitorVO> listLastRegistersTelcel(String batchName, String company, List<MonitorVO> empList) {

		//JDBC Code - Start
		String query = "SELECT TOP 2 DATE_OF_INSERTED FROM TB_TC_SERVICE_ORDER "
				+ "ORDER BY DATE_OF_INSERTED desc";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Map<String,Object>> empRows = jdbcTemplate.queryForList(query);

		int count = 0;
		MonitorVO log = new MonitorVO();

		for(Map<String,Object> empRow : empRows){

			log.setCompany(company);

			if (count == 0) {
				log.setlUpdate(String.valueOf(empRow.get("DATE_OF_INSERTED")).substring(0, 19));
				log.setCompany(company);				
				log.setSuccess("Y");
				log.setMessage("--");
				log.setUser("jem.oliveira");	
				log.setName(batchName);				
			}

			if (count == 1) {
				log.setpUpdate(String.valueOf(empRow.get("DATE_OF_INSERTED")).substring(0, 19));				
			}			
			count++;
		}
		
		if (!Sftp.send().equals("stop")) {
			if (MonitorValidator.monitorValidate(log.getlUpdate(), 12).equals("stop")) {
				log.setSituation("warn");
			} else {
				log.setSituation("run");
			}
		}else{
			log.setSituation("run");
		}
		empList.add(log);

		return empList;
	}
	//
}