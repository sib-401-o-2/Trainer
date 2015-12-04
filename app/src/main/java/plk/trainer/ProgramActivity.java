package plk.trainer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        final int id = intent.getIntExtra("id", -1);
        int count = Storage.Programs.get(id).Exercises.length;
        final ProgramsGroup[] p = new ProgramsGroup[count];
        for (int i = 1; i <= count; i++)
        {
            int c = Storage.Programs.get(id).Exercises[i-1].length;
            String[] names = new String[c];
            for (int j = 0; j < c; j++)
            {

                names[j] = Storage.Exercises.get(Storage.Programs.get(id).Exercises[i-1][j].ExerciseId).Name + "\n" +
                        Storage.Programs.get(id).Exercises[i-1][j].Times + "/" +
                        Storage.Programs.get(id).Exercises[i-1][j].Repeats;
            }
            p[i-1] = new ProgramsGroup("День" + i, names);
        }

        setTitle(Storage.Programs.get(id).Name);

        final ExpandableListView view = (ExpandableListView)findViewById(R.id.programs_list_view);
        ProgramAdapter adapter = new ProgramAdapter(getBaseContext(), p, id);
        view.setAdapter(adapter);
        view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id1) {
                Intent intent = new Intent(getBaseContext(), ExerciseActivity.class);
                intent.putExtra("id", Storage.Programs.get(id).Exercises[groupPosition][childPosition].ExerciseId);
                startActivity(intent);
                return true;
            }
        });
        for (int i = 0; i < count; i++)
        {
            view.expandGroup(i, false);
        }
    }
}
