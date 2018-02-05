package cn.gridlife.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aispeech.AIError;
import com.aispeech.common.AIConstant;
import com.aispeech.export.engines.AICloudTTSEngine;
import com.aispeech.export.listeners.AITTSListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn_send;
    private Button btn_paste;
    final String TAG = "Main";
    final String Tag = this.getClass().getName();
    private AICloudTTSEngine mEngine;
    private RecyclerView recyclerView;
    private ArrayList<String> datas;
    private RVAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    private String text;
//
//            //获取云端合成实例
//            AICloudTTSEngine mEngine = AICloudTTSEngine.createInstance();
//        //初始化云端合成实例
//        mEngine.init(new AITTSListener() {
//                //初始化完成的回调
//                @Override
//                public void onInit(int status) {
//                    Log.d(TAG, "onInit: ");
//                    if (status == AIConstant.OPT_SUCCESS) {
//                        Log.i(Tag, "初始化成功!");
//                    } else {
//                        Log.i(Tag, "初始化失败!code:" + status);
//                    }
//                }
//                //出现错误的回调
//                @Override
//                public void onError(String utteranceId, AIError error) {
//                    Log.d(TAG, "onError: "+utteranceId+","+error);
//                }
//                //合成音准备好可以播报的回调
//                @Override
//                public void onReady(String utteranceId) {
//                    Log.d(TAG, "onReady: "+utteranceId);
//                }
//                //播报完成的回调
//                @Override
//                public void onCompletion(String utteranceId) {
//                    Log.d(TAG, "onCompletion: "+utteranceId);
//                }
//                //当前播报进度的回调
//                @Override
//                public void onProgress(int currentTime, int totalTime, boolean isRefTextTTSFinished) {
//                    Log.d(TAG, "当前:" + currentTime + "ms, 总计:" + totalTime + "ms, 可信度:" + isRefTextTTSFinished);
//                }
//            });
//
//        //设置合成音的模板，默认为zhilingf  anonyf geyou xijunm
//        mEngine.setSpeaker("geyou");
//
//        //设置合成音保存的路径，默认不保存
//        mEngine.setAudioPath(Environment.getExternalStorageDirectory()+"/tts");
//
//        //设置合成音的语速，默认为1
//        mEngine.setSpeed("1");
//
//        //设置合成音的音量，默认50
//        mEngine.setVolume("50");
//
//        //设置要合成的文本并开始合成
//        mEngine.speak("今天天气怎么样");
//
//
//        //暂停当前合成音的播报
//        mEngine.pause();
//
//        //恢复合成音的播报
//        mEngine.resume();
//
//        //停止合成音的播报
//        mEngine.stop();
//
//        //释放引擎资源
//        mEngine.release();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClipboardManager cbm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        text = cbm.getText().toString();
        if (text != null) {
            initTTS();
            initView();
            initDialog(text);
        }
    }

    private void initDialog(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否读出如下内容")
                .setMessage(text)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                })

                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addData(text, datas.size());
                        mEngine.speak(text);
                    }
                }).setCancelable(false)
                .show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mEngine.stop();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_message);
        editText = (EditText) findViewById(R.id.et_content);
        btn_send = (Button) findViewById(R.id.btn_start);
        btn_paste = (Button) findViewById(R.id.btn_paste);
        btn_send.setOnClickListener(v -> {
                    if (editText.getText() != null && editText.getText().toString().trim().length() != 0) {
                        addData(editText.getText().toString(), datas.size());
                        mEngine.speak(editText.getText().toString());
                        editText.setText("");
                    }
                }
        );
        btn_paste.setOnClickListener(view -> {
            ClipboardManager cbm1 = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (cbm1 != null) {
            }
        });
        datas = new ArrayList<>();
        mLayoutManager = new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new RVAdapter(datas);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener((view, position) -> mEngine.speak(datas.get(position)));

//        if (cbm != null) {
//            String string=cbm.getText().toString();
//            editText.setText(string);

//        }
    }

    public void addData(String s, int position) {
        datas.add(position, s);
        mAdapter.notifyItemInserted(position);
    }

//    public void removeData(int position) {
//        mDatas.remove(position);
//        notifyItemRemoved(position);
//    }

    private void initTTS() {
        // 创建云端合成播放器
        mEngine = AICloudTTSEngine.createInstance();

        mEngine.init(new AITTSListener() {
            @Override
            public void onInit(int status) {
                Log.d(TAG, "onInit()");
                if (status == AIConstant.OPT_SUCCESS) {
                    Log.i(Tag, "初始化成功!");
                } else {
                    Log.i(Tag, "初始化失败!");
                }
            }

            @Override
            public void onError(String utteranceId, AIError error) {
                Log.d(TAG, "onError: " + utteranceId + "," + error);
            }

            @Override
            public void onReady(String utteranceId) {
                Log.d(TAG, "onReady: " + utteranceId);
            }

            @Override
            public void onCompletion(String utteranceId) {
//                tip.setText("合成完毕");
                Log.d(TAG, "onCompletion: " + utteranceId);
            }

            @Override
            public void onProgress(int currentTime, int totalTime, boolean isRefTextTTSFinished) {
                Log.d(TAG, "onProgress: " + currentTime);
//                showTip("当前:" + currentTime + "ms, 总计:" + totalTime + "ms, 可信度:" + isRefTextTTSFinished);
            }
        });
        ///////////////////////////////////////////////////////////////////////////
        // zhilingf
        // anonyf
        // geyou
        // xijunm
        ///////////////////////////////////////////////////////////////////////////
        mEngine.setSpeaker("zhilingf");
    }

}
