package com.bluestar.advertisement.controller;

import com.bluestar.advertisement.constant.AdConst;
import com.bluestar.advertisement.dto.ServerResponse;
import com.bluestar.advertisement.entity.Advertise;
import com.bluestar.advertisement.enums.response.AdResponse;
import com.bluestar.advertisement.service.AdService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/7/18 10:13
 */
@Controller
public class AdController {

    private final AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    @RequestMapping(value = "listAd.do")
    public String list() {

        return "advertisement/advertise_list.jsp";
    }


    @RequestMapping(value = "saveAd.do")
    @ResponseBody
    public ServerResponse upFile(@RequestParam("file")CommonsMultipartFile file, Advertise advertise,
                         HttpSession session) {

        // 判断是否上传图片
        if( file == null || file.getSize() == 0 ){
            return ServerResponse.getServerResponse(AdResponse.PICTURE_IS_NULL);

        }

        // 得到文件上传路径 /File/advertise/作者
        String dirPath =
                session.getServletContext().getRealPath(AdConst.DIR_PATH) + File.separator + "advertise"
                        + File.separator + advertise.getAdCreateUser() ;

        // 得到上传广告结果
        ServerResponse serverResponse = adService.saveAd(file, dirPath ,advertise);


        return  serverResponse;
    }



    @RequestMapping(value = "deleteAd.do")
    @ResponseBody
    public ServerResponse deleteAd(String adId) {
        return adService.deleteAdById(adId);
    }



    @RequestMapping(value = "updateAd.do")
    @ResponseBody
    public ServerResponse updateAd(@RequestParam(value = "file",required = false)CommonsMultipartFile file, Advertise advertise,
                                   String pictureId, HttpSession session) {


        String dirPath =
                session.getServletContext().getRealPath(AdConst.DIR_PATH) + File.separator + "advertise"
                        + File.separator + advertise.getAdCreateUser() ;

        // 得到更新结果
        ServerResponse serverResponse = adService.updateAd(file, dirPath ,advertise, pictureId);

        return  serverResponse;
    }


}
