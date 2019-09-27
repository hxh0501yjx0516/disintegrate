package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.NoticeMapper;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.INoticeService;
import com.tieshan.disintegrate.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/27 11:11
 * @version: 1.0
 * @modified By:
 */
@Service
public class NoticeService implements INoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public List<Map<String, Object>> getTop(HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return noticeMapper.getTop(sysUser.getCompany_id() + "");
    }
}
