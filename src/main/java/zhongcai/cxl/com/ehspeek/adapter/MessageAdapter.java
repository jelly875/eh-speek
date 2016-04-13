package zhongcai.cxl.com.ehspeek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import zhongcai.cxl.com.ehspeek.R;
import zhongcai.cxl.com.ehspeek.bean.ChatMessage;
import zhongcai.cxl.com.ehspeek.util.FormatDate;

/**
 * Created by chenjun on 16/4/12.
 */
public class MessageAdapter extends BaseAdapter{

    private static final int LEFTTAG = 0;
    private static final int RIGHTTAG = 1;
    private List<ChatMessage> chatMessages;
    private LayoutInflater inflater;

    public MessageAdapter(Context context,List<ChatMessage> chatMessages){
        this.chatMessages = chatMessages;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        if (chatMessages == null){
            return 0;
        }
        return chatMessages.size();
    }

    @Override
    public Object getItem(int position) {
        if (chatMessages == null){
            return null;
        }
        return chatMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = chatMessages.get(position);
        if (chatMessage.getType() == ChatMessage.Type.INCOMING){
            return LEFTTAG;
        }
        return RIGHTTAG;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage = chatMessages.get(position);
        ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            if (getItemViewType(position) == LEFTTAG){
                convertView = inflater.inflate(R.layout.left_item,parent,false);
                viewHolder.msg = (TextView)convertView.findViewById(R.id.left_msg);
                viewHolder.msgTime = (TextView)convertView.findViewById(R.id.left_msg_time);
            }else if(getItemViewType(position) == RIGHTTAG){
                convertView = inflater.inflate(R.layout.right_item,parent,false);
                viewHolder.msg = (TextView)convertView.findViewById(R.id.right_msg);
                viewHolder.msgTime = (TextView)convertView.findViewById(R.id.right_msg_time);
            }
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.msgTime.setText(FormatDate.getDateStr(chatMessage.getDate()));
        viewHolder.msg.setText(chatMessage.getMessage());
        return convertView;
    }

    private final class ViewHolder{
        TextView msgTime;
        TextView msg;
    }
}
