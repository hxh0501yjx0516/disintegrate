package com.tieshan.disintegrate.job;

import com.tieshan.disintegrate.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/8/29 10:02
 * @version: 1.0
 * @modified By:
 */
@Component
@EnableScheduling
public class JobTask {
    @Autowired
    private UserDao userDao;
//    @Scheduled(cron = "${testJob}")
    public void testJob(){
        System.err.println("---定时任务开始-----");
        System.out.println(userDao.getUser());
        System.err.println("---定时任务结束-----");


    }
}
