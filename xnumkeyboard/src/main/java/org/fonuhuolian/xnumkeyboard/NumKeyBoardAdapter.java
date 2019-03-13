package org.fonuhuolian.xnumkeyboard;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class NumKeyBoardAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mList;
    private LayoutInflater mInflater;

    private XBtnStyle mStyle;
    private ViewGroup.LayoutParams params;

    public NumKeyBoardAdapter(Context context, List<Integer> list, XBtnStyle style) {
        this.mContext = context;
        this.mStyle = style;
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) mStyle.getBtnHeight());
        this.mList = list;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        int type = getItemViewType(position);

        ViewHolder0 viewHolder0 = null;
        ViewHolder1 viewHolder1 = null;
        ViewHolder2 viewHolder2 = null;

        if (convertView == null) {
            switch (type) {
                case 0:
                    viewHolder0 = new ViewHolder0();
                    convertView = mInflater.inflate(mStyle.isClickAnim() ? R.layout.x_kbd_view_item0 : R.layout.x_kbd_view_no_anim_item0, null);

                    viewHolder0.tv = (TextView) convertView.findViewById(R.id.x_kbd_num_tv);
                    viewHolder0.layout0 = (RelativeLayout) convertView.findViewById(R.id.x_kbd_layout0);

                    viewHolder0.layout0.setBackgroundColor(mStyle.getBtnColor());
                    viewHolder0.tv.setText(mList.get(position) + "");
                    viewHolder0.tv.setTextColor(mStyle.getBtnTextColor());
                    viewHolder0.tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mStyle.getBtnTextSize());

                    convertView.setTag(viewHolder0);
                    break;
                case 1:
                    viewHolder1 = new ViewHolder1();
                    convertView = mInflater.inflate(mStyle.isClickAnim() ? R.layout.x_kbd_view_item1 : R.layout.x_kbd_view_no_anim_item1, null);

                    viewHolder1.layout1 = (RelativeLayout) convertView.findViewById(R.id.x_kbd_layout1);

                    viewHolder1.layout1.setBackgroundColor(mStyle.getLeftBottomBtnColor());

                    convertView.setTag(viewHolder1);
                    break;
                case 2:
                    viewHolder2 = new ViewHolder2();
                    convertView = mInflater.inflate(mStyle.isClickAnim() ? R.layout.x_kbd_view_item2 : R.layout.x_kbd_view_no_anim_item2, null);

                    viewHolder2.layout2 = (RelativeLayout) convertView.findViewById(R.id.x_kbd_layout2);
                    viewHolder2.img = convertView.findViewById(R.id.x_kbd_img);

                    viewHolder2.layout2.setBackgroundColor(mStyle.getRightBottomBtnColor());
                    viewHolder2.img.setImageResource(mStyle.getRightBottomImg());

                    convertView.setTag(viewHolder2);
                    break;
            }

        } else {

            switch (type) {
                case 0:
                    viewHolder0 = (ViewHolder0) convertView.getTag();

                    viewHolder0.tv.setText(mList.get(position) + "");
                    viewHolder0.layout0.setBackgroundColor(mStyle.getBtnColor());
                    viewHolder0.tv.setTextColor(mStyle.getBtnTextColor());
                    viewHolder0.tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mStyle.getBtnTextSize());
                    break;
                case 1:
                    viewHolder1 = (ViewHolder1) convertView.getTag();

                    viewHolder1.layout1.setBackgroundColor(mStyle.getLeftBottomBtnColor());
                    break;
                case 2:
                    viewHolder2 = (ViewHolder2) convertView.getTag();

                    viewHolder2.layout2.setBackgroundColor(mStyle.getRightBottomBtnColor());
                    viewHolder2.img.setImageResource(mStyle.getRightBottomImg());
                    break;
            }

        }

        convertView.setLayoutParams(params);

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 9) {
            return 1;
        } else if (position == 11) {
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }


    public void clearAll() {
        mList.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder0 {
        RelativeLayout layout0;
        TextView tv;
    }

    static class ViewHolder1 {
        RelativeLayout layout1;
    }

    static class ViewHolder2 {
        RelativeLayout layout2;
        ImageView img;
    }
}
