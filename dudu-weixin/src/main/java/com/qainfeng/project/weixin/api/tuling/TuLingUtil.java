package com.qainfeng.project.weixin.api.tuling;

import com.qainfeng.project.weixin.api.tuling.bean.InputText;
import com.qainfeng.project.weixin.api.tuling.bean.Perception;
import com.qainfeng.project.weixin.api.tuling.bean.TulingBean;
import com.qainfeng.project.weixin.api.tuling.bean.UserInfo;
import com.qainfeng.project.weixin.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TuLingUtil {
    private static final String TULING_API_URL="http://openapi.tuling123.com/openapi/api/v2";
    String apiKey [] = {"fb5a78bb2e79482d8075acd90b13231d","cd6f7bfdecba4d56a08a6956ea32c0f1","a2248ded1f8842729a36589d1829a986"};
    private int index = apiKey.length-1;
    public String sendMessage(String msg){
        JSONObject jsonObject = sendJSONObject(msg,apiKey[index]);
        JSONObject object = WeixinUtil.httpRequest(TULING_API_URL,"POST",jsonObject.toString());
        JSONArray array = (JSONArray) object.get("results");
        JSONObject object1 = (JSONObject) array.get(0);
        JSONObject object2 = (JSONObject) object1.get("values");
        String result = (String) object2.get("text");
        if(index<0){
            result = "不好意思我要睡觉了";
            return result;
        }
        if(result.equals("请求次数超限制!")){
                index = index-1;
                sendMessage(msg);
        }
        return result;

    }


    public JSONObject sendJSONObject(String msg,String apiKey){
        InputText inputText = new InputText();
        inputText.setText(msg);
        Perception perception = new Perception();
        perception.setInputText(inputText);
        UserInfo userInfo = new UserInfo();
        userInfo.setApiKey(apiKey );
        userInfo.setUserId("sunchen");

        TulingBean tulingBean = new TulingBean();
        tulingBean.setPerception(perception);
        tulingBean.setUserInfo(userInfo);
        JSONObject jsonObject =  JSONObject.fromObject(tulingBean);
        return jsonObject;
    }
}
