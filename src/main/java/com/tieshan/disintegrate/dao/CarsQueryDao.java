package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.vo.CaiPrePicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/17 16:31
 * @version: 1.0
 * @modified By:
 */
public interface CarsQueryDao {
    /**
     * 基于条件分页查询车辆信息
     * @param findMsg    查询条件(姓名/电话/车型/车牌号/车辆编号/车架号/发动机号)
     */
    List<CarsQuery> findPageObjects(@Param("findMsg") String findMsg,@Param("companyId")Long companyId);
    /***
     * App端查询车辆预处理车辆信息
     */
    List<Map<String, Object>>findPretreatmentCars(@Param("findMsg") String findMsg,@Param("dpId")Long dpId);
    /** App端根据carInfoId查询车辆预处理信息*/
    List<CaiPrePicVo> doFindCars(Long carInfoId, Long companyId);
    /***
     * App端查询车辆预处理拍照名称
     */
    List<Map<String, Object>>findPrePicNameCars();
    /***
     * App端查询更改入场预处理状态
     */
    void updatePretreatment(@Param("operatorId")Long operatorId,@Param("carInfoId")Long carInfoId,@Param("disId")Long disId);
    /***
     * App端查询预拓号车辆信息
     */
    List<Map<String, Object>>findCopyNumberCars(@Param("findMsg") String findMsg,@Param("dpId")Long dpId);
    /**
     * App端根据carInfoId查询车辆拓号数据
     */
    List<Map<String, Object>> findCpTuoPic(Long carInfoId, Long companyId);
    /***
     * App端查询车辆预拓号拍照名称
     */
    List<Map<String, Object>>findCpPicNameCars();
    /***
     * App端查询更改入场拓号状态
     */
    void updateTuoStatus(@Param("operatorId")Long operatorId,@Param("carInfoId")Long carInfoId,@Param("disId")Long disId);
    /***
     * App端查询预拆解车辆信息
     */
    List<Map<String, Object>> findDismantleCars(String findMsg, Long dpId);
    /***
     * App端根据carInfoId查询车辆初检信息
     */
    List<Map<String,Object>> findSurveyById(@Param("carInfoId")Long carInfoId, @Param("companyId")Long companyId);
    /***
     * App端根据carInfoId查询车辆初检数据
     */
    List<CaiPrePicVo> findPrePicById(@Param("carInfoId")Long carInfoId,@Param("companyId")Long companyId);
    /***
     * App端车辆初检拆解方式1
     */
    int updateSurveyWay(@Param("carInfoId")Long carInfoId,@Param("status")Integer status,@Param("companyId")Long companyId);
    /***
     * App端车辆初检拆解方式2
     */
    int updateEnterWay(@Param("carInfoId")Long carInfoId,@Param("status")Integer status,@Param("companyId")Long companyId);


    /***
     * App端-车辆待毁型
     */
    List<Map<String, Object>> findPreBreakCars(@Param("findMsg") String findMsg,@Param("companyId")Long companyId);
    /***
     * App端-车辆已毁型
     */
    List<Map<String, Object>> findBreakSuccessCars(@Param("findMsg") String findMsg,@Param("companyId")Long companyId);

    /***
     * App端根据carInfoId查询车辆待毁型数据
     */
    List<Map<String, Object>> findPreBreakCarsById(@Param("carInfoId")Long carInfoId,@Param("companyId")Long companyId);

    /**
     * App端添加毁型车辆
     */
    void updateBreakStatus(@Param("operatorId")Long operatorId,@Param("carInfoId")Long carInfoId,@Param("disId")Long disId);
    /***
     * App端-待上传报废证明车辆
     */
    List<Map<String, Object>> findProCars(@Param("findMsg") String findMsg,@Param("companyId")Long companyId);
    /***
     * App端-已上传报废证明车辆
     */
    List<Map<String, Object>> findProCarsComplete(@Param("findMsg") String findMsg,@Param("companyId")Long companyId);
    /**
     * App端修改上传报废证明车辆状态
     */
    void updateProStatus(@Param("carInfoId")Long carInfoId,@Param("disId")Long disId);

    /***
     * App端根据carInfoId查询车辆报废证明数据
     */
    List<Map<String, Object>> findProCarsById(@Param("carInfoId")Long carInfoId,@Param("companyId")Long companyId);
    /***
     * App端-本部已处理
     */
    List<Map<String, Object>> findIsHandle(@Param("findMsg") String findMsg,@Param("companyId")Long companyId);
}
