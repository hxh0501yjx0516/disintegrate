package com.tieshan.disintegrate.service.impl;

        import com.github.pagehelper.PageHelper;
        import com.tieshan.disintegrate.dao.CarSourceMapper;
        import com.tieshan.disintegrate.dao.SysUserMapper;
        import com.tieshan.disintegrate.pojo.*;
        import com.tieshan.disintegrate.service.DictionaryService;
        import com.tieshan.disintegrate.service.ICarSourceService;
        import com.tieshan.disintegrate.token.TokenService;
        import com.tieshan.disintegrate.util.IdWorker;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

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
     * 添加车辆
     * @param carInfo
     * @param id   车源主键id
     * @param request
     */
    @Override
    public void addCar(CarInfo carInfo,Long id, HttpServletRequest request) {
        // 设置车辆id
        IdWorker idWorker = new IdWorker(1, 1, 1);
        carInfo.setId(idWorker.nextId());
        // 设置车源主键id
        carInfo.setCarSource(id);

        // 获取token信息
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);

        // 设置车辆的其他信息
        carInfo.setDisintegratePlantId(sysUser.getCompany_id());
        carInfo.setCreateTime(new Date());
        carInfo.setOperatorId(sysUser.getId());
        carInfo.setOperator(sysUser.getOperator());
        carSourceMapper.addCar(carInfo);
    }

    /**
     * 获得所有的车辆处理方式和手续获取方式，
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
     * 查询指定车源的id
     * @param id
     * @return
     */
    @Override
    public CarSource selectCarSource(Long id) {
        return carSourceMapper.selectCarSource(id);
    }

    /**
     * 查询指定状态的车源
     * @return
     */
    @Override
    public List<Map<String, Object>> selectCarSourceList(String sourceType, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("id desc");
        // 查询指定状态的车源
        List<Map<String, Object>> carSourceList = carSourceMapper.selectCarSourceList(sourceType);
        return carSourceList;
    }

    /**
     * 增加车源
     *
     * @param carSource
     */
    @Override
    @Transactional
    public void add(CarSource carSource, HttpServletRequest request) {
        // 生成id
        IdWorker idWorker = new IdWorker(1, 1, 1);
        carSource.setId(idWorker.nextId());

        // 获得token
        String token = request.getHeader("token");
        String[] split = token.split("-");
        if (split[0].equals("PC")){
            carSource.setCreateSource("1");
        }else{
            carSource.setCreateSource("2");
        }

        // 获得token中的用户信息
        SysUser sysUser = tokenService.getToken(token);

        // 设置解体厂id
        carSource.setDisintegratePlantId(sysUser.getCompany_id());

        carSource.setCreateOperatorId(sysUser.getId());
        carSource.setCreateOperator(sysUser.getLogin_name());

        carSource.setCreateTime(new Date());
        carSourceMapper.insertCarSource(carSource);

        Long bankId = idWorker.nextId();
        carSource.setBankId(bankId);
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
