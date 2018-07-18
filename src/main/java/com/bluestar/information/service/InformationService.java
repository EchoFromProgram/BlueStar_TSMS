package com.bluestar.information.service;

import com.bluestar.information.entity.Information;

import java.util.List;

/**
 * @author: MackyHuang
 * @eamil: 973151766@qq.com
 * @createTime: 2018/7/18 11:36
 */
public interface InformationService {

    /**
     * 根据咨询状态返回一个列表
     * 1正常，2上架，3无效
     * @param informationStatu
     * @return
     */
    List<Information> listInformationByStatu(String informationStatu, String informationTitle);

    /**
     * 模糊查询标题 返回一个列表
     * @param informationTitle
     * @return
     */
    List<Information> listInformationByTitle(String informationTitle);

    /**
     * 根据id返回一个information对象
     * @param informationId
     * @return
     */
    Information getInformationById(String informationId);

    /**
     * 插入一个新的咨询
     * 需要参数为information全部参数
     * @param information
     * @return
     */
    Integer saveInformation(Information information);

    /**
     * 修改咨询，这是一个大修改
     * @param information
     * @return
     */
    Integer updateInformation(Information information);

    /**
     * 删除一个咨询根据他的id
     * @param informationId
     * @return
     */
    Integer removeInformatin(String informationId);

    /**
     * 修改状态
     * 1正常，2上架，3无效
     * @param informationId
     * @param statu
     * @return
     */
    Integer updateStatuByid(String informationId, String statu);
}
