package com.tieshan.disintegrate.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ningfeng
 * @description:
 * @date Created in 9:35 2019/10/17
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarArticle implements Serializable {
    // 主键id
    @Id
    private Long id;
    // 状态：1.正常，2.不启用
    private Integer state;
    // 标题
    private String articleTitle;
    // 文章内容
    private String articleContent;
    // 创建时间
    private Date createTime;
    // 创建人id
    private Long createUserId;
    // 创建人姓名
    private String createUser;
}
