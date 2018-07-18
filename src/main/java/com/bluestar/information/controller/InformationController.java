package com.bluestar.information.controller;

import com.bluestar.information.entity.Information;
import com.bluestar.information.service.InformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/7/18 12:06
 */
@Controller
public class InformationController {

    @Resource
    private InformationService informationService;

    /**
     * 根据statu查询列表  null，代表查询所有
     * @param model
     * @param
     * @return
     * 1正常，2上架，3无效
     */
    @RequestMapping(value = "information_list.do", method = RequestMethod.GET)
    public String InformationList(Model model, String statu, String title){
        List<Information> informationList = null;
        if("-1".equals(statu)){
            informationList = informationService.listInformationByStatu(null, title);
        }else {
            informationList = informationService.listInformationByStatu(statu, title);
        }
        model.addAttribute("statu", statu);
        model.addAttribute("title", title);
        model.addAttribute("informationList", informationList);
        return "information/information_list.jsp";
    }
}
