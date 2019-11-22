package com.qainfeng.project.weixin.api.tuling.bean;

import lombok.Data;

@Data
public class TulingBean {
    //机器人的标识
    private Perception perception;
    //用户的唯一标识
    private UserInfo userInfo;

}
