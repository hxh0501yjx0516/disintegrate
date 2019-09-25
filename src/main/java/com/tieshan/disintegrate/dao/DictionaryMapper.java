package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ningfeng
 * @description:
 * @date Created in 21:52 2019/9/18
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface DictionaryMapper {

    /**
     * 查询所有银行的名称
     * @return
     */
    List<String> findBankNameList();

    List<String> selectProvinceCodeList();

    List<String> selectProceduresTypeList();
}
