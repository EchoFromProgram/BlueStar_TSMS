package com.bluestar.advertisement.dao;

import com.bluestar.advertisement.entity.Advertise;
import com.bluestar.advertisement.entity.Enclosure;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Imp
 * email: 1318944013@qq.com
 * date: 2018/7/16 14:48
 */
public interface AdDao {

    /**
     * 插入一条广告
     * @param advertise 广告对象
     * @return 结果为1则插入成功
     */
    public Integer insertAd(Advertise advertise);

    /**
     * 如果广告携带附件，插入到附件表中
     * @param enclosure 附件对象
     * @return 结果为1则插入成功
     */
    public Integer insertEn(Enclosure enclosure);

    /**
     * 更新广告
     * @param advertise 广告对象
     * @return 结果为1则插入成功
     */
    public Integer updateAdById(Advertise advertise);

    /**
     * 根据状态和标题得到广告
     * @param status 状态
     * @param title 标题
     * @return
     */
    public List<Advertise> queryAdsByStatusAndTitle(@Param("status")String status,
                                                    @Param("title")String title);


}
