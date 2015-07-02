package com.example.PhoneNumber;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by John on 15/7/2.
 */
public class PhoneInfoFetcher {

    static String baseUrl="http://v.showji.com/Locating/showji.com20150416273007.aspx?m=phoneNumber&output=json";

    public PhoneInfo fetchPhoneInfo(String mobileId) throws IOException, JSONException {
        PhoneInfo phoneInfo;
        InputStream is=getSearchInput(mobileId);
        String jsonText=InputStreamRead(is);

        phoneInfo = prase(jsonText);
        return phoneInfo;
    }
    public InputStream getSearchInput(String mobileId) throws IOException {
        String url=baseUrl.replace("phoneNumber",mobileId);
        HttpURLConnection cnn= (HttpURLConnection) new URL(url).openConnection();
        InputStream is=cnn.getInputStream();
        return is;
    }
    public String InputStreamRead(InputStream is) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
        StringBuilder builder=new StringBuilder();
        String line;
        if ((line=reader.readLine())!=null) {
            builder.append(line);
        }
        reader.close();
        return builder.toString();
    }
    public PhoneInfo prase(String jsonText) throws JSONException {
        JSONObject o = new JSONObject(jsonText);
        PhoneInfo phoneInfo =new PhoneInfo();

        phoneInfo.setProvince(o.getString("Province"));
        phoneInfo.setCity(o.getString("City"));
        phoneInfo.setAreaCode(o.getString("AreaCode"));
        phoneInfo.setPostCode(o.getString("PostCode"));
        phoneInfo.setOperator(o.getString("Corp"));
        phoneInfo.setQuerySuccess(o.getBoolean("QueryResult"));

        if (phoneInfo.getOperator().equals("中国电信")) {
            phoneInfo.setOperatorImage(R.drawable.telecom);
        }else if (phoneInfo.getOperator().equals("中国移动")) {
            phoneInfo.setOperatorImage(R.drawable.cmcc);
        }else if (phoneInfo.getOperator().equals("中国联通")) {
            phoneInfo.setOperatorImage(R.drawable.unicom);
        }

        return phoneInfo;
    }
}
