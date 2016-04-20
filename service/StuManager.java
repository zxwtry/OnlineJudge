package service;

import vo.Student;

public interface StuManager {
	public Student  login(String xh,String psd);
	public int regist(Student s);
}
