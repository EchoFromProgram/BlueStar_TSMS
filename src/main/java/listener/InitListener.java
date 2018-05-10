package listener;


import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dto.AccountDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.InitService;

@Component
public class InitListener implements ServletContextListener
{
    //@Resource(name="initServiceImpl")
    private InitService initService = null;
    private ServletContext servletContext = null;

    // log4j 记录日志
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
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
        servletContext =  event.getServletContext(); // 准备工作

        load(initService.getAllPowers(), "powerMap", "权限表"); // 加载权限表
        load(initService.getAllCourses(), "Courses", "课程表"); // 加载课程表
    }

    /**
     * 加载数据
     *
     * @param accountDto 数据来源
     * @param data 数据 key 值
     * @param dataName 数据提示信息名字
     */
    private void load(AccountDto accountDto, String data, String dataName)
    {
        if (accountDto.getData() == null)
        {
            logger.error(dataName + "加载失败！！！！！！！！");
        }
        else
        {
            // 放进 Context
            servletContext.setAttribute(data, accountDto.getData());
            logger.info(dataName + "数据准备完毕！！！！！！！！！");
        }
    }
}
