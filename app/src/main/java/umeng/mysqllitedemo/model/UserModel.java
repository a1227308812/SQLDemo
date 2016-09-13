package umeng.mysqllitedemo.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umeng.mysqllitedemo.base.BaseModel;
import umeng.mysqllitedemo.base.DBHelper;
import umeng.mysqllitedemo.bean.UserBean;

/**
 * Created by ZWP on 2016/8/25.
 * Explain:
 */
public class UserModel extends BaseModel {

    private DBHelper dbHelper;
    public static final String USER_TABLE_NAME = "userinfo";//user信息表的表名
    public static final String USER_ACCOUNT = "user_account";//账户名
    public static final String USER_PASSWORD = "user_password";//密码
    public static final String USER_NAME = "user_name";//姓名
    public static final String USER_SEX = "user_sex";//性别
//    public static final String TIME = "time";//时间

    private static Map<String,String> paramsMap = new HashMap<>();
    {//获取列名
        paramsMap.put(_ID, "integer primary key autoincrement");//定义自增长主键
        paramsMap.put(USER_ACCOUNT, "TEXT NOT NULL");//账户名
        paramsMap.put(USER_PASSWORD, "TEXT NOT NULL");//密码
        paramsMap.put(USER_NAME, "TEXT NOT NULL");//姓名
        paramsMap.put(USER_SEX, "TEXT NOT NULL");//性别
//        paramsMap.put(TIME, "TEXT NOT NULL");//时间

        //USER_ACCOUNT = ？ and USER_NAME = ？    3231   646
    }

    public UserModel() {
        this.dbHelper = DBHelper.getInstence();
    }

    //获取表名
    @Override
    protected String getTableName() {
        return USER_TABLE_NAME;
    }
    //获取创建表时所需的列名
    @Override
    protected Map<String, String> getParamsMap() {
        return paramsMap;
    }

    /**
     * 插入一条数据
     * @param userBean
     */
    public void insertUser(UserBean userBean){
        if (userBean == null){
            Log.e("insertUser","userBean  为空");
            return;
        }

        ContentValues value = new ContentValues();
        value.put(USER_ACCOUNT,userBean.userAccount);
        value.put(USER_PASSWORD,userBean.userPassword);
        value.put(USER_NAME,userBean.userName);
        value.put(USER_SEX,userBean.userSex);
        super.insert(USER_TABLE_NAME,value);
    }

    /**
     * 插入多条数据
     * @param users
     */
    public void insertMoreUser(List<UserBean> users){
        if (users == null){
            Log.e("insertMoreUser","users  为空");
            return;
        }
        List<ContentValues> values = new ArrayList<>();
        ContentValues value = new ContentValues();
        for (UserBean user : users){
            if(user == null){
                Log.e("insertMoreUser","insertMoreUser  插入数据有空");
                return;
            }
            value.put(USER_ACCOUNT,user.userAccount);
            value.put(USER_PASSWORD,user.userPassword);
            value.put(USER_NAME,user.userName);
            value.put(USER_SEX,user.userSex);
            values.add(value);
        }
        super.insertMore(USER_TABLE_NAME,values);
    }

    /**
     * 根据用户名删除对应用户信息
     * @param userAccount
     */
    public void deleteUserByUserAccount(String userAccount){
        if (userAccount == null&&userAccount.equals("")){
            Log.e("deleteUserByUserName","userName  删除数据用户名不能为空");
            return;
        }
        String delete = "delete from "+USER_TABLE_NAME+" where "+USER_ACCOUNT+" = "+userAccount;
        dbHelper.getWritableDatabase().execSQL(delete);
//        super.delete(USER_TABLE_NAME,USER_ACCOUNT,new String[]{userAccount});
    }

    /**
     * 根据用户名和账户删除对应用户数据
     * @param username
     * @param account
     */
    public void deleteUserByNameAndAccount(String account,String username){
        String delete = "delete from 表名称 where 列名称1 = 值1 and/or 列名称2 = 值2... ";
        if (username == null || account == null){
            Log.e("delete","userName  删除数据用户名或者账户不能为空");
            return;
        }
        //方法一
//        String deletesql = "delete from "+USER_TABLE_NAME+" where "+USER_NAME+" = "+username
//                +" and "+USER_ACCOUNT+" = "+account;
//        dbHelper.getWritableDatabase().execSQL(deletesql);
        //方法二
        super.delete(USER_TABLE_NAME,USER_ACCOUNT+"=? and "+USER_NAME+"=?",new String[]{account,username});
    }

    /**
     * 更新
     * @param userAccount
     * @param userBean
     */
    public void updateUser(String userAccount,UserBean userBean){
        ContentValues values = new ContentValues();
        values.put(USER_ACCOUNT,userBean.userAccount);
        values.put(USER_PASSWORD,userBean.userPassword);
        values.put(USER_NAME,userBean.userName);
        values.put(USER_SEX,userBean.userSex);
        super.update(USER_TABLE_NAME,values,USER_ACCOUNT +" = ?",new String[]{userAccount});
    }

    /**
     * 查询user表中的所有数据
     * @return
     */
    public List<UserBean> selectUser (){
        String sql = "select * from "+USER_TABLE_NAME;
        Cursor cursor = super.select(sql);//获得游标
        if (cursor == null){
            Log.e("selectUser","cursor为空");
        }else{
            List<UserBean> users = new ArrayList<>();
//
            while(cursor.moveToNext()){
                UserBean userBean = new UserBean();
                userBean.setUserAccount(cursor.getString(cursor.getColumnIndex(USER_ACCOUNT)));
                userBean.setUserPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
                userBean.setUserName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                userBean.setUserSex(cursor.getString(cursor.getColumnIndex(USER_SEX)));
                users.add(userBean);
//                users.add(new UserBean(cursor.getString(cursor.getColumnIndex(USER_ACCOUNT)),cursor.getString(cursor.getColumnIndex(USER_NAME))));
            }
            return users;
        }
        return null;
    }

    /**
     * 根据账户名查找对应的User
     * @param userAccount
     * @return
     */
    public UserBean selectUserByAccount(String userAccount){
        String sql = "select * from "+  USER_TABLE_NAME+" where "+USER_ACCOUNT+" = "+userAccount;
        Cursor cursor  = super.select(sql);
        if (cursor == null){
            Log.e("cursor","cursor 为空");
        }else {
            UserBean userBean = new UserBean();
            while (cursor.moveToNext()){
                userBean.setUserAccount(cursor.getString(cursor.getColumnIndex(USER_ACCOUNT)));
                userBean.setUserPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
                userBean.setUserName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                userBean.setUserSex(cursor.getString(cursor.getColumnIndex(USER_SEX)));
            }
            return userBean;
        }
        return null;
    }

    /**
     * 清空对应表
     * @param tableName
     */
    public void clearTable(String tableName){
        super.clear(tableName);
    }

}
