package plk.trainer;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Enumeration;

public class ProgramsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        final ProgramsGroup[] p = new ProgramsGroup[2];
        Enumeration<Integer> k = Storage.Programs.keys();
        int cs = 0, cu = 0;
        while(k.hasMoreElements())
        {
            int kk = k.nextElement();
            if (kk < 0)
            {
                cs++;
            }
            else
            {
                cu++;
            }
        }
        String[] namess = new String[cs];
        String[] namesu = new String[cu];
        for (int j = 1; j <= cs; j++)
        {
            namess[j-1] = Storage.Programs.get(-j).Name;
        }
        for (int j = 0; j < cu; j++)
        {
            namess[j] = Storage.Programs.get(j).Name;
        }
        p[0] = new ProgramsGroup("Стандартные", namess);
        p[1] = new ProgramsGroup("Пользовательские", namesu);



        final ExpandableListView view = (ExpandableListView)findViewById(R.id.programs_list_view);
        ProgramsAdapter adapter = new ProgramsAdapter(getBaseContext(), p);
        view.setAdapter(adapter);
        view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0 )
                {
                    Intent intent = new Intent(getBaseContext(), ProgramActivity.class);
                    intent.putExtra("id", -(childPosition+1));
                    startActivity(intent);
                }
                if (groupPosition == 1)
                {
                    Intent intent = new Intent(getBaseContext(), ProgramActivity.class);
                    intent.putExtra("id", childPosition);
                    startActivity(intent);
                }
                return true;
            }
        });
    }
}
