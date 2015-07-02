package com.example.PhoneNumber;

/**
 * Created by John on 15/7/2.
 */
public class PhoneInfo {
    private String province;
    private String city;
    private String areaCode;
    private String postCode;
    private String operator;
    private int operatorImage;
    private boolean querySuccess;

    public boolean isQuerySuccess() {
        return querySuccess;
    }

    public void setQuerySuccess(boolean querySuccess) {
        this.querySuccess = querySuccess;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getOperatorImage() {
        return operatorImage;
    }

    public void setOperatorImage(int operatorImage) {
        this.operatorImage = operatorImage;
    }
}
