package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import service.StuManager;
import service.impl.StuManagerImpl;
import vo.Student;

@Controller
public class StuManagerController {
	@Autowired(required=true)
	private  StuManager sm;
	
	@RequestMapping("/stuLogin")
	public String login(String xh,String psd,HttpServletRequest request){
		
		
		Student s=sm.login(xh, psd);
		if(s!=null){
			request.getSession().setAttribute("s", s);
			return "WEB-INF/jps/loginSuc";
		}
		else{
			return "login";
		}
		
		
	}
	@RequestMapping("/stuRegist")
	public String regist(Student s,HttpServletRequest request){
		System.out.println(s.getUname()+"........");
		int rs=sm.regist(s);
		if(rs>0)
		return "redirect:/regSuc.jsp";
		else return "regist";
	}
	
	
	
	@RequestMapping("/tes1")
	public String test1(OutputStream out){
		try {
			InputStream in=new FileInputStream("e:/aa.jpg");
			byte[] buf=new byte[1024];
			int len=0;
			while((len=in.read(buf))!=-1){
				out.write(buf, 0, len);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		 
	}
	
	
	
	@RequestMapping("/test2")
	@ResponseBody
	public String test2(){
		
		
		return "Hello workd!!!!";
		
		 
	}
	
	
	@RequestMapping("/test3")
	@ResponseBody
	public Student test3(String uname,int age){
		System.out.println("clikck......"+uname+","+age);
		
		Student s = new Student();
		s.setId(100);
		s.setXh("123");
		s.setUname("John");
		
		return s;
		
		 
	}
	
	
	
}
