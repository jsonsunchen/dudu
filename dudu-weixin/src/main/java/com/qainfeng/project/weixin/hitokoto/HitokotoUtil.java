package com.qainfeng.project.weixin.hitokoto;

import com.qainfeng.project.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public class HitokotoUtil {
    private static final String HITOKOTO_API_URL ="https://v1.hitokoto.cn/";
    public String sendmsg(){
        JSONObject jsonObject = WeixinUtil.httpRequest(HITOKOTO_API_URL,"GET",null);
        String result = (String) jsonObject.get("hitokoto");
        return result;
    }

}
