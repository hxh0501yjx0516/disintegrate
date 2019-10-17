package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.top_pic;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/10/17 10:47
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface BannerPicDao {

    /**首页Banner图查询*/
    @Select("select * from ts_top_pic where company_id=#{companyId}")
    List<top_pic> findBannerPic(@Param("companyId") Long companyId);

    /**添加Banner图*/
    @Insert("insert into ts_top_pic values(#{id},#{topUrl},#{h5Url},1,#{companyId},now(),#{operator})")
    int insertBannerPic(@Param("id") Long id,
                        @Param("topUrl") String topUrl,
                        @Param("h5Url") String h5Url,
                        @Param("companyId") Long companyId,
                        @Param("operator") String operator);

    /**修改Banner图*/
    @Update("update ts_top_pic set top_url=#{topUrl},h5_url=#{h5Url} where id=#{id} and company_id=#{companyId}")
    int updateBannerPic(@Param("id") Long id,
                        @Param("topUrl") String topUrl,
                        @Param("h5Url") String h5Url,
                        @Param("companyId") Long companyId);

    /**修改Banner图状态，上架还是下架*/
    @Update("update ts_top_pic set pic_type=#{picType} where id=#{id} and company_id=#{companyId}")
    int updateBannerStatus(@Param("id") Long id,
                           @Param("pic_type") char picType,
                           @Param("companyId") Long companyId);

    /**删除Banner图*/
    @Delete("delete from ts_top_pic where id=#{id} and company_id=#{companyId}")
    int deleteBannerStatus(@Param("id") Long id,
                           @Param("companyId") Long companyId);

}
