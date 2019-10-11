package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.dao.CarSourceMapper;
import com.tieshan.disintegrate.dao.SysUserMapper;
import com.tieshan.disintegrate.pojo.*;
import com.tieshan.disintegrate.service.DictionaryService;
import com.tieshan.disintegrate.service.ICarSourceService;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.PubMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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
     * @param
     * @param request
     */
    @Override
    @Transactional
    public void addCar(CarInfo carInfo, HttpServletRequest request) {
        // 设置车辆id
        IdWorker idWorker = new IdWorker(1, 1, 1);
        Long carInfoId = idWorker.nextId();
        carInfo.setId(carInfoId);
        // 设置车源主键id
        carInfo.setCarSource(carInfo.getCarSource());

        // 获取token信息
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);

        // 设置车辆的其他信息
        carInfo.setDisintegratePlantId(sysUser.getCompany_id());
        carInfo.setCreateTime(new Date());
        carInfo.setOperatorId(sysUser.getId());

        carInfo.setOperator(sysUser.getLogin_name());
        // 设置车辆编号
        // 获得车辆编号的前缀
        String codeFront = sysUser.getCompany_code();
        String codeIn = getMonth();
//        int codeAfter = 0;
        Map<String, Object> map = carSourceMapper.selectCarCode(sysUser.getCompany_id(), codeFront, codeIn);
//        System.out.println(map);

        Integer codeAfter = 0;
        if (map == null) {
            codeAfter = 1;
        } else {
            Integer codeAfterMax = Integer.parseInt(map.get("codeAfterMax").toString());
            codeAfter = codeAfterMax + 1;
        }
        String codeAfterString = "";
        int length = String.valueOf(codeAfter).length();
        if (length < 5) {
            for (int i = length; i < 5; i++) {
                codeAfterString += "0";
            }
        } else {
            codeAfterString = codeAfter.toString();
        }
        codeAfterString += codeAfter;

        carInfo.setCodeFront(codeFront);
        carInfo.setCodeIn(codeIn);
        carInfo.setCodeAfter(codeAfter);
        carInfo.setCarCode(codeFront + codeIn + codeAfterString);
        /*System.out.println(carInfo.getCodeFront());
        System.out.println(carInfo.getCodeIn());
        System.out.println(carInfo.getCodeAfter());
        System.out.println(carInfo.getCarCode());
        */
        carSourceMapper.addCar(carInfo);

        // 添加车辆入场管理信息
        CarEnter carEnter = new CarEnter();
        carEnter.setId(idWorker.nextId());
        carEnter.setDisintegratePlantId(sysUser.getCompany_id());
        carEnter.setCarInfoId(carInfoId);
        carEnter.setIsApproach(1);
        carSourceMapper.insertCarEnter(carEnter);
    }

    public String getMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        String returnDate = format.format(calendar.getTime());
        return returnDate;
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
    @Transactional
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
    public List<Map<String, Object>> selectCarInfoList(Long id, HttpServletRequest request) {
        List<Map<String, Object>> carInfoList = null;
        String token = request.getHeader("token");
        // 判断是pc端还是App端
        String[] strArr = token.split("-");
        SysUser sysUser = tokenService.getToken(token);
//        if (strArr[0].equals("APP")){
        // 查询所有处于当前状态的车辆id
//            carInfoList = carSourceMapper.selectCarInfoListByIds(id, sysUser.getCompany_id());
//        }else{
        carInfoList = carSourceMapper.selectCarInfoList(id, sysUser.getCompany_id());
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
     * APP  指定某拆解厂和某业务员
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
        System.out.println(sysUser);
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("s.create_time DESC");
        // 查询指定状态的车源
        if (token.split("-")[0].equals("PC")) {
            carSourceList = carSourceMapper.selectCarSourceList(sysUser.getCompany_id(), sourceType, findMsg);
        } else {
            carSourceList = carSourceMapper.selectCarSourceListApp(sysUser.getCompany_id(), sysUser.getId(), sysUser.getLogin_name(), findMsg);
            /*int carInfoCount = 0;
            for (Map<String, Object> map : carSourceList) {
                carInfoCount += Integer.parseInt(map.get("count").toString());
                map.put("carInfoCount", carInfoCount);
            }*/
        }
        return carSourceList;
    }

    /**
     * 修改车源信息
     *
     * @param params
     */
    @Override
    @Transactional
    public void editCarSource(Map<String, Object> params) {
        // 设置车源的信息
        CarSource carSource = new CarSource();
        // 设置车源的id
        carSource.setId(Long.parseLong(params.get("id").toString()));
        // 设置车源的联系人
        carSource.setContacts(params.get("contacts").toString());
        // 设置车源的联系人电话
        carSource.setPhone(params.get("phone").toString());
        // 设置车源的车辆台次
        carSource.setCount(Integer.parseInt(params.get("count").toString()));
        // 设置车源的位置
        carSource.setCarLocation(params.get("carLocation").toString());
        // 设置车源的业务员id
        carSource.setUserId(Long.parseLong(params.get("userId").toString()));
        carSource.setBankId(params.get("bankId").toString());
        // 设置更新时间
        carSource.setCreateTime(new Date());
        carSourceMapper.editCarSource(carSource);
        Bank bank = carSource.getBank();
        // 设置银行的id
        bank.setId(Long.parseLong(carSource.getBankId()));
        // 设置银行的名称
        bank.setBankName(params.get("bankName").toString());
        bank.setBankBranch(params.get("bankBranch").toString());
        bank.setAccount(params.get("account").toString());
        bank.setPayee(params.get("payee").toString());
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
        Bank bank = carSourceMapper.selectBankById(Long.parseLong(carSource.getBankId()));
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
     * @param params
     */
    @Override
    @Transactional
    public void addCarSource(Map<String, Object> params, HttpServletRequest request) {
        CarSource carSource = new CarSource();
        // 生成id
        IdWorker idWorker = new IdWorker(1, 1, 1);
        carSource.setId(idWorker.nextId());
        // 设置车源联系人
        carSource.setContacts(params.get("contacts").toString());
        // 设置车源联系人电话
        carSource.setPhone(params.get("phone").toString());
        // 设置车源的车辆台次
        carSource.setCount(Integer.parseInt(params.get("count").toString()));
        // 设置车源位置
        carSource.setCarLocation(params.get("carLocation").toString());
        // 设置车源的入场状态
//        carSource.setSourceType("1");

        // 获得token
        String token = request.getHeader("token");
        String[] split = token.split("-");
        // 获得token中的用户信息
        SysUser sysUser = tokenService.getToken(token);
        if (split[0].equals("PC")) {
            carSource.setCreateSource("1");
//             PC端会设置车源的业务员id
            carSource.setUserId(Long.parseLong(params.get("userId").toString()));
        } else {
            carSource.setCreateSource("2");
            carSource.setUserId(sysUser.getId());
        }
        // 打款银行的id
        Long bankId = idWorker.nextId();
        carSource.setBankId(bankId+"");
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
        // 设置银行的名称
        bank.setBankName(params.get("bankName").toString());
        // 设置银行的支行名称
        bank.setBankBranch(params.get("bankBranch").toString());
        // 设置银行的账户
        bank.setAccount(params.get("account").toString());
        // 设置银行的账户名称
        bank.setPayee(params.get("payee").toString());

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






    /**
     * 首页的查询
     * @param page
     * @param pageSize
     * @param state
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> selectHomePage(Integer page, Integer pageSize, String state, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("e.approach_time DESC");
        return carSourceMapper.selectHomePage(state, sysUser.getCompany_id());
    }

    /**
     * 保存该车辆的拆解方式
     * @param carInfoId
     * @param dismantleWay
     * @param request
     * @return
     */
    @Override
    public int updateDismantleWay(Long carInfoId, Integer dismantleWay, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carSourceMapper.updateDismantleWay(carInfoId, dismantleWay, sysUser.getCompany_id());
    }

    /**
     * 查询某拆解厂下的所有的车俩
     *
     * @param request
     * @param page
     * @param pageSize
     * @param findMsg
     * @param status
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCarInfoCompanyList(HttpServletRequest request, Integer page, Integer pageSize, String findMsg, String status) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("e.approach_time DESC");
        return carSourceMapper.selectCarInfoCompanyList(findMsg, status, sysUser.getCompany_id());
    }

    /**
     * 判断该车牌号的入场状态，若已入场，则提示"该车辆已入场，不能重复入场！"，若未入场，则提示"是否确定入场"
     * @param carNo
     * @param request
     * @return
     */
    @Override
    public int selectCarInfoCountByCarNo(String carNo, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        int count = carSourceMapper.selectCarInfoCountByCarNo(carNo, sysUser.getCompany_id());
        return count;
    }

    /**
     *  查询该车牌号是否存在
     * @param carNo
     * @param request
     * @return
     */
    @Override
    public int selectCarInfoNumByCarNo(String carNo, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        int count = carSourceMapper.selectCarInfoNumByCarNo(carNo, sysUser.getCompany_id());
        return count;
    }

    /**
     * 查询某拆解厂下某业务员下核档完成的所有车辆（搜索的是车牌号，车型，vin，车辆编号）
     *
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCarInfoListByIsVerify(Integer page, Integer pageSize, HttpServletRequest request, String findMsg) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("p.verify_time DESC");
        List<Map<String, Object>> mapList = carSourceMapper.selectCarInfoListByIsVerify(sysUser.getId(), sysUser.getLogin_name(), sysUser.getCompany_id(), findMsg);
        for (Map<String, Object> map : mapList) {
            map.put("status", "已核档");
        }
        return mapList;
    }

    /**
     * 更新车辆信息，添加车辆的存放位置
     *
     * @param carInfo
     */
    @Override
    public void editCarInfoLocation(CarInfo carInfo) {
        carSourceMapper.editCarInfoLocation(carInfo);
    }

    /**
     * 查询所有位置信息
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> selectLocationListByPid(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carSourceMapper.selectLocationListByPid(id, sysUser.getCompany_id());
    }

    /**
     * 查询某解体厂下的所有车辆
     *
     * @param page
     * @param pageSize
     * @param findMsg
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCarInfoListByDisintegratePlantId(Integer page, Integer pageSize, String findMsg, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        // 设置分页信息
        PageHelper.startPage(page, pageSize);
        return carSourceMapper.selectCarInfoListByDisintegratePlantId(sysUser.getCompany_id(), findMsg);
    }

    /**
     * 初检完成
     *
     * @param carSurvey
     * @param request
     */
    @Override
    @Transactional
    public void editCarSurveyComplete(CarSurvey carSurvey, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        carSurvey.setCreateTime(new Date());
        carSurvey.setCreateOperator(sysUser.getLogin_name());
        carSurvey.setCreateOperatorId(sysUser.getId());
        carSurvey.setDisintegratePlantId(sysUser.getCompany_id());
        // 更新车车辆初检信息
        carSourceMapper.editCarSurvey(carSurvey);
        // 修改初检的状态和预处理的状态
        CarEnter carEnter = new CarEnter();
        carEnter.setCarInfoId(carSurvey.getCarInfoId());
        carEnter.setDisintegratePlantId(carSurvey.getDisintegratePlantId());
        carEnter.setInitialSurveyTime(new Date());
        carEnter.setInitialSurveyUserId(sysUser.getId());
        carSourceMapper.updateCarEnterIsInitialSurvey(carEnter);
    }

    /**
     * 修改车辆初检的信息
     *
     * @param carSurvey
     */
    @Override
    @Transactional
    public void editCarSurvey(CarSurvey carSurvey, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        carSurvey.setCreateTime(new Date());
        carSurvey.setCreateOperator(sysUser.getLogin_name());
        carSurvey.setCreateOperatorId(sysUser.getId());
        carSurvey.setDisintegratePlantId(sysUser.getCompany_id());
        carSourceMapper.editCarSurvey(carSurvey);
    }

    /**
     * 通过车辆id查询该车辆的初检信息
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public CarSurvey selectCarSurveyByCarInfoId(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carSourceMapper.selectCarSurveyByCarInfoId(id, sysUser.getCompany_id());
    }

    /**
     * 通过id查询车辆信息和部分车辆入场信息
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> selectCarInfoByIdAndCarEnter(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carSourceMapper.selectCarInfoByIdAndCarEnter(id, sysUser.getCompany_id());
    }

    /**
     * 分页查询所有未初检的车辆信息(包括搜索条件)
     *
     * @param page
     * @param pageSize
     * @param findMsg
     * @param request
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCarInfoByIsInitialSurvey(Integer page, Integer pageSize, String findMsg, HttpServletRequest request) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("e.approach_time DESC");
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carSourceMapper.selectCarInfoByIsInitialSurvey(sysUser.getCompany_id(), findMsg);
    }

    /**
     * 查询车辆核档不通过的原因
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> selectCarInfoReason(Long id, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return carSourceMapper.selectCarInfoReason(id, sysUser.getCompany_id());
    }

    /**
     * 添加部分初检信息和车牌图片
     *
     * @param carNo
     * @param selfWeight
     * @param cardColor
     */
    @Override
    @Transactional
    public int insertCarSurveyPart(String carNo, String selfWeight, String cardColor, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        // 通过车牌号查询车辆id
        Map<String, Object> map = carSourceMapper.selectCarInfoByCarNo(carNo, sysUser.getCompany_id());
        if (PubMethod.isEmpty(map)) {
            return 2;
        }
        System.out.println(map);
        IdWorker idWorker = new IdWorker(1, 1, 1);
        CarSurvey carSurvey = new CarSurvey();
        carSurvey.setId(idWorker.nextId());
        carSurvey.setDisintegratePlantId(sysUser.getCompany_id());

        Long carInfoId = Long.parseLong(map.get("carInfoId").toString());

        carSurvey.setCarInfoId(carInfoId);
        carSurvey.setSelfWeight(selfWeight);
        carSurvey.setCreateOperatorId(sysUser.getId());
        carSurvey.setCreateOperator(sysUser.getLogin_name());
        carSurvey.setCardColor(cardColor);
        carSurvey.setCreateTime(new Date());
        carSourceMapper.insertCarSurveyPart(carSurvey);
        // 修改车辆入场的状态
        CarEnter carEnter = new CarEnter();
        carEnter.setId(Long.parseLong(map.get("id").toString()));
        carEnter.setIsApproach(2);
        carEnter.setApproachTime(new Date());
        carEnter.setApproachUserId(sysUser.getId());
        carEnter.setDisintegratePlantId(sysUser.getCompany_id());
        carEnter.setIsInitialSurvey(1);
        carSourceMapper.updateCarEnterIsApproach(carEnter);
        // 将该车辆添加到手续表中
        CarProcessing carProcessing = new CarProcessing();
        carProcessing.setId(idWorker.nextId());
        carProcessing.setDisintegratePlantId(sysUser.getCompany_id());
        carProcessing.setCarInfoId(carInfoId);
        carSourceMapper.insertCarProcessing(carProcessing);
        // 将该车辆信息添加到车辆身份表中
        CarIdentity carIdentity = new CarIdentity();
        carIdentity.setId(idWorker.nextId());
        carIdentity.setDisintegratePlantId(sysUser.getCompany_id());
        carIdentity.setCarInfoId(carInfoId);
        carSourceMapper.insertCarIdentity(carIdentity);
        return 1;
    }

    /**
     * APP 查询所有指定车源下的车辆
     *
     * @param id
     * @param request
     * @param state
     * @param findMsg
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCarInfoListApp(Long id, HttpServletRequest request, String state, String findMsg, Integer page, Integer pageSize) {
        // 定义一个查询结果的结果集对象
        List<Map<String, Object>> resultMapList = new ArrayList<>();

        // 设置分页信息
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("i.create_time DESC");
        // 获取token
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        List<Map<String, Object>> mapList = null;
        // 查询所有指定车源下的车辆
        mapList = carSourceMapper.selectCarInfoListApp(id, sysUser.getCompany_id(), state, findMsg);
        System.out.println(mapList);
        // 遍历maps集合
        for (Map<String, Object> map : mapList) {
            // 定义一个map集合对象
            Map<String, Object> mapCarInfo = new HashMap<>();
            System.out.println(map);
//            Set<String> keySet = map.keySet();
//            for (String key : keySet) {
//                String value = map.get(key).toString();
//                System.out.println(key+"::::::"+value);
////                mapCarInfo.put(key, value);
//            }
//            mapCarInfo.putAll(map);
//            Iterator<String> iterator = keySet.iterator();
//                if (iterator.hasNext()){
//                    String key = iterator.next();
//                    Object value = map.get(key);
//                }
            mapCarInfo.put("id", map.get("id").toString());
            mapCarInfo.put("carNo", map.get("carNo").toString());
            mapCarInfo.put("carCode", map.get("carCode").toString());
            mapCarInfo.put("vin", map.get("vin").toString());
            mapCarInfo.put("approachTime", map.get("approachTime").toString());
            // 判断车辆当前的状态
            if (map.get("isApproach").toString().equals("1")) {
                mapCarInfo.put("status", "待入场");
            } else if (map.get("isApproach").toString().equals("2") && map.get("isInitialSurvey").toString().equals("1")) {
                mapCarInfo.put("status", "待初检");
            } else if (map.get("isInitialSurvey").toString().equals("2") && map.get("isPretreatment").toString().equals("1")) {
                mapCarInfo.put("status", "待预处理");
            } else if (map.get("isPretreatment").toString().equals("2") && map.get("isCopyNumber").toString().equals("1")) {
                mapCarInfo.put("status", "待拓号");
            } else if (map.get("isCopyNumber").toString().equals("2") && map.get("isRegister").toString().equals("1")) {
                mapCarInfo.put("status", "待登记");
            } else if (map.get("isRegister").toString().equals("2") && map.get("isQuery").toString().equals("1")) {
                mapCarInfo.put("status", "待查询");
            } else if (map.get("isQuery").toString().equals("2") && map.get("isVerify").toString().equals("1")) {
                mapCarInfo.put("status", "待核档");
            } else if (map.get("isQuery").toString().equals("2") && map.get("isVerify").toString().equals("3")) {
                mapCarInfo.put("status", "核档未通过");
            } else if (map.get("isDestructive").toString().equals("2") && map.get("isDataUpload").toString().equals("1")) {
                mapCarInfo.put("status", "待上传商委");
            } else if (map.get("isDestructive").toString().equals("2") && map.get("isDismantle").toString().equals("1")) {
                mapCarInfo.put("status", "待拆解");
            }  else if (map.get("isLogout").toString().equals("2") && map.get("isAppointLogoutTime").toString().equals("1")) {
                mapCarInfo.put("status", "待商委注销");
            } else if (map.get("isAppointLogoutTime").toString().equals("2") && map.get("isGetSalvage").toString().equals("1")) {
                mapCarInfo.put("status", "待领取残值");
            } else if (map.get("isAppointLogoutTime").toString().equals("2")) {
                mapCarInfo.put("status", "报废完成");
            }
            // 一辆车的信息
            resultMapList.add(mapCarInfo);
            System.err.println(mapCarInfo);
        }
        System.out.println(resultMapList);
        return resultMapList;
    }
}
