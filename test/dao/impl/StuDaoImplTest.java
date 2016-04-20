package test.dao.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import vo.Student;
import dao.StuDao;
import dao.impl.StuDaoImpl;

public class StuDaoImplTest {
	private static StuDao sd;
	@BeforeClass
	public static void beforeClass(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		sd=(StuDao)context.getBean("sd");
	}
	
	@Test
	public void testFind(){
		Student s1=sd.findStu("003", "ddfdsfsdf");
		Student s2=sd.findStu("003", "ddfdsfsddfdf");
		Assert.assertTrue(s1!=null);
		Assert.assertTrue(s2==null);
		
		
		
	}
	@Test
	public void testAddStu(){
		Student s=new Student();
		s.setUname("John¿´");
		s.setXh("003");
		s.setSex(true);
		s.setGroupid(1);
		s.setPsd("ddfdsfsdf");
		
		int rs=sd.addStu(s);
		
		Assert.assertTrue(rs>=1);
	}
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
}
