package plk.trainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        setTitle(Storage.Exercises.get(id).Name);

        TextView view = (TextView)findViewById(R.id.exercise_text);
        view.setText(Storage.Exercises.get(id).Description);
    }
}
