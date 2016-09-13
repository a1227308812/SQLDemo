package umeng.mysqllitedemo.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umeng.mysqllitedemo.base.BaseModel;
import umeng.mysqllitedemo.bean.BusBean;

/**
 * Created by ZWP on 2016/8/19.
 * Explain:车辆信息的model
 */
public class BusInfoModel extends BaseModel {

    public static final String BUS_TABLE_NAME = "businfo";//车辆信息表的表名
    public static final String BUS_NAME = "BUS_NAME";//车名
    public static final String BUS_ID = "BUS_ID";//车id
    public static final String BUS_IMGURL = "BUS_IMGURL";//车图片
    public static final String BUS_PRICE = "BUS_PRICE";//车指导价格

    private static Map<String, String> paramsMap = new HashMap<>();

    static {//构建创建表的SQL语句
        paramsMap.put(_ID, "integer primary key autoincrement");//定义自增长主键
        paramsMap.put(BUS_NAME, "TEXT NOT NULL");//车名
        paramsMap.put(BUS_ID, "TEXT NOT NULL");//车id
        paramsMap.put(BUS_IMGURL, "TEXT NOT NULL");//车图片
        paramsMap.put(BUS_PRICE, "TEXT NOT NULL");//车指导价格
    }

    //获得表名
    @Override
    protected String getTableName() {//获取表名
        return BUS_TABLE_NAME;
    }

    //获得列表参数
    @Override
    protected Map<String, String> getParamsMap() {//表的列参数
        return paramsMap;
    }

    /**
     * 插入一个用户
     */
    public void insertUser(BusBean BusBean) {
        if (BusBean == null)
            return;
        ContentValues values = new ContentValues();
        values.put(BUS_ID, BusBean.busid);
        values.put(BUS_NAME, BusBean.busname);
        values.put(BUS_IMGURL, BusBean.busimgurl);
        values.put(BUS_PRICE, BusBean.busiprice);
        insert(BUS_TABLE_NAME, values);

    }
    /**
     * 同时插入多个对象 (传入busbean的集合)
     * List<ContentValues> values集合中的元素为ContentValues对象，把要插入的值与对应列表装到ContentValues对象中，在加载到集合中
     */
    public void insertmore(List<BusBean> busBeanList ){
        if (busBeanList == null)
            return;
        List<ContentValues> values = new ArrayList<>();
        for (BusBean busBean : busBeanList)
        {
            if (busBean == null)
                return;
            ContentValues value = new ContentValues();
            value.put(BUS_ID, busBean.busid);
            value.put(BUS_NAME, busBean.busname);
            value.put(BUS_IMGURL, busBean.busimgurl);
            value.put(BUS_PRICE, busBean.busiprice);
            values.add(value);
        }
        super.insertMore(BUS_TABLE_NAME, values);
    }

    /**
     * 删除一条数据(根据车辆id删除该条浏览记录)
     */
    public void deleteDataById(String busid){
        if (busid == null)
            return;
        super.delete(BUS_TABLE_NAME,BUS_ID,new String[]{busid});
    }
    /**
     * 删除一条数据(根据车辆id和车辆名字删除该条浏览记录)
     */
    public void deleteDataByIdAndName(String busid,String busname){
        if (busid == null && busname == null)
            return;
        super.delete(BUS_TABLE_NAME,BUS_NAME,new String[]{busid,busname});
    }
    /**
     * 删除表
     */
    public void deleteTable(String tablename){
        if (tablename == null)
            return;
        super.deleteTable(tablename);
    }

    /**
     * 更新数据(根据车辆id更新对应车辆的所有信息)
     * busbean表示要被修改进入到数据库的车辆对象
     * id表示被更新的车辆id
     */
    public void updateData(BusBean busBean, String busid) {
        if (busBean == null || busid == null)
            return;
        ContentValues values = new ContentValues();
        values.put(BUS_ID,busBean.busid);
        values.put(BUS_NAME,busBean.busname);
        values.put(BUS_IMGURL,busBean.busimgurl);
        values.put(BUS_PRICE,busBean.busiprice);
        super.update(BUS_TABLE_NAME, values, BUS_ID, new String[]{busid});
    }

    /**
     * 查询对象
     * 根据车辆id查询
     */
    public BusBean getBusById(String busid) {
        if (busid == null)
            return null;
        BusBean busBean = new BusBean();
        String sql = "select * from "+BUS_TABLE_NAME+" where "+BUS_ID+" =" + busid;
        Cursor cursor = select(sql);

        if (cursor != null) {
            if (cursor.moveToNext()) {//找到指定的数据了
                busBean.busname = cursor.getString(cursor.getColumnIndex(BUS_NAME));
                busBean.busid = cursor.getString(cursor.getColumnIndex(BUS_ID));
                busBean.busimgurl = cursor.getString(cursor.getColumnIndex(BUS_IMGURL));
                busBean.busiprice = cursor.getString(cursor.getColumnIndex(BUS_PRICE));
            }
        }
        return busBean;
    }
    /**
     * 查询对象
     * 查询当前车车辆信息表中的所有车辆数据
     */
    public List<BusBean> getAllBusById() {
        List<BusBean> allBus = new ArrayList<>();
        BusBean busBean = new BusBean();
        String sql = "select * from "+BUS_TABLE_NAME;
        Cursor cursor = select(sql);

        if (cursor != null) {
            while (cursor.moveToNext()) {//找到每一条的数据了
                busBean.busname = cursor.getString(cursor.getColumnIndex(BUS_NAME));
                busBean.busid = cursor.getString(cursor.getColumnIndex(BUS_ID));
                busBean.busimgurl = cursor.getString(cursor.getColumnIndex(BUS_IMGURL));
                busBean.busiprice = cursor.getString(cursor.getColumnIndex(BUS_PRICE));
                //加入到集合中
                allBus.add(busBean);
            }
        }
        return allBus;
    }


}
