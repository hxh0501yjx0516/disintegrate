package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.dao.CarPicMapper;
import com.tieshan.disintegrate.dao.CarsQueryDao;
import com.tieshan.disintegrate.pojo.CarPic;
import com.tieshan.disintegrate.vo.CaiPrePicVo;
import com.tieshan.disintegrate.vo.CarPicData;
import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.CarsQueryService;
import com.tieshan.disintegrate.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/17 20:50
 * @version: 1.0
 * @modified By:
 */
@Service
@Transactional
public class CarsQueryServiceImpl implements CarsQueryService {

    @Autowired
    private CarsQueryDao carsQueryDao;
    @Autowired
    private CarPicMapper carPicMapper;

    @Override
    public List<CarsQuery> findPageObjects(String findMsg, Integer page, Integer pageSize,Long companyId) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("reg_time desc");
        return carsQueryDao.findPageObjects(findMsg,companyId);
    }

    @Override
    public List<Map<String, Object>> findPretreatmentCars(String findMsg, Integer page, Integer pageSize,Long dpId) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("approach_time desc");
        return carsQueryDao.findPretreatmentCars(findMsg, dpId);
    }

    @Override
    public List<CaiPrePicVo> doFindCars(Long carInfoId, Long companyId) {
        return carsQueryDao.doFindCars(carInfoId, companyId);
    }

    @Override
    public List<Map<String, Object>> findPrePicNameCars() {
        return carsQueryDao.findPrePicNameCars();
    }

    @Override
    public synchronized void addPrePic(CarPicData carPicData, SysUser user) {

        //不管点击哪个按钮，先清空数据库
        //测试数据：carPicMapper.batchDeleteCarPic(3333L,carPicData.getCarInfoId());
        carPicMapper.batchDeleteCarPic(user.getCompany_id(), carPicData.getCarInfoId());
        //入库
        pubMethod(carPicData, user);
        //判断是暂存还是完成
        if (carPicData.getStatus() == 2) {
            //修改入场状态值
            //测试数据：carsQueryDao.updatePretreatment(8888L,carPicData.getCarInfoId(),3333L);
            carsQueryDao.updatePretreatment(user.getId(), carPicData.getCarInfoId(), user.getCompany_id());
        }
    }
    @Override
    public List<Map<String, Object>> findCopyNumberCars(String findMsg, Integer page, Integer pageSize,Long dpId) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("approach_time desc");
        return carsQueryDao.findCopyNumberCars(findMsg, dpId);
    }
    @Override
    public List<Map<String, Object>> findCpTuoPic(Long carInfoId, Long companyId) {
        return carsQueryDao.findCpTuoPic(carInfoId, companyId);
    }
    @Override
    public List<Map<String, Object>> findCpPicNameCars() {
        return carsQueryDao.findCpPicNameCars();
    }
    @Override
    public synchronized void addTuoPic(CarPicData carPicData, SysUser user) {

        //不管点击哪个按钮，先清空数据库
        carPicMapper.batchDeleteTuoPic(user.getCompany_id(), carPicData.getCarInfoId());
        //测试数据：carPicMapper.batchDeleteTuoPic(3333L, carPicData.getCarInfoId());
        pubMethod(carPicData, user);
        //判断是暂存还是完成
        if (carPicData.getStatus() == 2) {
            //修改入场状态值
            //测试数据:carsQueryDao.updateTuoStatus(8888L, carPicData.getCarInfoId(), 3333L);
            carsQueryDao.updateTuoStatus(user.getId(), carPicData.getCarInfoId(), user.getCompany_id());
        }
    }
    public void pubMethod(CarPicData carPicData, SysUser user) {
        //将pic的list存入Map，方便Mybatis批量存储
        Map<String, Object> mapList = new HashMap<>();
        //将循环遍历封装完的pic存入List
        List<CarPic> listPic = new ArrayList<>();
        CarPic carPic = null;
        IdWorker idWorker = new IdWorker(1, 1, 1);
        //从前端传入的数据解析值
        Long carInfoId = carPicData.getCarInfoId();
        int status = carPicData.getStatus();
        List<Map<String, Object>> carPicDataList = carPicData.getData();
        //解析List数据
        for (int i = 0; i < carPicDataList.size(); i++) {
            //解析Map
            Map<String, Object> map = carPicDataList.get(i);
            String fileName = map.get("filed_name").toString();
            String fileUrl = map.get("fileUrl").toString();
            String firstType = map.get("first_type").toString();
            String twoType = map.get("two_type").toString();
            //封装值
            Long idxx = idWorker.nextId();
            carPic = new CarPic();
            //自动生成
            carPic.setId(idxx);
            //从user对象中获取
            carPic.setDisintegratePlantId(user.getCompany_id());
            carPic.setOperator(user.getUser_name());
            carPic.setOperatorId(user.getId());
            //自定义
            carPic.setCreateTime(new Date());
            carPic.setIsDelete(2);
            //从参数中获取
            carPic.setCarInfoId(carInfoId);
            carPic.setFileName(fileName);
            carPic.setFileUrl(fileUrl);
            carPic.setFirstType(firstType);
            carPic.setTwoType(twoType);
            //存入List
            listPic.add(carPic);
        }
        //将List存入Map
        mapList.put("list", listPic);
        //再执行插入操作
        carPicMapper.insertBatchCarPic(mapList);
    }
    @Override
    public List<Map<String, Object>> findDismantleCars(String findMsg,Integer page, Integer pageSize, Long dpId) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("approach_time desc");
        return carsQueryDao.findDismantleCars(findMsg, dpId);
    }
    @Override
    public List<Map<String,Object>> findSurveyById(Long carInfoId, Long companyId) {
        return carsQueryDao.findSurveyById(carInfoId, companyId);
    }
    @Override
    public List<CaiPrePicVo> findPrePicById(Long carInfoId, Long companyId) {
        return carsQueryDao.findPrePicById(carInfoId, companyId);
    }
    @Override
    public int dismantleWay(Long carInfoId, Integer status, Long companyId) {

        carsQueryDao.updateSurveyWay(carInfoId, status, companyId);
        carsQueryDao.updateEnterWay(carInfoId, status, companyId);
        return 1;
    }

    @Override
    public List<Map<String, Object>> findPreBreakCars(String findMsg, Integer page, Integer pageSize, Long companyId) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("approach_time desc");
        return carsQueryDao.findPreBreakCars(findMsg,companyId);
    }

    @Override
    public List<Map<String, Object>> findPreBreakCarsById(Long carInfoId, Long companyId) {
        return carsQueryDao.findPreBreakCarsById(carInfoId,companyId);
    }

    @Override
    public void addBreakPic(CarPicData carPicData, SysUser user) {

        //不管点击哪个按钮，先清空数据库
        carPicMapper.batchDeleteBreakPic(user.getCompany_id(), carPicData.getCarInfoId());
        pubMethod(carPicData, user);
        //判断是暂存还是完成
        if (carPicData.getStatus() == 2) {
            //修改入场状态值
            carsQueryDao.updateBreakStatus(user.getId(), carPicData.getCarInfoId(), user.getCompany_id());
        }
    }

    @Override
    public List<Map<String, Object>> findBreakSuccessCars(String findMsg, Integer page, Integer pageSize, Long companyId) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("approach_time desc");
        return carsQueryDao.findBreakSuccessCars(findMsg,companyId);
    }
}
