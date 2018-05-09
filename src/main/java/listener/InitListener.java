package listener;


import javax.annotation.Resource;
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
    private InitService initService;

    // log4j 记录日志
    Logger logger = Logger.getLogger(this.getClass());

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
        AccountDto accountDto = initService.getAllPowers();
        if (accountDto.getData() == null)
        {
            logger.error("数据加载失败！！！！！！！！");
            return;
        }

        // 放进 Context
        event.getServletContext().setAttribute("powerMap", accountDto.getData());
        logger.info("权限表数据准备完毕！！！！！！！！！");
    }

}
