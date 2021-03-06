package com.sds.icto.emaillist.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.emaillist.dao.EmailDao;
import com.sds.icto.emaillist.vo.EmailVO;

@Controller
public class EmailListController {
	
	@Autowired
	EmailDao emailDao;
	
	@RequestMapping("/index")
	public String index(Model model){
		ArrayList<EmailVO> list = emailDao.selectList();
		model.addAttribute("list", list);
		return "show_emaillist";
		// view 리졸버가 prefix와 suffix를 붙여준다.
	}
	
	@RequestMapping("/form")
	public String form(){
		return "form_emaillist";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestParam("fn") String firstName, 
						@RequestParam("ln") String lastName, 
						@RequestParam String email){
		
		EmailVO vo = new EmailVO();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		
		emailDao.insert(vo);
		return "redirect:/index";
	}
}
