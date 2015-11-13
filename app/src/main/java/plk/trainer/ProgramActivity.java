package plk.trainer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProgramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        Intent intent = getIntent();
        String pname = intent.getStringExtra("name");
        Bundle exercises = intent.getBundleExtra("exercises");
        int count = exercises.getInt("countdays");
        final ProgramsGroup[] p = new ProgramsGroup[count];
        for (int i = 1; i <= count; i++)
        {
            p[i-1] = new ProgramsGroup("День" + i, exercises.getStringArray(Integer.toString(i)));
        }

        setTitle(pname);

        final ExpandableListView view = (ExpandableListView)findViewById(R.id.programs_list_view);
        ProgramsAdapter adapter = new ProgramsAdapter(getBaseContext(), p);
        view.setAdapter(adapter);
        view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Toast.makeText(getBaseContext(), p[groupPosition].Values[childPosition], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        for (int i = 0; i < count; i++)
        {
            view.expandGroup(i, false);
        }
    }
}
