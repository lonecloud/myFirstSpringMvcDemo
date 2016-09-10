package cn.lonecloud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class TestController implements Controller {

	private static final Log logger = LogFactory.getLog(TestController.class);

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("运行" + this.getClass() + "-----");
		return new ModelAndView("/WEB-INF/jsp/index.jsp");
	}

}
