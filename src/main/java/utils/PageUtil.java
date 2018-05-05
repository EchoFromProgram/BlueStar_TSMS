package utils;

import com.github.pagehelper.PageHelper;
import constant.Page;

/**
 * 分页常用工具
 *
 * @author Fish
 */
public class PageUtil
{
    /**
     * pageHelper 每进行一次分页就得调用一次这个方法
     *
     * @param pn 到第几页
     * */
    public static void toPage(Integer pn)
    {
        PageHelper.startPage(pn, Page.ONE_PAGE_SIZE);
    }
}
