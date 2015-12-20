package plk.trainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ExercisesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        if(Storage.Exercises.size() == 0)
        {
            Storage.Init(this);
        }

        setTitle("Список упражнений");

        int count = Storage.Exercises.size();
        final String[] s = new String[count];
        for (int i = 0; i < count; i++)
        {
            s[i] = Storage.Exercises.get(i).Name;
        }

        final ListView view = (ListView)findViewById(R.id.main_menu_list_view);

        final ListAdapter adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, s);
        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), ExerciseActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }
}
