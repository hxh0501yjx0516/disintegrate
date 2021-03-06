package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.DictionaryMapper;
import com.tieshan.disintegrate.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ningfeng
 * @description:
 * @date Created in 21:49 2019/9/18
 * @version: 1.0
 * @modified By:
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    /**
     * 查询车牌颜色
     * @return
     */
    @Override
    public List<String> selectLicensePlateColorList() {
        return dictionaryMapper.selectLicensePlateColorList();
    }

    /**
     * 查询所有手续
     * @return
     */
    @Override
    public List<String> selectProceduresTypeList() {
        return dictionaryMapper.selectProceduresTypeList();
    }

    /**
     * 查询所有省份简称
     * @return
     */
    @Override
    public List<String> selectProvinceCodeList() {
        return dictionaryMapper.selectProvinceCodeList();
    }

}
