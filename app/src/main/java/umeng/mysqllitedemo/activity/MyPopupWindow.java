package umeng.mysqllitedemo.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import umeng.mysqllitedemo.R;
import umeng.mysqllitedemo.bean.UserBean;
import umeng.mysqllitedemo.model.UserModel;

/**
 * Created by ZWP on 2016/8/25.
 * Explain:
 */
public class MyPopupWindow extends PopupWindow {

    private EditText et_account,et_password,et_name,et_sex,et_delete_pop,et_delete_pop_acc,et_delete_pop_name,et_update_account;
    private TextView selsect_account,select_name,select_sex;
    private Button btn_tijiao,btu_select_ganhui,btn_delete_pop,btn_delete_pop_more,btn_quxiao,btn_next;
    private UserBean user = new UserBean();
    private UserModel userModel = new UserModel();
    private int flag;
    private List<UserBean> users;
    private Context context;
    private View view;
    private LinearLayout pop_select;
    private LinearLayout pop_insert;
    private LinearLayout pop_deleteByAccount;
    private LinearLayout pop_del_Acc_Name;
    private RecyclerView recyclerView;
    private RecycleviewAdapter recycleAdapter;
    private ListView listview;
    private MyListAdapter adapter;
    public MyPopupWindow(Activity context) {
        super(context);
        initView(context);
    }

    public MyPopupWindow(Activity context, int flag) {
        this.flag = flag;
        initView(context);
    }

    private void initView(Activity context) {
        //获取布局
        view = View.inflate(context, R.layout.popwintow,null);
        setContentView(view);
        this.setFocusable(true);//可获取焦点
        this.setOutsideTouchable(false);//不可点击外部
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        if (flag == 1){
            selectAllUserState(context);
        }else if (flag == 2){
            deleteState(context);
        }else if(flag == 3){
            deleteByMoreState(context);
        }else if(flag == 4){
            updateUserInfo();
        }else if(flag == 5){
            deleteState(context);
        }
        else{
            insertState(context);
        }
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
    }

    public void insertState(Context context){
        //初始化里面的控件
        et_account = (EditText) view.findViewById(R.id.et_account);
        et_password = (EditText) view.findViewById(R.id.et_password);
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_sex = (EditText) view.findViewById(R.id.et_sex);
        btn_tijiao = (Button) view.findViewById(R.id.btn_tijiao);
        btn_quxiao = (Button) view.findViewById(R.id.btn_quxiao);

        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入数据，并储存到数据库中
                if (et_account.getText().toString().equals("")){
                    Log.e("ssa","账号不能为空");
                    return;
                }
                user.setUserAccount(et_account.getText().toString());
                user.setUserPassword(et_password.getText().toString());
                user.setUserName(et_name.getText().toString());
                user.setUserSex(et_sex.getText().toString());
                userModel.insertUser(user);
//                Log.e("ss",""+user.toString());
                //清空输入框
                cleanEditText();
            }
        });
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

    public void selectAllUserState(Context context){
        //初始化里面的控件
        pop_select = (LinearLayout) view.findViewById(R.id.pop_select);
        pop_insert = (LinearLayout) view.findViewById(R.id.pop_insert);
        pop_insert.setVisibility(View.GONE);
        pop_select.setVisibility(View.VISIBLE);
//        selsect_account = (TextView) view.findViewById(R.id.selsect_account);
//        select_name = (TextView) view.findViewById(R.id.select_name);
//        select_sex = (TextView) view.findViewById(R.id.select_sex);
        btu_select_ganhui = (Button) view.findViewById(R.id.btu_select_ganhui);
        recyclerView = (RecyclerView) view.findViewById(R.id.pop_recycle);
//        listview = (ListView) view.findViewById(R.id.listview);
        recycleAdapter = new RecycleviewAdapter(context);
        //设置recycleview的参数
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        //查询数据并显示
        List<UserBean> users = userModel.selectUser();
        Log.e("users",""+users.size());
        for (int i = 0 ; i < users.size() ; i++){
            Log.e("users",""+users.get(i).toString());
        }
        recycleAdapter.setUserBeen(users);
        recyclerView.setAdapter(recycleAdapter);
//        adapter = new MyListAdapter(context,users);
//        listview.setAdapter(adapter);

//        adapter = new RecycleviewAdapter(context,users);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        btu_select_ganhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void deleteState(final Context context){
        pop_deleteByAccount = (LinearLayout) view.findViewById(R.id.pop_deleteByAccount);
        pop_deleteByAccount.setVisibility(View.VISIBLE);
        pop_insert = (LinearLayout) view.findViewById(R.id.pop_insert);
        pop_insert.setVisibility(View.GONE);
        et_delete_pop = (EditText) view.findViewById(R.id.et_delete_pop);
        btn_delete_pop = (Button) view.findViewById(R.id.btn_delete_pop);
        if (flag == 5){
            et_delete_pop.setHint("请输入你要查找的User账户");
            btn_delete_pop.setText("确定");
        }
        Button btn_delete_pop_fanhui = (Button) view.findViewById(R.id.btn_delete_pop_fanhui);
        btn_delete_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 5){
                    if (et_delete_pop.getText().toString().equals("")){
                        Log.e("aaa","输入的账户不能为空");
                    }else{
                        //查询到对应的user信息
                       UserBean userinfo =  userModel.selectUserByAccount(et_delete_pop.getText().toString());
                        Log.e("aaa","++++++"+userinfo.toString());
                        //弹出dialog
                        UserInfoDialog dialog = new UserInfoDialog(context,userinfo);
//                        dialog.setUserinfo(userinfo);
                        dialog.show();
                        dismiss();
                    }
                }else {
                    String account = et_delete_pop.getText().toString();
                    //删除对应数据
                    Log.e("aaa",account);
//                userModel.deleteUserByUserAccount(account);
                    userModel.deleteUserByNameAndAccount("22","123");
                    dismiss();
                }
            }
        });
        btn_delete_pop_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void deleteByMoreState(final Activity context){
        pop_del_Acc_Name = (LinearLayout) view.findViewById(R.id.pop_del_Acc_Name);
        pop_del_Acc_Name.setVisibility(View.VISIBLE);
        pop_insert = (LinearLayout) view.findViewById(R.id.pop_insert);
        pop_insert.setVisibility(View.GONE);
        et_delete_pop_acc = (EditText) view.findViewById(R.id.et_delete_pop_acc);
        et_delete_pop_name = (EditText) view.findViewById(R.id.et_delete_pop_name);
        btn_delete_pop_more = (Button) view.findViewById(R.id.btn_delete_pop_more);
        Button btn_delete_pop_more_fanhui = (Button) view.findViewById(R.id.btn_delete_pop_more_fanhui);
        btn_delete_pop_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = et_delete_pop_acc.getText().toString();
                String name = et_delete_pop_name.getText().toString();
                if (!account.equals("")&&!name.equals("")){
                    //删除对应数据
                    Log.e("aaa","account"+account+"****name" +name);
//                userModel.deleteUserByUserAccount(account);
                    userModel.deleteUserByNameAndAccount(account,name);
                    dismiss();
                }else{
                    Toast.makeText(context,"账户或者姓名不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_delete_pop_more_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public List<UserBean>  addMoreUser(){

        if (et_account.getText().toString().equals("")){
            Log.e("ssa","账号不能为空");
        }else{
            //加入数据
            user.setUserAccount(et_account.getText().toString());
            user.setUserPassword(et_password.getText().toString());
            user.setUserName(et_name.getText().toString());
            user.setUserSex(et_sex.getText().toString());
            userModel.insertUser(user);
            users.add(user);
            //清空输入的数据
            cleanEditText();
            return users;
        }
        return null;
    }

    public void updateUserInfo(){
        //初始化里面的控件
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.ll_update_insert);
        layout.setVisibility(View.VISIBLE);
        et_account = (EditText) view.findViewById(R.id.et_account);
        et_update_account = (EditText) view.findViewById(R.id.et_update_account);
        et_password = (EditText) view.findViewById(R.id.et_password);
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_sex = (EditText) view.findViewById(R.id.et_sex);
        btn_tijiao = (Button) view.findViewById(R.id.btn_tijiao);
        btn_tijiao.setText("修改");
        btn_quxiao = (Button) view.findViewById(R.id.btn_quxiao);

        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得要修改的账号
                String account = et_update_account.getText().toString();
                //获取输入数据，并储存到数据库中
                if (et_account.getText().toString().equals("")||et_update_account.getText().toString().equals("")){
                    Log.e("ssa","要修改的账号或者修改后的账号不能为空");
                    return;
                }
                user.setUserAccount(et_account.getText().toString());
                user.setUserPassword(et_password.getText().toString());
                user.setUserName(et_name.getText().toString());
                user.setUserSex(et_sex.getText().toString());

                userModel.updateUser(account,user);
                dismiss();
            }
        });
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }


    public void cleanEditText(){
        et_account.setText("");
        et_password.setText("");
        et_name.setText("");
        et_sex.setText("");
    }
}
