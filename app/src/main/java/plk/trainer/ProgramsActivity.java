package plk.trainer;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ProgramsActivity extends AppCompatActivity {

    final List<Integer> custProgKeys = new ArrayList<Integer>();

    ExpandableListView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        if(Storage.Exercises == null)
        {
            Storage.Init(this);
        }

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
                custProgKeys.add(kk);
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
            namesu[j] = Storage.Programs.get(custProgKeys.get(j)).Name;
        }

        p[0] = new ProgramsGroup("Стандартные", namess);
        p[1] = new ProgramsGroup("Пользовательские", namesu);

        view = (ExpandableListView)findViewById(R.id.programs_list_view);
        ProgramsAdapter adapter = new ProgramsAdapter(getBaseContext(), p);
        view.setAdapter(adapter);
        view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0) {
                    Intent intent = new Intent(getBaseContext(), ProgramActivity.class);
                    intent.putExtra("id", -(childPosition + 1));
                    startActivity(intent);
                }
                if (groupPosition == 1) {
                    Intent intent = new Intent(getBaseContext(), ProgramActivity.class);
                    intent.putExtra("id", custProgKeys.get(childPosition));
                    startActivity(intent);
                }
                return true;
            }
        });

        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    final int groupPosition = ExpandableListView.getPackedPositionGroup(id);
                    final int childPosition = ExpandableListView.getPackedPositionChild(id);
                    if(groupPosition == 1)
                    {

                        AlertDialog dialog = createDialog(childPosition, v);
                        dialog.show();
                    }
                    return true;
                }

                return false;
            }
        });
    }

    AlertDialog createDialog(final int position, final View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Запрос");
        builder.setMessage("Удалить программу?");
        builder.setCancelable(true);
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id = custProgKeys.get(position);
                Storage.Programs.remove(custProgKeys.get(position));
                EditXML.deleteElement(id, new File(getBaseContext().getFilesDir(), "programs.xml"));
                custProgKeys.remove(position);
                setAdapter();

            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
        }
        });
        AlertDialog dialog = builder.create();
        return dialog;
    }

    void setAdapter()
    {
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
                custProgKeys.add(kk);
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
            namesu[j] = Storage.Programs.get(custProgKeys.get(j)).Name;
        }

        p[0] = new ProgramsGroup("Стандартные", namess);
        p[1] = new ProgramsGroup("Пользовательские", namesu);

        ProgramsAdapter adapter = new ProgramsAdapter(getBaseContext(), p);
        view.setAdapter(adapter);

    }

}
