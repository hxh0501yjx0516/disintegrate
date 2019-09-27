package com.tieshan.disintegrate.vo;

import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/26 19:02
 * @version: 1.0
 * @modified By:
 */
public class CaiPrePicVo implements Serializable {
    private static final long serialVersionUID = 1169844612292509144L;
    private Long id;
    private String file_name;
    private String first_type;
    private String two_type;
    private String file_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFirst_type() {
        return first_type;
    }

    public void setFirst_type(String first_type) {
        this.first_type = first_type;
    }

    public String getTwo_type() {
        return two_type;
    }

    public void setTwo_type(String two_type) {
        this.two_type = two_type;
    }

    public String getFile_url() {
        if(StringUtils.isEmpty( this.file_url)){
            if(this.two_type.equals("left45")){
                return "https://ts-disintegrate.oss-cn-beijing.aliyuncs.com/zuoqian45.png";
            }
            if(this.two_type.equals("right45")){
                return "https://ts-disintegrate.oss-cn-beijing.aliyuncs.com/youqian45.png";
            }
            if(this.two_type.equals("check")){
                return "https://ts-disintegrate.oss-cn-beijing.aliyuncs.com/jinazi.png";
            }
            if(this.two_type.equals("mingpai")){
                return "https://ts-disintegrate.oss-cn-beijing.aliyuncs.com/mingpai.png";
            }
            if(this.two_type.equals("vin")){
                return "https://ts-disintegrate.oss-cn-beijing.aliyuncs.com/chejiahao.png";
            }
            if(this.two_type.equals("engine")){
                return "https://ts-disintegrate.oss-cn-beijing.aliyuncs.com/fadongjihao.png";
            }
            return "https://ts-disintegrate.oss-cn-beijing.aliyuncs.com/zuoqian45.png";
        }else {
            return file_url;
        }
    }

    public void setFile_url(String file_url) {
            this.file_url=file_url;

    }
}
