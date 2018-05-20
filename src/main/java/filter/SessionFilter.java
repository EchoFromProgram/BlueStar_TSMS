package filter;

import entity.Power;
import utils.ContextUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 用户会话相关过滤器
 *
 * @author Fish
 * created by 2018-05-20 10:50
 */
public class SessionFilter implements Filter
{
    private String loginUri = null; // 登陆界面 uri

    private String loginCheckUri = null;

    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 如果已经是登陆界面，就不用过滤了
        String currentUri = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/") + 1);
        if ("".equals(currentUri) || currentUri.equals(loginUri) || currentUri.equals(loginCheckUri))
        {
            chain.doFilter(request, response);
        }
        else
        {
            HttpSession session = request.getSession(); // 取得会话
            if (session.getAttribute("user") == null)
            {
                response.sendRedirect(request.getContextPath() + "/" + loginUri);
                return; // 阻止继续向下走了，没必要再过滤
            }

            // 取得这个用户能访问的 uri TODO 对 ajax 的请求怎么过滤？
            /*List<Integer> hisPowers = (List<Integer>) session.getAttribute("hisPowers");
            Map<Integer, Power> powersMap = (Map<Integer, Power>) ContextUtil.get(ContextUtil.POWER_MAP);

            boolean isChain = false;
            for (int i = 0; i < hisPowers.size(); i++)
            {
                if (currentUri.equals(powersMap.get(hisPowers.get(i)).getPower()))
                {
                    isChain = true;
                    break;
                }
            }*/

            //if (isChain)
            if (true)
            {
                chain.doFilter(request, response);
            }
            else
            {
                response.sendRedirect(request.getContextPath() + "/" + loginUri);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException
    {
        // 获得登陆界面 uri
        loginUri = config.getInitParameter("loginUri");
        if (loginUri == null || "".equals(loginUri))
        {
            loginUri = "login.do";
        }

        // 获得登陆验证 uri
        loginCheckUri = config.getInitParameter("loginCheckUri");
        if (loginCheckUri == null || "".equals(loginCheckUri))
        {
            loginCheckUri = "loginCheck.do";
        }
    }
}
