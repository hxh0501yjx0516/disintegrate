package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.dao.CarDismantleDao;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IDismantleService;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.vo.CarBreakInfoVo;
import com.tieshan.disintegrate.vo.CarPartsData;
import com.tieshan.disintegrate.vo.PartsListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 拆解管理模块服务层
 * @author: Leavonson
 * @date: Created in 2019/9/29 17:59
 * @version: 1.0
 * @modified By:
 */
@Service
public class DismantleService implements IDismantleService {
    @Autowired
    private CarDismantleDao carDismantleDao;
    @Autowired
    private TokenService tokenService;

    @Override
    public List<CarBreakInfoVo> findDismantleList(String findMsg, Integer page, Integer pageSize, SysUser user) {
        List<CarBreakInfoVo> dismantleList = new ArrayList<>();
        CarBreakInfoVo breakInfoVo = null;
        //分页信息
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("info.id desc");
        //查询出基本信息封装为Map中，下一步遍历
        List<Map<String, Object>> carInfoList = carDismantleDao.findCarInfo(findMsg, user.getCompany_id());
        if (!PubMethod.isEmpty(carInfoList)) {
            for (Map<String, Object> carInfoMap : carInfoList) {
                breakInfoVo = new CarBreakInfoVo();
                //获取当前CarInfoId，为后续查询图片使用
                String str = String.valueOf(carInfoMap.get("id"));
                Long carInfoId = Long.valueOf(str);
                //封装一行数据
                //StringUtils.isEmpty((carInfoMap.get("car_code"))
                breakInfoVo.setCarCode(StringUtils.isEmpty(carInfoMap.get("car_code")) ? "" : (carInfoMap.get("car_code").toString()));
                breakInfoVo.setCarNo(StringUtils.isEmpty(carInfoMap.get("car_no")) ? "" : (carInfoMap.get("car_no").toString()));
                breakInfoVo.setCarName(StringUtils.isEmpty(carInfoMap.get("car_name")) ? "" : (carInfoMap.get("car_name").toString()));
                breakInfoVo.setContacts(StringUtils.isEmpty(carInfoMap.get("contacts")) ? "" : (carInfoMap.get("contacts").toString()));
                breakInfoVo.setContactsPhone(StringUtils.isEmpty(carInfoMap.get("contacts_phone")) ? "" : (carInfoMap.get("contacts_phone").toString()));
                breakInfoVo.setPrePic(carDismantleDao.findPrePic(carInfoId, user.getCompany_id()));
                breakInfoVo.setBreakPic(carDismantleDao.findBreakPic(carInfoId, user.getCompany_id()));
                //将封装好的一整行数据存入List
                dismantleList.add(breakInfoVo);
            }
        }

        //返回List
        return dismantleList;
    }


    @Override
    public List<Map<String, Object>> selectIsSuperviseSale(HttpServletRequest request, Integer page, Integer pageSize, String findMsg, Integer isSuperviseSale) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("p.dismantle_time DESC");
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carDismantleDao.selectIsSuperviseSale(sysUser.getCompany_id(), findMsg, isSuperviseSale);
    }


    @Override
    public List<Map<String, Object>> selectIsDismantle(HttpServletRequest request, Integer page, Integer pageSize, String findMsg, Integer isDismantle) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("p.destructive_time DESC");
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        List<Map<String, Object>> mapList = null;
        if (isDismantle == 1) {     // 待拆车：毁型时间
            mapList = carDismantleDao.selectIsDismantle(sysUser.getCompany_id(), findMsg, isDismantle);
        } else {         // 拆解时间
            mapList = carDismantleDao.selectIsDismantleComplete(sysUser.getCompany_id(), findMsg, isDismantle);
        }
        return mapList;
    }


    @Override
    public List<Map<String, Object>> selectCarParts(HttpServletRequest request, Integer page, Integer pageSize, String findMsg) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("p.print_time DESC");
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carDismantleDao.selectCarParts(sysUser.getCompany_id(), sysUser.getId(), findMsg);
    }


    @Override
    public void updateDismantle(Long operatorId, Long carInfoId, Long companyId) {
        carDismantleDao.updateDismantle(operatorId, carInfoId, companyId);
    }

    @Override
    public List<Map<String, Object>> findPartsNameList() {
        return carDismantleDao.findPartsNameList();
    }

    @Override
    public int addCarParts(CarPartsData carPartsData, SysUser user
                           //Long carInfoId, SysUser user, List<Map<String, Object>> partsNameAndOeList, Integer partsStatus
    ) {
        //从登录信息中获取数据
        Long companyId = user.getCompany_id();
        Long printOperatorId = user.getId();
        String printOperator = user.getUser_name();
        //从Post传参中获取数据
        Long carInfoId = carPartsData.getCarInfoId();
        String carCode = carPartsData.getCarCode();
        List<Map<String, Object>> partsNameAndCodeList = carPartsData.getData();
        int partsStatus = carPartsData.getPartsStatus();
        IdWorker idWorker = new IdWorker(1, 1, 1);
        int count = 0;
        for (Map<String, Object> map : partsNameAndCodeList) {
            Long id = idWorker.nextId();
            String partsCode = StringUtils.isEmpty(map.get("partsCode")) ? "" : (map.get("partsCode").toString());
            String partsName = StringUtils.isEmpty(map.get("partsName")) ? "" : (map.get("partsName").toString());
            if(!StringUtils.isEmpty(carDismantleDao.findPartByName(carInfoId,partsName))){
            }
            else{
                partsCode=carCode+partsCode;
                carDismantleDao.addCarParts(id, carInfoId, companyId, partsCode, partsName, partsStatus, printOperatorId, printOperator);
                count++;
            }
        }
        return count;
    }

    @Override
    public List<PartsListVo> findFirstPartsName() {
        return carDismantleDao.findFirstPartsName();
    }

    @Override
    public List<PartsListVo> findSecondPartsName(Long parentId) {
        return carDismantleDao.findSecondPartsName(parentId);
    }

}