package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.johnyu.util.BaseUtil;
import vo.Student;
@Aspect
@Component
public class SecAspect {
	private final String registPc="execution(* service.StuManager.regist(..))";
	private final String loginPc="execution(* service.StuManager.login(..))";
	@Around(registPc)
	public Object registMdProcess(ProceedingJoinPoint jp){
		Object o=null;
		Student stu=(Student)jp.getArgs()[0];
		stu.setPsd(BaseUtil.JohnMd(stu.getPsd(), "md5"));
		try {
			o=jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	@Around(loginPc)
	public Object loginMdProcess(ProceedingJoinPoint jp){
		Object o=null;
		String psd=(String)jp.getArgs()[1];
		try {
			o=jp.proceed(new Object[]{jp.getArgs()[0],BaseUtil.JohnMd(psd, "md5")});
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
				
	}
}
