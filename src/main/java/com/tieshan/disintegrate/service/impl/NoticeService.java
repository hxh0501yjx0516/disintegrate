package com.tieshan.disintegrate.service.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.PushPayload;
import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.dao.NoticeMapper;
import com.tieshan.disintegrate.pojo.Notice;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.INoticeService;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.JPushUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

    private static final String APP_KEY = "945f168fb6a5d5ddb2dfa461";
    private static final String MASTER_SECRET = "a3f1d24bec914d8df15b7343";
    //    private static final String MSG_CONTENT = "车互联";
    private static JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());


    @Override
    public List<Map<String, Object>> getTop(HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return noticeMapper.getTop(sysUser.getCompany_id() + "");
    }

    @Override
    public int push(String id, HttpServletRequest request) {
        int num = noticeMapper.push(id);
        int resultNum = 0;
        if (num > 0) {
            Notice notice = noticeMapper.pushNotice(id);
            Map<String, String> pushMap = object2Map(notice);
            pushMap.remove("is_push");
            try {
                if ("1".equals(pushMap.get("device_type"))) {
                    resultNum = JPushUtil.buildPushAndroid(pushMap);

                } else if ("2".equals(pushMap.get("device_type"))) {
                    resultNum = JPushUtil.buildPushIOS(pushMap);
                } else {
                    pushMap.put("device_type", "1");
                    resultNum = JPushUtil.buildPushAndroid(pushMap);
                    pushMap.put("device_type", "2");
                    resultNum = JPushUtil.buildPushIOS(pushMap);
                }


            } catch (APIConnectionException e) {
                e.printStackTrace();
            } catch (APIRequestException e) {
                e.printStackTrace();
            }
        }
        return resultNum;
    }

    @Override
    @Transactional
    public int insertNotice(Notice notice, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        IdWorker idWorker = new IdWorker(1, 1, 1);
        notice.setOperator(sysUser.getLogin_name());
        notice.setId(idWorker.nextId());
        notice.setDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        notice.setDisintegrate_plant_id(sysUser.getCompany_id() + "");
        int num = noticeMapper.insertNotice(notice);
//        int resultNum = 0;
//        if (num > 0) {
//
//            Map<String, String> pushMap = object2Map(notice);
//            pushMap.remove("operator");
//            pushMap.remove("disintegrate_plant_id");
//            try {
//                if ("1".equals(pushMap.get("device_type"))) {
//                    resultNum = JPushUtil.buildPushAndroid(pushMap);
//
//                } else if ("2".equals(pushMap.get("device_type"))) {
//                    resultNum = JPushUtil.buildPushIOS(pushMap);
//                } else {
//                    pushMap.put("device_type", "1");
//                    resultNum = JPushUtil.buildPushAndroid(pushMap);
//                    pushMap.put("device_type", "2");
//                    resultNum = JPushUtil.buildPushIOS(pushMap);
//                }
//
//
//            } catch (APIConnectionException e) {
//                e.printStackTrace();
//            } catch (APIRequestException e) {
//                e.printStackTrace();
//            }
//        }
        return num;
    }

    @Override
    public List<Map<String, Object>> selNotice(String type, String device_type, HttpServletRequest request, int page, int pageSize) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("datetime desc");
        List<Map<String, Object>> resultList = noticeMapper.selNotice(type, device_type, sysUser.getCompany_id() + "");
        return resultList;
    }

    @Override
    public Map<String, Object> selNoticeById(String id) {
        return noticeMapper.selNoticeById(id);
    }

    @Override
    public List<Map<String, Object>> selNoticePC(HttpServletRequest request, int page, int pageSize) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("datetime desc");
        List<Map<String, Object>> resultList = noticeMapper.selNoticePC(sysUser.getCompany_id() + "");
        return resultList;
    }

    @Override
    public Map<String, Object> selNoticeByIdPC(String id) {
        return noticeMapper.selNoticeByIdPC(id);
    }

    @Override
    public int delNoticeById(String id) {
        return noticeMapper.delNoticeById(id);
    }

    @Override
    public int upNotieById(Notice notice) {
        return noticeMapper.upNotieById(notice);
    }


    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */
    public static Map<String, String> object2Map(Object obj) {
        Map<String, String> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
