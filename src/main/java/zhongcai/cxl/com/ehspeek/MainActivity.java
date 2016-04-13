package zhongcai.cxl.com.ehspeek;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zhongcai.cxl.com.ehspeek.adapter.MessageAdapter;
import zhongcai.cxl.com.ehspeek.bean.ChatMessage;
import zhongcai.cxl.com.ehspeek.http.HttpEngine;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MessageAdapter mAdapter;
    private ImageButton sendButton;
    private EditText sendMsg;
    private ListView msgList;
    private List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ChatMessage chatMessage = (ChatMessage)msg.obj;
            chatMessages.add(chatMessage);
            mAdapter.notifyDataSetChanged();
            msgList.setSelection(chatMessages.size()-1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        findViewById();
        initView();
    }

    private void findViewById(){
        sendButton = (ImageButton)findViewById(R.id.send_button);
        sendMsg = (EditText)findViewById(R.id.send_msg);
        msgList = (ListView)findViewById(R.id.msg_list);
    }

    private void initView(){
        initData();
        sendButton.setOnClickListener(this);

        mAdapter = new MessageAdapter(this,chatMessages);
        msgList.setAdapter(mAdapter);
    }

    private void initData(){
        ChatMessage initData = new ChatMessage();
        initData.setType(ChatMessage.Type.INCOMING);
        initData.setDate(new Date());
        initData.setMessage("二哈，你好啊^_^");
        chatMessages.add(initData);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_button:{
                final String msg = sendMsg.getText().toString();
                if(TextUtils.isEmpty(msg)){
                    return;
                }
                ChatMessage chatMessage1 = new ChatMessage();
                chatMessage1.setDate(new Date());
                chatMessage1.setMessage(msg);
                chatMessage1.setType(ChatMessage.Type.OUTCOMING);
                chatMessages.add(chatMessage1);
                mAdapter.notifyDataSetChanged();
                msgList.setSelection(chatMessages.size() - 1);
                sendMsg.setText("");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ChatMessage chatMessage2 = HttpEngine.sendMessage(msg);
                        Message message = new Message();
                        message.obj=chatMessage2;
                        mHandler.sendMessage(message);
                    }
                }).start();
                break;
            }
            default : break;
        }
    }
}
