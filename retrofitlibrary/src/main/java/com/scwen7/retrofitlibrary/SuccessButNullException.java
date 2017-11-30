package com.scwen7.retrofitlibrary;

/**
 * Created by 解晓辉  on 2017/11/30 10:47 *
 * QQ  ：811733738
 * 作用: 网络请求成功但是  数据为 NULL 的  Exception
 */

public class SuccessButNullException extends BaseException {

    public SuccessButNullException(String code, String displayMessage) {
        super(code, displayMessage);
    }
}
