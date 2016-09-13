package umeng.mysqllitedemo;

import umeng.mysqllitedemo.model.BusInfoModel;
import umeng.mysqllitedemo.model.UserModel;

/**
 * Created by Lizhangfeng on 2016/7/13 0013.
 * Description: 常量类
 */
public class Constants {

    public static final String DB_NAME = "SQL_Demo";//数据库名称

    public static final int DB_VERSION = 1;//数据库版本


    //数据库所有的表(要创建上面表就在该表的model.class.getName()数组中加入)
    public static String[] TABLES = new String[]{UserModel.class.getName()};//DBhelper中可以根据类名那到类的对象

}
