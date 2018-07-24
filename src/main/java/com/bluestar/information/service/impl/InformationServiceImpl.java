package com.bluestar.information.service.impl;

import com.bluestar.advertisement.constant.AdConst;
import com.bluestar.advertisement.dao.AdDao;
import com.bluestar.advertisement.entity.Enclosure;
import com.bluestar.advertisement.enums.response.AdResponse;
import com.bluestar.advertisement.utils.AdUtils;
import com.bluestar.common.utils.CodeUtil;
import com.bluestar.common.utils.PageUtil;
import com.bluestar.information.common.status.enums.DepartmentEnum;
import com.bluestar.information.dao.InformationDao;
import com.bluestar.information.dto.ServerResponse;
import com.bluestar.information.entity.Information;
import com.bluestar.information.service.InformationService;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/7/18 11:37
 */
@Service
public class InformationServiceImpl implements InformationService {

    public static final Logger log = Logger.getLogger(InformationServiceImpl.class);

    @Resource
    private InformationDao informationDao;

    @Autowired
    private AdDao adDao;

    /**
     * 根据咨询状态返回一个列表
     * 1正常，2上架，3无效
     * @param informationStatu
     * @param informationTitle
     * @return
     */
    @Override
    public PageInfo<Information> listInformationByStatu(Integer page, String informationStatu, String informationTitle) {
        List<Information> informationList = null;
        PageUtil.toPage(page);
        informationList = informationDao.listInformationByStatu(informationStatu, informationTitle);
        if(informationList != null && informationList.size() > 0){
            return PageUtil.pageInfo(informationList);
        }
        return null;
    }

    @Override
    public List<Information> listInformationByTitle(String informationTitle) {
        return null;
    }

    @Override
    public ServerResponse<Information> getInformationById(String informationId) {
        if(informationId == null){
            return ServerResponse.response(DepartmentEnum.PARAMETER_UNCOMPLETED);
        }
        Information information = informationDao.getInformationById(informationId);
        if(information != null){
            return ServerResponse.response(DepartmentEnum.SUCCESS, information);
        }else {
            return ServerResponse.response(DepartmentEnum.GET_FAILED);
        }
    }

    /**
     * 插入新资讯
     * @param information
     * @return
     */
    @Override
    public ServerResponse saveInformation(Information information) {
        if (information == null || information.getInformationCreateUser() == null ||
                information.getInformationContent() == null || information.getInformationTitle() == null ||
                information.getInformationAuthor() == null || information.getInformationStatu() == null){
            return ServerResponse.response(DepartmentEnum.PARAMETER_UNCOMPLETED);
        }
        String informationId = CodeUtil.getId();
        information.setInformationId(informationId);
        information.setInformationPublishTime(new Date());
        information.setInformationCreateTime(new Date());
        information.setInformationOrder(1);
        information.setInformationEnclosure(null);
        int result = informationDao.saveInformation(information);
        if(result > 0){
            return ServerResponse.response(DepartmentEnum.SUCCESS);
        }else {
            return ServerResponse.response(DepartmentEnum.SAVE_FAILED);
        }
    }

    @Override
    public ServerResponse updateInformation(Information information) {
        if (information == null || information.getInformationCreateUser() == null ||
                information.getInformationContent() == null || information.getInformationTitle() == null ||
                information.getInformationAuthor() == null || information.getInformationStatu() == null ||
                information.getInformationId() == null){
            return ServerResponse.response(DepartmentEnum.PARAMETER_UNCOMPLETED);
        }
        information.setInformationPublishTime(new Date());
        information.setInformationOrder(1);
        int result = informationDao.updateInformation(information);
        if(result > 0){
            return ServerResponse.response(DepartmentEnum.SUCCESS);
        }else {
            return ServerResponse.response(DepartmentEnum.UPDATE_FAILED);
        }

    }

    @Override
    public ServerResponse removeInformatin(String informationId) {
        if(informationId == null){
            return ServerResponse.response(DepartmentEnum.PARAMETER_UNCOMPLETED);
        }
        int result = informationDao.removeInformatin(informationId);
        if(result > 0){
            return ServerResponse.response(DepartmentEnum.SUCCESS);
        }else {
            return ServerResponse.response(DepartmentEnum.REMOVE_FAILED);
        }
    }

    @Override
    public Integer updateStatuByid(String informationId, String statu) {
        return null;
    }

    @Transactional
    public ServerResponse savaEnclosure(CommonsMultipartFile file, String dirPath) {

        int affect = 0;

        String path = null;

        // 得到上传的图片名
        String pictureName = file.getOriginalFilename();

        // 得到图片上传完整路径
        path = dirPath + File.separator + pictureName;

        String uri = AdUtils.getUri(path);

        List<Information> informationList = null;
        informationList = informationDao.listInformationByStatu(null, null);
        Information information = informationList.get(0);
        String enclosureId = CodeUtil.getId();
        information.setInformationEnclosure(enclosureId);
        int result = informationDao.updateInformation(information);
        if(result < 0){
            return ServerResponse.response(DepartmentEnum.SAVE_FAILED);
        }

        // 上传图片
        if(upFile(file, path , pictureName) != null) {
            return upFile(file, path, pictureName);
        }

        // 图片上传成功，将广告和图片信息插入数据库
        try {

            Enclosure enclosure =
                    new Enclosure(enclosureId, "2", information.getInformationId(), pictureName,
                            uri, "0", file.getSize(), StringUtils.getFilenameExtension(pictureName), uri);

            // 把图片插入数据库
            affect = adDao.insertEn(enclosure);
            if(affect <=0){
                File f = new File(path);
                f.delete();
                return ServerResponse.response(DepartmentEnum.SAVE_FAILED);
            }
        }catch (Exception e){
            File f = new File(path);
            f.delete();
            log.error("未知错误发生",e);
            return ServerResponse.response(DepartmentEnum.SAVE_FAILED);
        }

        // 默认返回成功
        return ServerResponse.response(DepartmentEnum.SUCCESS);
    }

    /**
     * 封装上传图片方法
     * @param file 图片
     * @param path 路径
     * @param pictureName 图片名
     * @return 上传结果 如果是null表示上传成功
     */
    private ServerResponse upFile
    (CommonsMultipartFile file, String path, String pictureName) {

        //如果该文件存在，提示重复
        if(AdUtils.fileIsExits(path)){
            return ServerResponse.response(DepartmentEnum.PICTURE_IS_EXITS);
        }

        // 如果图片上传失败，返回失败结果
        if(!AdUtils.upFileUtils(file, new File(path))){
            return ServerResponse.response(DepartmentEnum.UP_PICTURE_FAILURE);
        }

        return null;
    }
}
