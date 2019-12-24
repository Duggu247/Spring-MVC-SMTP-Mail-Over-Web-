package com.duggu;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {
	@RequestMapping("/showMailForm")
	public ModelAndView redirect()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("../pages/emailForm");
		return mv;
	}
	
	@RequestMapping("/sendEmail")
	public ModelAndView sendWebmail(HttpServletRequest req , HttpServletResponse res)
	{
		SendWebMail obj_send_mail=new SendWebMail();		
		String subject = req.getParameter("subject");
		String messageBody = req.getParameter("message");
		String recipient = req.getParameter("mailTo");
		String response = obj_send_mail.send_mail(subject,messageBody, recipient);	
		ModelAndView mv = new ModelAndView();
		if(response.equals("Success"))
		{
			mv.setViewName("../pages/success");
			mv.addObject("message","Mail has been sent successfully.");
		}
		else
		{
			mv.setViewName("../pages/error");
			mv.addObject("message","Sending failure.");
		}
		return mv;
		
	}
}
