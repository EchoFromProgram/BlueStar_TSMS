package listener;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.InitService;

@Component
public class InitListener implements ServletContextListener{
	@Resource(name="initServiceImpl")
	InitService initService;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		//Map<Integer, String> map = initService.getAllPowers();
		//event.getServletContext().setAttribute("powerMap", map);
		//System.out.println(initService + "权限表数据准备完毕");
		System.out.println(initService);
	}

}
