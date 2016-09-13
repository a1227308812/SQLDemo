package umeng.mysqllitedemo.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import umeng.mysqllitedemo.R;
import umeng.mysqllitedemo.bean.UserBean;

/**
 * Created by ZWP on 2016/8/29.
 * Explain:
 */
public class UserInfoDialog extends Dialog implements View.OnClickListener{
    public TextView tv_account_userinfo,tv_pwd_userinfo,tv_name_userinfo,tv_sex_userinfo;
    public Button btn_quxiao_userinfo,btn_queding_userinfo;
    public UserBean userinfo;

    public void setUserinfo() {

        initData();
    }

    public UserInfoDialog(Context context,UserBean userinfo) {
        super(context);
        this.userinfo = userinfo;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_userinfo);
        this.setTitle("User详细信息");
        initView();
        initData();
    }

    public void initView(){
        tv_account_userinfo = (TextView) findViewById(R.id.tv_account_userinfo);
        tv_pwd_userinfo = (TextView) findViewById(R.id.tv_pwd_userinfo);
        tv_name_userinfo = (TextView) findViewById(R.id.tv_name_userinfo);
        tv_sex_userinfo = (TextView) findViewById(R.id.tv_sex_userinfo);
        btn_quxiao_userinfo = (Button) findViewById(R.id.btn_quxiao_userinfo);
        btn_queding_userinfo = (Button) findViewById(R.id.btn_queding_userinfo);
        btn_quxiao_userinfo.setOnClickListener(this);
        btn_queding_userinfo.setOnClickListener(this);

    }
    public void initData(){
        if (userinfo == null){
            return;
        }
        tv_account_userinfo.setText(String.format("账户：%s",userinfo.getUserAccount()));
        tv_pwd_userinfo.setText(String.format("密码：%s",userinfo.getUserPassword()));
        tv_name_userinfo.setText(String.format("姓名：%s",userinfo.getUserName()));
        tv_sex_userinfo.setText(String.format("性别：%s",userinfo.getUserSex()));


    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_quxiao_userinfo:
                    dismiss();
                break;
                case R.id.btn_queding_userinfo:
                    dismiss();
                break;
            }
    }
}
