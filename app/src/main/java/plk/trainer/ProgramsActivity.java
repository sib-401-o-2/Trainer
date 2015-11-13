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

public class ProgramsActivity extends AppCompatActivity {

    String[][] mNames = {{"Стандарт 1", "Стандарт 2"}, {"что-то там"}};
    ProgramsGroup[] mPrograms = {new ProgramsGroup("Стандартные", mNames[0]), new ProgramsGroup("Пользовательские", mNames[1])};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        mPrograms[0] = new ProgramsGroup("Стандартные", mNames[0]);

        final ExpandableListView view = (ExpandableListView)findViewById(R.id.programs_list_view);
        ProgramsAdapter adapter = new ProgramsAdapter(getBaseContext(), mPrograms);
        view.setAdapter(adapter);
        view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0 && childPosition == 0)
                {
                    String[][] vals = {{"Жим лежа"}, {"Становая тяга"}, {"Присяд"}};
                    Bundle b = new Bundle();
                    b.putInt("countdays", vals.length);
                    for (int i = 0; i < vals.length; i++)
                    {
                        b.putStringArray(Integer.toString(i+1), vals[i]);
                    }
                    Intent intent = new Intent(getBaseContext(), ProgramActivity.class);
                    intent.putExtra("exercises", b);
                    intent.putExtra("name", mPrograms[groupPosition].Values[childPosition]);
                    startActivity(intent);
                }
                if (groupPosition == 0 && childPosition == 1)
                {
                    String[][] vals = {{"Армейский жим"}, {"Тяга штанги к подбородку"}, {"Французкий жим"}};
                    Bundle b = new Bundle();
                    b.putInt("countdays", vals.length);
                    for (int i = 0; i < vals.length; i++)
                    {
                        b.putStringArray(Integer.toString(i+1), vals[i]);
                    }
                    Intent intent = new Intent(getBaseContext(), ProgramActivity.class);
                    intent.putExtra("exercises", b);
                    intent.putExtra("name", mPrograms[groupPosition].Values[childPosition]);
                    startActivity(intent);
                }
                if (groupPosition == 1)
                {
                    Toast.makeText(getBaseContext(), "Не тыкай сюда", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
