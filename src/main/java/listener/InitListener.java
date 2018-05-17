package listener;


import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.InitService;
import utils.ContextUtil;

@Component
public class InitListener implements ServletContextListener
{
    //@Resource(name="initServiceImpl")
    private InitService initService = null;

    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
        ContextUtil.destroy(); // 释放资源
    }

    /**
     * 加载全局数据
     *
     * @param event 加载数据要用
     */
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        // 这里有个空指针异常是因为没有配置 context-param 和 ContextLoaderListener 导致的
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        initService = webApplicationContext.getBean(InitService.class);
        ContextUtil.init(event.getServletContext()); // 初始化 ContextUtil

        ContextUtil.load("powerMap", initService.getAllPowers().getData(), "权限表"); // 加载权限表
        ContextUtil.load("Courses", initService.getAllCourses().getData(), "课程表"); // 加载课程表
    }
}
