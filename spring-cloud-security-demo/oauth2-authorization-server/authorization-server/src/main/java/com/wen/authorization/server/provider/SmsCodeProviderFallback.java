package com.wen.authorization.server.provider;

/**
 * @ClassName: SmsCodeProviderFallback
 * @Description:
 * @Author: 文
 * @Data 2024/3/21
 * @Version 1.0
 */
public class SmsCodeProviderFallback implements SmsCodeProvider{
    @Override
    public String getSmsCode(String mobile, String businessType) {
        return null;
    }
}
