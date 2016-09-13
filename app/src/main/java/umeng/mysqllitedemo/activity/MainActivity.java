package umeng.mysqllitedemo.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import umeng.mysqllitedemo.R;
import umeng.mysqllitedemo.model.UserModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_insertOne,btn_insertMore,btn_deleteByOne,btn_deleteByMore,btn_updateByOne,
            btn_updateByMore,btn_selectOne,btn_selectAll,btn_cleanTable;
    private RecyclerView recyclerView;
    private UserModel userModel = new UserModel();
//    private
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_insertOne = (Button) findViewById(R.id.btn_insertOne);
        btn_insertMore = (Button) findViewById(R.id.btn_insertMore);
        btn_deleteByOne = (Button) findViewById(R.id.btn_deleteByOne);
        btn_deleteByMore = (Button) findViewById(R.id.btn_deleteByMore);
        btn_updateByOne = (Button) findViewById(R.id.btn_updateByOne);
        btn_updateByMore = (Button) findViewById(R.id.btn_updateByMore);
        btn_selectOne = (Button) findViewById(R.id.btn_selectOne);
        btn_selectAll = (Button) findViewById(R.id.btn_selectAll);
        btn_cleanTable = (Button) findViewById(R.id.btn_cleanTable);
        btn_insertOne.setOnClickListener(this);
        btn_insertMore.setOnClickListener(this);
        btn_deleteByOne.setOnClickListener(this);
        btn_deleteByMore.setOnClickListener(this);
        btn_updateByOne.setOnClickListener(this);
        btn_updateByMore.setOnClickListener(this);
        btn_selectOne.setOnClickListener(this);
        btn_selectAll.setOnClickListener(this);
        btn_cleanTable.setOnClickListener(this);
//        recyclerView = (RecyclerView) findViewById(R.id.recycle);
//        //集合输出
//        List<Object>  list = new ArrayList<>();
//        Iterator<Object> iterator = list.iterator();
//        while(iterator.hasNext()){
//            System.out.print(iterator.next().toString());
//        }
//        //Map集合输出
//        Map<String,String> map = new HashMap<>();
//        Set<Map.Entry<String,String>> entrySet = map.entrySet();
//        Iterator<Map.Entry<String,String>> iterator1 = entrySet.iterator();
//        while(iterator1.hasNext()){
//            System.out.print(iterator1.next().toString());
//        }
    }


    @Override
    public void onClick(View view) {
       // btn_insertOne,btn_insertMore,btn_deleteByOne,btn_deleteByMore,btn_updateByOne,
         //       btn_updateByMore,btn_selectOne,btn_selectAll,btn_cleanTable;
        switch (view.getId()){
            case R.id.btn_insertOne://插入一条数据(可多次插入)
                Toast.makeText(this,"insert",Toast.LENGTH_SHORT).show();
                //弹出pop
                MyPopupWindow myPopupWindow = new MyPopupWindow(this);
                myPopupWindow.showAsDropDown(btn_insertOne);
            break;
            case R.id.btn_insertMore://插入多条数据（直接传入集合，此功能暂未开放）
                Toast.makeText(MainActivity.this, "此功能暂未开放", Toast.LENGTH_SHORT).show();
            break;
            case R.id.btn_deleteByOne://删除一条数据根据账户
                MyPopupWindow myPopupWindow3 = new MyPopupWindow(this,2);
                myPopupWindow3.showAsDropDown(btn_deleteByOne);
                break;
            case R.id.btn_deleteByMore://删除一条数据根据账户名和姓名
                MyPopupWindow myPopupWindow2 = new MyPopupWindow(this,3);
                myPopupWindow2.showAsDropDown(btn_deleteByMore);
                break;
            case R.id.btn_updateByOne://修改一条数据根据账户
                MyPopupWindow myPopupWindow4 = new MyPopupWindow(this,4);
                myPopupWindow4.showAsDropDown(btn_updateByOne);
                break;
            case R.id.btn_updateByMore://修改一条数据根据账户和姓名
                Toast.makeText(MainActivity.this, "此功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_selectOne://查询一条数据根据账户
                MyPopupWindow myPopupWindow5 = new MyPopupWindow(this,5);
                myPopupWindow5.showAsDropDown(btn_selectOne);
                break;
            case R.id.btn_selectAll://查询表中所有数据
                MyPopupWindow myPopupWindow1 = new MyPopupWindow(this,1);
                myPopupWindow1.showAsDropDown(btn_selectAll);
            break;
            case R.id.btn_cleanTable://清空表
                //弹出dialog询问是否清空表
                AlertDialog.Builder dialog = new AlertDialog.Builder(this,R.style.AppTheme);

                dialog.setCancelable(false)//点击外部不可取消
                .setMessage("是否要清空表？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                userModel.clearTable(UserModel.USER_TABLE_NAME);
                                dialogInterface.dismiss();
                            }
                        });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
            break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //这里写判断用来觉得是否向下传递


        return false;//在这里拦截  返回false继续向下传递，返回true不继续向下传递

    }
}
