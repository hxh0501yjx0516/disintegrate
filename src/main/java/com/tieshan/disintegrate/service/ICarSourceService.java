package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.Bank;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.pojo.SysUser;

import java.util.List;

/**
 * @description:
 * @author: renlei
 * @date: 2019/9/6 11:41
 * @version: 1.0
 * @modified By:
 */
public interface ICarSourceService {

    void add(CarSource carSource);

    List<SysUser> findUserNameList();

    List<String> findBankNameList();
}
