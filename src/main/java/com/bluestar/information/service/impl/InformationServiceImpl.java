package com.bluestar.information.service.impl;

import com.bluestar.information.dao.InformationDao;
import com.bluestar.information.entity.Information;
import com.bluestar.information.service.InformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/7/18 11:37
 */
@Service
public class InformationServiceImpl implements InformationService {

    @Resource
    private InformationDao informationDao;

    @Override
    public List<Information> listInformationByStatu(String informationStatu, String informationTitle) {
        List<Information> informationList = null;
        informationList = informationDao.listInformationByStatu(informationStatu, informationTitle);
        if(informationList != null && informationList.size() > 0){
            return informationList;
        }
        return null;
    }

    @Override
    public List<Information> listInformationByTitle(String informationTitle) {
        return null;
    }

    @Override
    public Information getInformationById(String informationId) {
        return null;
    }

    @Override
    public Integer saveInformation(Information information) {
        return null;
    }

    @Override
    public Integer updateInformation(Information information) {
        return null;
    }

    @Override
    public Integer removeInformatin(String informationId) {
        return null;
    }

    @Override
    public Integer updateStatuByid(String informationId, String statu) {
        return null;
    }
}
