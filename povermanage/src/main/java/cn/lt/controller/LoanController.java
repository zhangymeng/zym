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

import cn.lt.po.Department;
import cn.lt.po.Loan;
import cn.lt.po.LoanDepartment;
import cn.lt.po.LoanStudent;
import cn.lt.po.UserInfo;
import cn.lt.service.LoanService;
import cn.lt.service.StudentService;
import cn.lt.vo.IndexVo;
import cn.lt.vo.LoanVo;

@Controller
@RequestMapping("/loan")
public class LoanController {
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * menu2 路由
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/loanPage")
	public ModelAndView loanPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		String urlStr = "";
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getRoleId()==1){
				urlStr = "loanPage";
			}else{
				urlStr = "loanDepartmentPage";
			}
		}
	    return new ModelAndView(urlStr, model);
	}
	
	/**
	 * 助学贷款列表   院系助学贷款
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			if(user.getRoleId()==1 && vo.getType()==null){
				//超管显示p_loan
				List<Loan> list = loanService.findLoan(vo);
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", list.size());
				map.put("data", list);
			}else{
				//院系管理员显示p_loan_department
				if(user.getdId()!=null && user.getdId()!=0){
					vo.setdId(user.getdId());
				}
				List<LoanDepartment> list = loanService.findLoanDepartment(vo);
				map.put("code", 0);
				map.put("msg", "");
				map.put("count", list.size());
				map.put("data", list);
			}
		}
        return map;
    }
	
	/**
	 * 删除助学贷款条目  id （loan的id�?
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/delLoan")
	@ResponseBody
    public Map<String,Object> delLoan(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = loanService.delLoan(vo);
        return map;
    }
	
	/**
	 * 添加助学贷款
	 * @param request
	 * @param vo
	 * @return
	 */
	@RequestMapping("/addLoan")
	@ResponseBody
    public Map<String,Object> addLoan(HttpServletRequest request,LoanVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = loanService.editLoan(vo);
		}else{
			map = loanService.addLoan(vo);
		}
		
        return map;
    }
	
	/**
	 * 添加院系助学贷款写页面
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public ModelAndView addPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
	    return new ModelAndView("addLoan", model);
	}
	
	@RequestMapping("/addLDPage")
	public ModelAndView addLDPage(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		List<Department> dList = studentService.findDepartment(vo);
		model.addAttribute("dList", dList);
		model.addAttribute("loanId", vo.getLoanId());
	    return new ModelAndView("addLD", model);
	}
	
	@RequestMapping("/delLD")
	@ResponseBody
    public Map<String,Object> delLD(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = loanService.delLD(vo);
        return map;
    }
	
	@RequestMapping("/addLD")
	@ResponseBody
    public Map<String,Object> addLD(HttpServletRequest request,LoanVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()!=null){
			map = loanService.editLD(vo);
		}else{
			map = loanService.addLD(vo);
		}
        return map;
    }
    
	@RequestMapping("/addLS")
	@ResponseBody
    public Map<String,Object> addLS(HttpServletRequest request,LoanVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			//判断是否是本院系学生
			vo.setdId(user.getdId());
			
			vo.setAdminId(user.getId());
			map = loanService.addLS(vo);
		}
        return map;
    }
	
	@RequestMapping("/loanStudent")
	public ModelAndView loanStudent(IndexVo vo,HttpServletRequest request){
		ModelMap model = new ModelMap();
		model.addAttribute("loanId", vo.getLoanId());
	    return new ModelAndView("loanStudent", model);
	}
	
	@RequestMapping("/allLS")
	@ResponseBody
    public Map<String,Object> allLS(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			//判断是否是本院系学生
			if(user.getdId()!=0){
				vo.setdId(user.getdId());
			}
			List<LoanStudent> list = loanService.allLS(vo);
			map.put("code", 0);
			map.put("msg", "");
			map.put("count", list.size());
			map.put("data", list);			
		}
        return map;
    }
	
	@RequestMapping("/delLS")
	@ResponseBody
    public Integer delLS(HttpServletRequest request,IndexVo vo){
		Integer count = loanService.delLS(vo);
        return count;
    }
}
