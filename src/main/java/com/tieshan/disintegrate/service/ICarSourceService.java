package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.Bank;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.pojo.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: renlei
 * @date: 2019/9/6 11:41
 * @version: 1.0
 * @modified By:
 */
public interface ICarSourceService {

    void add(CarSource carSource);

    List<Map<String, Object>>  findUserNameList();

    List<String> findBankNameList();
}
