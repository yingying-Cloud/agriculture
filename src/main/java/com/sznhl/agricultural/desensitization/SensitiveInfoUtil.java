package com.sznhl.agricultural.desensitization;

import com.sznhl.agricultural.util.AESUtil;
import org.apache.commons.lang3.StringUtils;

public class SensitiveInfoUtil {
    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     */
    public static String chineseName(String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        String name = StringUtils.left(fullName, 1);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }

    /**
     * [身份证号] 显示最后四位，其他隐藏。共计18位或者15位。<例子：3304**********5762>
     */
    public static String idCardNum(String id) {
        if (StringUtils.isBlank(id)) {
            return "";
        }

//        return StringUtils.left(id, 4).concat(StringUtils
//                .removeStart(StringUtils.leftPad(StringUtils.right(id, 4), StringUtils.length(id), "*"),
//                        "****"));
        return AESUtil.encrypt(id);
    }

    /**
     * [手机号码] 前三位，后四位，其他隐藏<例子:138*****234>
     */
    public static String mobilePhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num, 3).concat(StringUtils
                .removeStart(StringUtils.leftPad(StringUtils.right(num, 3), StringUtils.length(num), "*"),
                        "***"));

    }
}
