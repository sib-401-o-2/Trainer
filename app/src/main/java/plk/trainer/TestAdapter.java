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
        if (convertView == null)
        {
            convertView = new TestRowView(mContext);
        }

        TestRowView trv = (TestRowView) convertView;
        trv.SetQuestion(Storage.TestQuestions.get(position));

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
