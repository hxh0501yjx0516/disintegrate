package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.dao.BannerPicDao;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.pojo.top_pic;
import com.tieshan.disintegrate.service.IBannerPicService;
import com.tieshan.disintegrate.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/10/17 10:48
 * @version: 1.0
 * @modified By:
 */
@Service
public class BannerPicService implements IBannerPicService {

    @Autowired
    BannerPicDao bannerPicDao;
    @Override
    public List<top_pic> findBannerPic(Integer page,Integer pageSize,SysUser user) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("create_time desc");
        return bannerPicDao.findBannerPic(user.getCompany_id());
    }

    @Override
    public int insertBannerPic(String topUrl, String h5Url,SysUser user) {

        IdWorker idWorker = new IdWorker(1, 1, 1);
        Long id = idWorker.nextId();
        Long companyId = user.getCompany_id();
        String operator = user.getOperator();
        return bannerPicDao.insertBannerPic(id,topUrl,h5Url,companyId,operator);
    }

    @Override
    public int updateBannerPic(Long id, String topUrl, String h5Url, SysUser user) {
        return bannerPicDao.updateBannerPic(id,topUrl,h5Url,user.getCompany_id());
    }

    @Override
    public int updateBannerStatus(Long id, char picType, SysUser user) {
        return bannerPicDao.updateBannerStatus(id,picType,user.getCompany_id());
    }

    @Override
    public int deleteBannerStatus(Long id, SysUser user) {
        return bannerPicDao.deleteBannerStatus(id,user.getCompany_id());
    }
}
