package com.samsung.service.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.samsung.service.service.AscLogService;
import com.samsung.service.service.BatLogService;
import com.samsung.service.util.MonitorUtil;
import com.samsung.service.vo.MonitorListVO;
import com.samsung.service.vo.MonitorVO;

@Controller
@RequestMapping("/monitor")
public class MonitorController {

	private List<String> companyList;

	/*	@Autowired
	private SkpUserService skpUserService;
	 */
	@Autowired
	private BatLogService batLogService;

	@Autowired
	private MessageSource messageSource;

	@Value("10")
	private int maxResults;

	private static Logger logger = Logger.getLogger(MonitorListVO.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {  
		return new ModelAndView("monitor");
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
		return createListAllResponse(page, locale);
	}

	private MonitorListVO listAll(int page) {

		return null;
	}

	private ResponseEntity<MonitorListVO> returnListToUser(MonitorListVO monitorList) {
		return new ResponseEntity<MonitorListVO>(monitorList, HttpStatus.OK);
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
		return createListAllResponse(page, locale, null);
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
		MonitorListVO monitorListVO = listAll(page);

		addActionMessageToVO(monitorListVO, locale, messageKey, null);

		return returnListToUser(monitorListVO);
	}

	private MonitorListVO addActionMessageToVO(MonitorListVO monitorListVO, Locale locale, String actionMessageKey, Object[] args) {
		if (StringUtils.isEmpty(actionMessageKey)) {
			return monitorListVO;
		}

		monitorListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

		return monitorListVO;
	}
}