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

    List<String> selectProvinceCodeList();

    List<String> selectProceduresTypeList();

    List<String> selectLicensePlateColorList();
}
