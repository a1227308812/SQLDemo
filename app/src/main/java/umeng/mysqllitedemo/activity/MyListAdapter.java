package umeng.mysqllitedemo.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import umeng.mysqllitedemo.R;
import umeng.mysqllitedemo.bean.UserBean;

/**
 * Created by ZWP on 2016/8/25.
 * Explain:
 */
public class MyListAdapter extends BaseAdapter {

    private Context context;
    private List<UserBean> userBeen;

    public MyListAdapter(Context context, List<UserBean> userBeen) {
        this.context = context;
        this.userBeen = userBeen;
    }

    @Override
    public int getCount() {
        return userBeen == null ? 0 :userBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return userBeen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View conventview, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (conventview == null){
            viewHolder = new ViewHolder();
            conventview = View.inflate(context, R.layout.item_recycler,null);
            viewHolder.tv_account = (TextView) conventview.findViewById(R.id.tv_account);
            viewHolder.tv_name = (TextView) conventview.findViewById(R.id.tv_name);
            viewHolder.tv_sex = (TextView) conventview.findViewById(R.id.tv_sex);
            viewHolder.tv_time = (TextView) conventview.findViewById(R.id.tv_time);
            conventview.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) conventview.getTag();
        }
        viewHolder.tv_account.setText(userBeen.get(position).getUserAccount());
        viewHolder.tv_name.setText(userBeen.get(position).getUserName());
        viewHolder.tv_sex.setText(userBeen.get(position).getUserSex());
        return conventview;
    }

    public class ViewHolder{

        public TextView tv_account,tv_name,tv_sex,tv_time;
    }
}
