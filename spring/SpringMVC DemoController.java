package com.froad.directbank.dsa.api.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.froad.directbank.iframe.utils.io.PropertiesUtil;
import com.froad.directbank.dsa.dto.DemoDto;
import com.froad.directbank.dsa.service.DemoService;

@Controller(value = "demoController")
@RequestMapping(value = "/demo/demotrl" )
public class DemoController extends AbstractController{ 
	
	@Resource(name = "demoServiceImpl")
	private DemoService demoService;
	

	@RequestMapping(value = "/demo1", method = {RequestMethod.POST, RequestMethod.GET})
	//请求参数包含特定参数名@RequestMapping( params = {"name", "pwd"}, method = {RequestMethod.GET}) 匹配：http://XXX/create?name = "test" & pwd = "test"
	//请求参数不包含特定参数名@RequestMapping( params = {"!name"}, method = {RequestMethod.GET}) 匹配：http://XXX/create? pwd = "test"
	//请求参数包含定参数名的值@RequestMapping( params = {"submitflag = 1"}, method = {RequestMethod.GET}) 匹配：http://XXX/create?submitflag=1 & pwd = "test"
	//请求参数不包含定参数名的值@RequestMapping( params = {"submitflag != 1"}, method = {RequestMethod.GET}) 匹配：http://XXX/create?submitflag=2 & pwd = "test"
	//请求参数且关系@RequestMapping( params = {"test1" , "test2 = create"}, method = {RequestMethod.GET}) 匹配：http://XXX/create?test1=2 & test2 = "create"
	//@RequestMapping(value = "/demo1/{id}/create", method = RequestMethod.POST)
	//@RequestMapping(value = "/demo1/{id}/create/{productCode}", method = RequestMethod.POST)
	//@RequestMapping(value = "/demo1/product?", method = RequestMethod.POST)
	//@RequestMapping(value = "/demo1/product*", method = RequestMethod.POST)
	//@RequestMapping(value = "/demo1/product/*", method = RequestMethod.POST)
	//@RequestMapping(value = "/demo1/**/{productId}", method = RequestMethod.POST)
	//正则表达式（spring3.0以上）@RequestMapping(value = "/demo1/{categoryCode:\\d+}-{pageNumber:\\d+}", method = RequestMethod.POST)
	//或组合@RequestMapping(value = {"/test1", "/user/create"}, method = RequestMethod.POST)
	
	//请求头中必须有Accept参数才能匹配@RequestMapping(value = "/demo1/productId", method = RequestMethod.POST, headers = "Accept")
	//请求头中不能没有abc参数才能匹配@RequestMapping(value = "/demo1/productId", method = RequestMethod.POST, headers = "!abc")
	//请求头中必须有Content-Type = application/json 参数才可匹配@RequestMapping(value = "/demo1/productId", method = RequestMethod.POST, headers = "Content-Type = application/json")
	//请求头中必须有如Accept = "text/plan" 参数才可匹配@RequestMapping(value = "/demo1/productId", method = RequestMethod.POST, headers = "Accept = "text/*")
	//请求头中不能有Accept = "text/plan" 参数才可匹配@RequestMapping(value = "/demo1/productId", method = RequestMethod.POST, headers = "Accept != "text/plain")
	//请求头中必须有Accept 参数但是值不能是"text/plan"且请求中必须有参数abc=3才可匹配@RequestMapping(value = "/demo1/productId", method = RequestMethod.POST, headers ={"Accept != "text/plain", abc = 3} )
	
	
	
	public ModelAndView demo1(HttpServletRequest req, HttpServletResponse res, DemoDto demo) {
		//校验表单
		validator(demo);
		demoService.save(demo);
		//收集参数
		//绑定参数到对象
		//调用业务对象
		//选择下一个页面
		ModelAndView view = new ModelAndView();
		view.addObject("message", "demo");
		view.setViewName("demo");
		//view.setViewName("redirect:/demo");
		return view;
	}
	
	@RequestMapping(value = "/demo2/update")//绑定demo2/update?username = "123" defaultValue可以使用spel表达式
	public ModelAndView demo2(@RequestParam(value = "username", required = true, defaultValue = "test") String name) {
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/demo3/{userid}/update/{productCode}")
	public ModelAndView demo3(@PathVariable(value = "userId") int userid, @PathVariable(value = "productCode") int productid) {
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/demo4/update")//将cookie数据映射到功能处理方法参数上
	//public ModelAndView demo4(@CookieValue(value = "JSEESION", defaultValue = "test") Cookie cookie) {
	public ModelAndView demo4(@CookieValue(value = "JSEESION", defaultValue = "test") String sessionId) {
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/demo5/update")//绑定请求头数据
	public ModelAndView demo5(@RequestHeader("user-Agent")String userAgent, @RequestHeader(value = "Accept") String[] accepts) {
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/demo6/update")//绑定请求参数到命令对象,在视图页面可以通过${demo.username}来获取命令对象的属性值
	public ModelAndView demo6(@ModelAttribute("user") DemoDto demo) {
		return new ModelAndView();
	}
	
	@RequestMapping(value = "/demo7/update")//绑定请求参数到命令对象,在视图页面可以通过${demo.username}来获取命令对象的属性值
	public ModelAndView demo7(@ModelAttribute("user") DemoDto demo) {
		return new ModelAndView();
	}
	
}
