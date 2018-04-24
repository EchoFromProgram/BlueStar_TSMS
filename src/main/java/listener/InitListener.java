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
public class InitListener implements ServletContextListener
{
	//@Resource(name="initServiceImpl")
	private InitService initService;
	
	@Override
	public void contextDestroyed(ServletContextEvent event)
    {
	}

	@Override
	public void contextInitialized(ServletContextEvent event)
    {
        // TODO 这里有个空指针异常！！
        //initService = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()).getBean(InitService.class);
		//Map<Integer, String> map = initService.getAllPowers();
		//event.getServletContext().setAttribute("powerMap", map);
		//System.out.println(initService + "权限表数据准备完毕");
		System.out.println(initService);
	}

}
