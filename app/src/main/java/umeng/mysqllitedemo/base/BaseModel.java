package umeng.mysqllitedemo.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;


import java.util.List;
import java.util.Map;


/**
 * Created by Lizhangfeng on 2016/8/8 0008.
 * Description: 基类model
 */
public abstract class BaseModel implements BaseColumns {

    private DBHelper dbHelper;

    public BaseModel() {
        dbHelper = DBHelper.getInstence();
    }

    /**
     * 获取破解后的创建表的SQL语句
     * @return
     */
    public String getCreateTableSql() {
        return getCreateTable(getTableName(), getParamsMap());
    }

    /**
     * 拼接创建表的SQL语句
     * @param tableName
     * @param map
     * @return
     */
    public String getCreateTable(String tableName, Map<String, String> map) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table ").append(tableName).append(" (");

        for (Map.Entry<String, String> entity : map.entrySet()) {
            stringBuilder.append(entity.getKey()).append(" ").append(entity.getValue());
            stringBuilder.append(",");
        }
        String sql = stringBuilder.substring(0, stringBuilder.length() - 1);
        sql = sql + ")";
        return sql;
    }

    /**
     * 插入一条
     */
    public void insert(String tableName, ContentValues values) {
        String insert= "insert into 表名称 (列名称1.....) value (值1.....)";
        //1 表名  2 null 3 （相当于一个map）
        dbHelper.getWritableDatabase().insert(tableName, null, values);
    }

    /**
     * 插入多条数据（没插入一条数据，数据库会默认开启一个事务）
     *
     * @param tableName
     * @param values
     */
    public void insertMore(String tableName, List<ContentValues> values) {
        //事务的开启
        dbHelper.getWritableDatabase().beginTransaction();
        for (int i = 0; i < values.size(); i++) {
            insert(tableName, values.get(i));
        }
        dbHelper.getWritableDatabase().endTransaction();//事务的结束

    }

    /**
     * 更新
     */
    public void update(String tableName, ContentValues values, String where, String[] whereArgs) {
        String update= "update 表名称 set 列名称1 = 新值1，列名称2 = 新值2... where 列名称1 = 值1 and/or 列名称 = 值2...";//UPDATE 表名称 SET 列名称= 新值 WHERE 列名称 = 某值
        //1 表名称 2 你要修改的参数（是一个键值对，key表示那一列，value表示这一列的哪一行的数据） 3 条件（列名称（可确定是那一列）） 4条件（具体的值）  3,4时一一对应的3的列名称中的具体指来确定那一行
        dbHelper.getWritableDatabase().update(tableName, values, where, whereArgs);
    }

    /**
     * 删除数据
     */
    public void delete(String tableName, String where, String[] whereArgs) {
        String delete = "delete from 表名称 where 列名称1 = 值1 and/or 列名称2 = 值2... ";
        //1 表名称 2 列名称 3 条件值（对应2）
        //
        dbHelper.getWritableDatabase().delete(tableName, where, whereArgs);
    }

    /**
     * 查询
     *
     * @param sql
     */
    public Cursor select(String sql) {
//        String sql = "select * from 表名称";
//        read.execSQL();//这种方式只返回数据 不返回游标
        return dbHelper.getReadableDatabase().rawQuery(sql, null);
    }

    /**
     * 清空表
     *
     * @param tableName
     */
    public void clear(String tableName) {
        dbHelper.getWritableDatabase().execSQL("delete from " + tableName);
    }

    /**
     * 删除整个表
     *
     * @param tableName
     */
    public void deleteTable(String tableName) {
        String sql = "delete * from "+tableName;
        dbHelper.getWritableDatabase().execSQL("drop table " + tableName);
    }

    protected abstract String getTableName();

    protected abstract Map<String, String> getParamsMap();


}
