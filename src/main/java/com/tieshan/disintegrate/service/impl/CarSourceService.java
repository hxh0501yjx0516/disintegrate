package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.CarSourceMapper;
import com.tieshan.disintegrate.pojo.Bank;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.pojo.User;
import com.tieshan.disintegrate.service.DictionaryService;
import com.tieshan.disintegrate.service.ICarSourceService;
import com.tieshan.disintegrate.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:  车源业务层
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
    private UserService userService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     *  增加车源
     * @param carSource
     */
    @Override
    public void add(CarSource carSource) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        carSource.setId(idWorker.nextId());
        carSourceMapper.insertCarSource(carSource);
    }

    /**
     * 查询所有的用户
     * @return
     */
    @Override
    public List<SysUser> findUserNameList() {
        return userService.findUserList();
    }

    /**
     * 查询所有的银行
     * @return
     */
    @Override
    public List<String> findBankNameList() {
        return dictionaryService.findBankNameList();
    }
}
