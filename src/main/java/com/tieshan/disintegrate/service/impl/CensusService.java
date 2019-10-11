package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.CensusMapper;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.ICensusService;
import com.tieshan.disintegrate.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/10/10 14:59
 * @version: 1.0
 * @modified By:
 */
@Service
public class CensusService implements ICensusService {
    @Autowired
    private CensusMapper censusMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public Map<String, Object> census(SysUser sysUser) {
        Map<String, Object> resultMap = new HashMap<>();
        if ("60001".equals(sysUser.getDepart_code())) {
            resultMap = censusMapper.commissionaireCensus(sysUser.getCompany_id() + "");
        }
        if ("50001".equals(sysUser.getDepart_code())) {
            resultMap = censusMapper.filesCensus(sysUser.getCompany_id() + "");
        }
        if ("40001".equals(sysUser.getDepart_code())) {
            resultMap = censusMapper.disassembleCensus(sysUser.getCompany_id() + "");
        }
        if ("30001".equals(sysUser.getDepart_code())) {
            resultMap = censusMapper.proceduresCensus(sysUser.getCompany_id() + "");
        }
        if ("20001".equals(sysUser.getDepart_code())) {
            resultMap = censusMapper.businessCensus(sysUser.getId() + "");
        }
        if ("10001".equals(sysUser.getDepart_code())) {
            resultMap = censusMapper.technologyCensus(sysUser.getCompany_id() + "");
        }
        return resultMap;
    }


}
