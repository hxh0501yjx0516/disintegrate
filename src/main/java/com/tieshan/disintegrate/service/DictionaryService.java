package com.tieshan.disintegrate.service;

import java.util.List;

/**
 * @author ningfeng
 * @description:
 * @date Created in 21:48 2019/9/18
 * @version: 1.0
 * @modified By:
 */
public interface DictionaryService {

    /**
     * 查询所有银行的名称
     * @return
     */
    List<String> findBankNameList();
}
