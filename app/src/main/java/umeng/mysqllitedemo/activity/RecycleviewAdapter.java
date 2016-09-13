package umeng.mysqllitedemo.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import umeng.mysqllitedemo.R;
import umeng.mysqllitedemo.bean.UserBean;

/**
 * Created by ZWP on 2016/8/25.
 * Explain:
 */
public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.RecyclerViewHolder> {

    private Context context;
    private List<UserBean> userBeen;

    public RecycleviewAdapter(Context context) {
        this.context = context;
    }

    public RecycleviewAdapter(Context context, List<UserBean> userBeen) {
        this.context = context;
        this.userBeen = userBeen;
    }

    public void setUserBeen(List<UserBean> userBeen) {
        this.userBeen = userBeen;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_recycler,null);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        viewHolder.tv_account = (TextView) view.findViewById(R.id.tv_account);
        viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
        viewHolder.tv_sex = (TextView) view.findViewById(R.id.tv_sex);
        viewHolder.tv_time = (TextView) view.findViewById(R.id.tv_time);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (userBeen == null)
            return;
//        String ss = String.format("性别：%s",userBeen.get(position).getUserAccount());
        holder.tv_account.setText(String.format("账户：%s",userBeen.get(position).getUserAccount()));
        holder.tv_name.setText(String.format("姓名：%s",userBeen.get(position).getUserName()));
        holder.tv_sex.setText(String.format("性别：%s",userBeen.get(position).getUserSex()));
//        holder.tv_time.setText(String.format("时间：%s",userBeen.get(position).getTime()));

        //Item的监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,""+view.getId(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userBeen == null ? 0 : userBeen.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
        public TextView tv_account,tv_name,tv_sex,tv_time;
    }
}
