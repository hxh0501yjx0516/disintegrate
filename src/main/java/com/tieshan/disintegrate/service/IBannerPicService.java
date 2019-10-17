package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.pojo.top_pic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/10/17 10:48
 * @version: 1.0
 * @modified By:
 */
public interface IBannerPicService {

    /**首页Banner图查询*/
    List<top_pic> findBannerPic(Integer page,Integer pageSize,SysUser user);

    /**添加Banner图查询*/
    int insertBannerPic(String topUrl,String h5Url,SysUser user);

    /**修改Banner图*/
    int updateBannerPic(Long id,String topUrl,String h5Url,SysUser user);

    /**修改Banner图状态，上架还是下架*/
    int updateBannerStatus(Long id,char picType,SysUser user);

    /**删除Banner图*/
    int deleteBannerStatus(Long id,SysUser user);
}
