package cn.lt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.lt.po.AidStudent;
import cn.lt.po.Department;
import cn.lt.po.LoanStudent;
import cn.lt.po.Professional;
import cn.lt.po.ScholarshipStudent;
import cn.lt.po.SocialStudent;
import cn.lt.po.Student;
import cn.lt.po.UserInfo;
import cn.lt.service.AidService;
import cn.lt.service.LoanService;
import cn.lt.service.ScholarshipService;
import cn.lt.service.SocialService;
import cn.lt.service.StudentService;
import cn.lt.vo.IndexVo;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private ScholarshipService scholarshipService;
	
	@Autowired
	private SocialService socialService;
	
	@Autowired
	private AidService aidService;
	
	/**
	 * 贫困生管�?
	 * @param request
	 * @return
	 */
	@RequestMapping("/page")
    public ModelAndView getListaUtentiView(HttpServletRequest request,IndexVo vo){
		ModelMap model = new ModelMap();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			model.addAttribute("userInfo", user);
			if(user.getRoleId()==1){
				List<Department> dList = studentService.findDepartment(vo);
				vo.setdId(dList.get(0).getId());
				List<Professional> pList = studentService.getProfessional(vo);
				model.addAttribute("dList", dList);
				model.addAttribute("pList", pList);
			}else{
				Department d = studentService.getDepartmentById(user.getdId());
				model.addAttribute("department", d);
				vo.setdId(user.getdId());
				List<Professional> pList = studentService.getProfessional(vo);
				model.addAttribute("pList", pList);
			}
		}
        return new ModelAndView("student", model);
    }
	
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null && user.getRoleId()!=1){
			vo.setdId(user.getdId());
		}
		List<Student> list = studentService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }

	@RequestMapping("/del")
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = studentService.del(vo);
        return map;
    }
	
	@RequestMapping("/addPage")
    public ModelAndView addPage(HttpServletRequest request,IndexVo vo){
		ModelMap model = new ModelMap();
		//�?��院系
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getRoleId()==1){
				List<Department> dList = studentService.findDepartment(vo);
				vo.setdId(dList.get(0).getId());
				List<Professional> pList = studentService.getProfessional(vo);
				model.addAttribute("dList", dList);
				model.addAttribute("pList", pList);
			}else{
				Department d = studentService.getDepartmentById(user.getdId());
				model.addAttribute("department", d);
				vo.setdId(user.getdId());
				List<Professional> pList = studentService.getProfessional(vo);
				model.addAttribute("pList", pList);
				
			}
		}
        return new ModelAndView("addStu", model);
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public Map<String,Object> add(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = studentService.add(vo);
        return map;
    }
	
	@RequestMapping("/getProfessional")
	@ResponseBody
    public List<Professional> getProfessional(HttpServletRequest request,IndexVo vo){
		List<Professional> list = studentService.getProfessional(vo);
        return list;
    }
	
	@RequestMapping("/getStuByNo")
	@ResponseBody
    public Student getStuByNo(String stuNo){
		Student stu = studentService.getStudentByNo(stuNo);
        return stu;
    }
	
	@RequestMapping("/povertyPage")
	public ModelAndView povertyPage(HttpServletRequest request,IndexVo vo){
		ModelMap model = new ModelMap();
		//贷款
		List<LoanStudent> loanList = loanService.allLS(vo);
		model.addAttribute("loanList", loanList);
		//社会资助
		List<SocialStudent> socialList = socialService.allSS(vo);
		model.addAttribute("socialList", socialList);
		//奖学金
		List<ScholarshipStudent> shipList = scholarshipService.allSS(vo);
		model.addAttribute("shipList", shipList);
		//助学金
		List<AidStudent> aidList = aidService.allAS(vo);
		model.addAttribute("aidList", aidList);
		
		return new ModelAndView("povertyPage", model);
	}
	
}