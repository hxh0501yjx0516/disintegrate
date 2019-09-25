package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.dao.CarSourceMapper;
import com.tieshan.disintegrate.dao.SysUserMapper;
import com.tieshan.disintegrate.pojo.*;
import com.tieshan.disintegrate.service.DictionaryService;
import com.tieshan.disintegrate.service.ICarSourceService;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.IdWorker;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 车源业务层
 * @author: renlei
 * @date: 2019/9/6 11:41
 * @version: 1.0
 * @modified By:
 */
@Service
public class CarSourceService implements ICarSourceService {

    @Autowired
    private CarSourceMapper carSourceMapper;

    @Autowired
    private SysUserMapper sysUserMapper;
//
//    @Autowired
//    private UserService userService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private TokenService tokenService;

    /**
     * 添加车辆      时间转换
     *
     * @param carInfo
     * @param carSource      车源主键id
     * @param request
     */
    @Override
    public void addCar(CarInfo carInfo, Long carSource, HttpServletRequest request) {
        // 设置车辆id
        IdWorker idWorker = new IdWorker(1, 1, 1);
        long carInfoId = idWorker.nextId();
        carInfo.setId(carInfoId);
        // 设置车源主键id
        carInfo.setCarSource(carSource);

        // 获取token信息
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);

        // 设置车辆的其他信息
        carInfo.setDisintegratePlantId(sysUser.getCompany_id());
        carInfo.setCreateTime(new Date());
        carInfo.setOperatorId(sysUser.getId());

        carInfo.setOperator(sysUser.getLogin_name());
        // 设置车辆编号
//        carInfo.setCarCode();
        carSourceMapper.addCar(carInfo);

        // 添加车辆入场管理信息
        CarEnter carEnter = new CarEnter();
        carEnter.setId(idWorker.nextId());
        carEnter.setDisintegratePlantId(sysUser.getCompany_id());
        carEnter.setCarInfoId(carInfoId);
        carEnter.setIsApproach(1);
        carSourceMapper.insertCarEnter(carEnter);
    }

    /**
     * 获得所有的车辆处理方式和手续获取方式，
     *
     * @return
     */
    @Override
    public Map<String, List<String>> selectProcessingTypeAndProceduresType() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> processingTypes = carSourceMapper.selectProcessingTypeOrProceduresType("processing");
        List<String> proceduresTypes = carSourceMapper.selectProcessingTypeOrProceduresType("procedures");
        map.put("processingType", processingTypes);
        map.put("proceduresType", proceduresTypes);
        return map;
    }


    /**
     * 通过主键id查询车辆信息
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public CarInfo selectCarInfoById(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carSourceMapper.selectCarInfoById(id, sysUser.getCompany_id());
    }

    /**
     * 修改车辆信息
     *
     * @param carInfo
     */
    @Override
    public void editCar(CarInfo carInfo) {
        carInfo.setCreateTime(new Date());
        carSourceMapper.editCar(carInfo);
    }

    /**
     * 查询车源下的车辆
     *
     * @param id      车源的信息
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCarInfoList(Long id, HttpServletRequest request, Integer isVerify) {
        List<Map<String, Object>> carInfoList = null;
        String token = request.getHeader("token");
        // 判断是pc端还是App端
        String[] strArr = token.split("-");
        SysUser sysUser = tokenService.getToken(token);
//        if (strArr[0].equals("APP")){
            // 查询所有处于当前状态的车辆id
//            List<Long> carInfoIds = carSourceMapper.selectCarInfoIdList(sysUser.getCompany_id());
            carInfoList = carSourceMapper.selectCarInfoListByIds(id, sysUser.getCompany_id());
//            carInfoList = carSourceMapper.selectCarInfoList(id, sysUser.getCompany_id());
//        }else{
//            carInfoList = carSourceMapper.selectCarInfoList(id, sysUser.getCompany_id());
//        }
        return carInfoList;
    }

    /**
     * 查询指定车源的id
     *
     * @param id
     * @return
     */
    @Override
    public CarSource selectCarSource(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carSourceMapper.selectCarSource(id, sysUser.getCompany_id());
    }

    /**
     * 查询车源列表  PC  指定车源状态或搜索条件
     *              APP  指定某拆解厂和某业务员
     *
     * @param sourceType
     * @param findMsg
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCarSourceList(String sourceType, String findMsg, Integer page, Integer pageSize, HttpServletRequest request) {
        List<Map<String, Object>> carSourceList = null;
        // 获得token中的信息
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("id desc");
        // 查询指定状态的车源
        if (token.split("-")[0].equals("PC")) {
            carSourceList = carSourceMapper.selectCarSourceList(sysUser.getCompany_id(), sourceType, findMsg);
        }else{
            carSourceList = carSourceMapper.selectCarSourceListApp(sysUser.getCompany_id(), sysUser.getId(), sysUser.getLogin_name());
        }
        return carSourceList;
    }

    /**
     * 修改车源信息
     *
     * @param carSource
     */
    @Override
    public void editCarSource(CarSource carSource) {
        carSource.setCreateTime(new Date());
        carSourceMapper.editCarSource(carSource);
        Bank bank = carSource.getBank();
        bank.setId(carSource.getBankId());
        bank.setCreateTime(new Date());
        carSourceMapper.updateBank(bank);
    }

    /**
     * 通过id查询车源以及银行的信息
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public CarSource selectCarSourceById(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        CarSource carSource = carSourceMapper.selectCarSourceById(id, sysUser.getCompany_id());
        Bank bank = carSourceMapper.selectBankById(carSource.getBankId());
        carSource.setBank(bank);
        return carSource;
    }

    /**
     * 删除车辆
     *
     * @param id
     * @param request
     */
    @Override
    public void deleteCarInfoById(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        carSourceMapper.deleteCarInfoById(id, sysUser.getCompany_id());
    }

//    /**
//     * APP: 增加车源    前端不用返回业务员id，需要自己添加业务员id
//     * @param carSource
//     * @param request
//     */
//    @Override
//    public void addCarSourceApp(CarSource carSource, HttpServletRequest request) {
//        IdWorker idWorker = new IdWorker(1, 1, 1);
//        long carSourceId = idWorker.nextId();
//        carSource.setId(carSourceId);
//        String token = request.getHeader("token");
//        SysUser sysUser = tokenService.getToken(token);
//        // 业务员的id
//        carSource.setUserId();
//        // 解体厂的id
//        carSource.setDisintegratePlantId(sysUser.getCompany_id());
//
//        long bankId = idWorker.nextId();
//        carSource.setBankId(bankId);
//        carSource.set
//
//
//    }

//    /**
//     * APP端：查询某拆解厂的某业务员的车源列表管理
//     *
//     * @return
//     */
//    @Override
//    public List<Map<String, Object>> selectCarSourceListApp(HttpServletRequest request) {
//        String token = request.getHeader("token");
//        SysUser sysUser = tokenService.getToken(token);
//        return carSourceMapper.selectCarSourceListApp(sysUser.getCompany_id(), sysUser.getId(), sysUser.getLogin_name());
//    }

    /**
     * 删除指定的车源和旗下的车辆
     *
     * @param id
     * @param request
     */
    @Override
    public void deleteCarSource(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        // 先删除指定车源下的所有车辆
        carSourceMapper.deleteCarInfoById(id, sysUser.getCompany_id());
        // 删除指定的车源
        carSourceMapper.deleteCarSourceById(id, sysUser.getCompany_id());
    }

    /**
     * 增加车源
     *
     * @param carSource
     */
    @Override
    @Transactional
    public void addCarSource(CarSource carSource, HttpServletRequest request) {
        // 生成id
        IdWorker idWorker = new IdWorker(1, 1, 1);
        carSource.setId(idWorker.nextId());

        // 获得token
        String token = request.getHeader("token");
        String[] split = token.split("-");
        // 获得token中的用户信息
        SysUser sysUser = tokenService.getToken(token);
        if (split[0].equals("PC")) {
            carSource.setCreateSource("1");
        } else {
            carSource.setCreateSource("2");
            carSource.setUserId(sysUser.getId());
        }
        // 打款银行的id
        Long bankId = idWorker.nextId();
        carSource.setBankId(bankId);
        // 设置解体厂id
        carSource.setDisintegratePlantId(sysUser.getCompany_id());
        // 创建人id
        carSource.setCreateOperatorId(sysUser.getId());
        // 创建人姓名
        carSource.setCreateOperator(sysUser.getLogin_name());
        // 创建时间
        carSource.setCreateTime(new Date());
        // 添加车源
        carSourceMapper.insertCarSource(carSource);

        // 保存银行的信息
        Bank bank = carSource.getBank();
        // 设置银行的信息
        bank.setDisintegratePlantId(sysUser.getCompany_id());
        bank.setOperator(sysUser.getLogin_name());
        bank.setOperatorId(sysUser.getId());
        bank.setId(bankId);
        bank.setCreateTime(new Date());
        carSourceMapper.insertBank(bank);
    }

    /**
     * 查询所有的用户
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> findUserNameList() {
        return sysUserMapper.findUserList();
    }

    /**
     * 查询所有的银行
     *
     * @return
     */
    @Override
    public List<String> findBankNameList() {
        return dictionaryService.findBankNameList();
    }



}
