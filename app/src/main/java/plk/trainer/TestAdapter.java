package plk.trainer;


import android.app.ActionBar;
import android.content.Context;
import android.database.DataSetObserver;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TestAdapter implements ListAdapter
{

    Context mContext;

    public TestAdapter(Context context)
    {
        mContext = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return Storage.TestQuestions.size();
    }

    @Override
    public Object getItem(int position) {
        return Storage.TestQuestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            //LayoutInflater inf = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = inf.inflate(R.layout.test_question_layout, null);
        }

        TextView v = (TextView)convertView.findViewById(R.id.test_question_name);
        v.setText(Storage.TestQuestions.get(position).Name);

        RadioGroup r = (RadioGroup) convertView.findViewById(R.id.test_question_rg);
        for (int i = 0; i < Storage.TestQuestions.size(); i++) {
            inf.inflate(R.layout.test_question_rb_layout, r);
            RadioButton rb = (RadioButton) r.findViewById(R.id.test_question_rb);
            rb.setText(Storage.TestQuestions.get(position).Answers[i]);
            rb.setId(i);
            //convertView.setTag(i, rb);
            if (i == 0) {
                rb.setChecked(true);
            }
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.test_question_layout;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
