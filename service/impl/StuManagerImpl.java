package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.johnyu.util.BaseUtil;
import dao.StuDao;
import dao.impl.StuDaoImpl;
import service.StuManager;
import vo.Student;
@Service("sm")
public class StuManagerImpl implements StuManager {
	@Autowired(required=true)
	private StuDao sd;
	@Override
	public Student login(String xh, String psd) {
		System.out.println(psd);
		return sd.findStu(xh,psd);
	}

	@Override
	@Transactional
	public int regist(Student s) {
		System.out.println(s.getPsd());
		//s.setPsd(BaseUtil.JohnMd(s.getPsd(), "md5"));
		return sd.addStu(s);
	}

}
