package com.tieshan.disintegrate.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ningrz
 * @version 1.0
 * @date 2019/7/3 15:26
 */

public class JPushUtil {

    private static final String APP_KEY = "945f168fb6a5d5ddb2dfa461";
    private static final String MASTER_SECRET = "a3f1d24bec914d8df15b7343";
    //    private static final String MSG_CONTENT = "车互联";
    private static JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());


    public static void main(String[] args) throws APIConnectionException, APIRequestException {
        String notification_title = "消息推送1";
        String msg_title = "发版本";
        String msg_content = "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试";
        String id = "12321321321321321321";
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("msg_title", msg_title);
        map.put("msg_content", msg_content);
        map.put("type", "1");
        map.put("datetime", "2019-11-11 12:12:12");


//        PushPayload pushPayload = JPushUtil.buildPushObject_android_and_ios(notification_title, msg_title, msg_content, map);
//        PushResult PushResult = JPushUtil.buildPushAndroid(map);
//        PushResult pushResult = jpushClient.sendPush(pushPayload);
//        System.err.println(pushResult);
//        System.err.println(pushResult);
//        System.err.println(pushResult);
    }


    public static int buildPushAndroid(Map<String, String> map) throws APIConnectionException, APIRequestException {
        PushPayload pushPayload = JPushUtil.android(map);
        PushResult pushResult = jpushClient.sendPush(pushPayload);
        System.err.println(pushResult);
        if (pushResult.getResponseCode() == 200) {
            return 1;
        }
        return 2;

    }


    /**
     * 推送到所有用户 (Android)
     *
     * @return
     */
    public static PushPayload android(Map<String, String> map) {
        String msg_content = map.get("msg_content");
        if (msg_content.length() > 20) {
            msg_content = msg_content.substring(0, 20);
        }
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert(map.get("msg_title"))
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(map.get("msg_title"))
                                .setAlert(msg_content)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(map)
                                .build()
                        )
                        .build()
                )
                .build();
    }

    /**
     * 推送到所有用户 (IOS)
     *
     * @param map
     * @return
     */
    public static PushPayload buildPushIOS(Map<String, String> map) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
//                        .setAlert("公告")
                                .addPlatformNotification(IosNotification.newBuilder()
                                        //传一个IosAlert对象，指定apns title、title、subtitle等
                                        .setAlert(map.get("msg_title"))
                                        //直接传alert
                                        //此项是指定此推送的badge自动加1
                                        .incrBadge(1)
                                        //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                        // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                        .setSound("default")
                                        .addExtras(map)
                                        .build()
                                )
                                .build()
                )
                .build();
    }


    /**
     * 推送到所有用户
     *
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param map
     * @return
     */
    public static PushPayload buildPushObject_android_and_ios(String notification_title, String msg_title, String msg_content, Map<String, String> map) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                                .setAlert("公告")
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                                .setTitle(map.get("msg_title"))
                                                .setAlert(map.get("msg_content"))
                                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
//                                                .addExtra("id", map.get("id"))
//                                                .addExtra("title", map.get("msg_title"))
//                                                .addExtra("content", map.get("msg_content"))
//                                                .addExtra("type", map.get("type"))
//                                                .addExtra("datetime", map.get("type"))
                                                .addExtras(map)
                                                .build()
                                )
                                .addPlatformNotification(IosNotification.newBuilder()
                                        //传一个IosAlert对象，指定apns title、title、subtitle等
                                        .setAlert("公告")
                                        //直接传alert
                                        //此项是指定此推送的badge自动加1
                                        .incrBadge(1)
                                        //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                        // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                        .setSound("default")
                                        .addExtras(map)
                                        .build()
                                )
                                .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra("msgKey", map.toString())
                        .build())

                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build()
                )
                .build();
    }


}

