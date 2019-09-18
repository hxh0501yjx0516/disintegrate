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

    @Override
    public List<String> findBankNameList() {
        return dictionaryMapper.findBankNameList();
    }
}
