package com.blq.zzc.project.Bomb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blq.zzc.project.Bomb.Data.Person;
import com.blq.zzc.project.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class BombActivity extends AppCompatActivity {

    @Bind(R.id.button_add)
    Button mButtonAdd;
    @Bind(R.id.button_shan)
    Button mButtonShan;
    @Bind(R.id.button_edit)
    Button mButtonEdit;
    @Bind(R.id.button_chaxun)
    Button mButtonChaxun;
    private void initBomb() {
        //第一：默认初始化
        Bmob.initialize(this, "c83bf7287934c1852b9c56d4a2abb42f");
        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomb);
        ButterKnife.bind(this);
        initBomb();
        initView();
    }
    private void initView() {
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        mButtonShan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shanchuData();
            }
        });
        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xiugaiData();
            }
        });
        mButtonChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chaxunData();
            }
        });
    }
    private void addData() {
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Log.i("data", "添加数据成功，返回objectId为：" + objectId);
                } else {
                    Log.i("data", "创建数据失败：" + e.getMessage());
                }
            }
        });
    }
    private void shanchuData(){
        final Person p2 = new Person();
        p2.setObjectId("0d28e4c97d");
        p2.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("data", "删除成功:"+p2.getUpdatedAt());
                }else{
                    Log.i("data", "删除失败：" + e.getMessage());
                }
            }

        });
    }
    private void xiugaiData(){
        //更新Person表里面id为0d28e4c97d的数据，address内容更新为“北京朝阳”
        final Person p2 = new Person();
        p2.setAddress("北京朝阳");
        p2.update("0d28e4c97d", new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("data", "更新成功:"+p2.getUpdatedAt());
                }else{
                    Log.i("data", "更新失败：" + e.getMessage());
                }
            }

        });
    }
    private void chaxunData(){
        //查找Person表里面id为0d28e4c97d的数据
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("0d28e4c97d", new QueryListener<Person>() {
            @Override
            public void done(Person object,BmobException e) {
                if(e==null){
                    Log.i("data", "查询成功");
                }else{
                    Log.i("data", "查询失败：" + e.getMessage());
                }
            }
        });
    }
}
