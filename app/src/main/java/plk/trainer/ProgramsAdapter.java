package plk.trainer;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

public class ProgramsAdapter extends BaseExpandableListAdapter {

    Context mContext;
    ProgramsGroup[] mPrograms;

    public ProgramsAdapter(Context context, ProgramsGroup[] programs)
    {
        mContext = context;
        mPrograms = programs;
    }

    @Override
    public int getGroupCount() {
        return mPrograms.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mPrograms[groupPosition].Values.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mPrograms[groupPosition].Values;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mPrograms[groupPosition].Values[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater inf = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView v = (TextView) convertView;
        v.setText("\t" + mPrograms[groupPosition].Name);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater inf = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView v = (TextView) convertView;
        v.setText(mPrograms[groupPosition].Values[childPosition]);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
