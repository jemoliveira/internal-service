package com.samsung.service.controller;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
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

import com.samsung.service.service.ContactService;
import com.samsung.service.vo.ContactListVO;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
    private ContactService contactService;

	@Value("10")
	private int maxResults;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView welcome() {  
		return new ModelAndView("test");
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
		return createListAllResponse(page, locale);
	}

	private ContactListVO listAll(int page) {
        return contactService.findAll(page, maxResults);
    }

	private ResponseEntity<ContactListVO> returnListToUser(ContactListVO monitorList) {
		return new ResponseEntity<ContactListVO>(monitorList, HttpStatus.OK);
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
		return createListAllResponse(page, locale, null);
	}

	private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
		ContactListVO ContactListVO = listAll(page);

		addActionMessageToVO(ContactListVO, locale, messageKey, null);

		return returnListToUser(ContactListVO);
	}

	private ContactListVO addActionMessageToVO(ContactListVO ContactListVO, Locale locale, String actionMessageKey, Object[] args) {
		if (StringUtils.isEmpty(actionMessageKey)) {
			return ContactListVO;
		}

		ContactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

		return ContactListVO;
	}
}