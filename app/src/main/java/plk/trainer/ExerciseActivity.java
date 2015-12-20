package plk.trainer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", -1);
        setTitle(Storage.Exercises.get(id).Name);
        TextView view = (TextView)findViewById(R.id.exercise_text);
        view.setText(Storage.Exercises.get(id).Description.replace("\\n", "\n"));

        Button b = (Button)findViewById(R.id.ex_woy);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Storage.Exercises.get(id).VideoLink.equals("")) return;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Storage.Exercises.get(id).VideoLink));
                startActivity(i);
            }
        });

        ImageView iv = (ImageView)findViewById(R.id.exercise_image);
        iv.setImageResource(getRes(Storage.Exercises.get(id).Image, "drawable"));
    }

    int getRes(String f, String p) {
        return getResources().getIdentifier(f, p, getPackageName());
    }
}
