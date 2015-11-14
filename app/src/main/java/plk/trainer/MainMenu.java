package plk.trainer;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    String[] mMenuElements = {"Тест", "Программы", "Упражнения"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Storage.Init();

        final ListView view = (ListView)findViewById(R.id.main_menu_list_view);

        final ListAdapter adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, mMenuElements);
        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    Intent intent = new Intent(getBaseContext(), TestActivity.class);
                    startActivity(intent);
                }
                else if (position == 1)
                {
                    Intent intent = new Intent(getBaseContext(), ProgramsActivity.class);
                    startActivity(intent);
                }
                else if (position == 2)
                {
                    Intent intent = new Intent(getBaseContext(), ExercisesListActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getBaseContext(), (String)adapter.getItem(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
