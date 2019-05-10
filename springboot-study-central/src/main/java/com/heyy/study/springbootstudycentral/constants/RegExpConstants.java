package com.heyy.study.springbootstudycentral.constants;

/**
 * @Classname RegExpConstants
 * @Description TODO
 * @Date 2019/5/9 20:49
 * @Created by Breeze
 */
public class RegExpConstants {
    public static final String REGEX_NAME = "([A-Za-z0-9\\u4e00-\\u9fa5]|-){1,}";
    public static final String REGEX_STAFF_ID = "\\d+";
    public static final String REGEX_STAFF_NAME = "^[A-Za-z]+$";
    public static final String REGEX_FROM_DB = "(TRUE|FALSE)";
    public static final String REGEX_ADDRESS_TYPE = "[123]{1}";
    public static final String REGEX_ID_TYPE = "[01]{1}";
}
