package plk.trainer;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        if(Storage.Exercises == null)
        {
            Storage.Init(this);
        }

        final ListView view = (ListView)findViewById(R.id.test_list_view);

        final ListAdapter adapter = new TestAdapter(getBaseContext());
        view.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.test_activity_ok_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.test_activity_program_name);
                String t = tv.getText().toString();
                if (t.equals(""))
                {
                    Toast.makeText(TestActivity.this, "Введите название программы", Toast.LENGTH_SHORT).show();
                    return;
                }
                Resources re = getResources();
                InputStream res = re.openRawResource(R.raw.db_android);
                ++Storage.MaxId;
                Program p = EditXML.CreateCustomProgramBasedOnTest(res, t, new File(getBaseContext().getFilesDir(), "programs.xml"));
                Storage.Programs.put(Storage.MaxId, p);
                finish();
            }
        });
    }
}
