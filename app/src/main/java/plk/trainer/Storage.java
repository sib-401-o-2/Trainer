package plk.trainer;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import java.io.File;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Storage {
    public static Dictionary<Integer, Exercise> Exercises;
    public static Dictionary<Integer, Program> Programs;
    public static Dictionary<Integer, Question> TestQuestions;
    public static int MaxId = 0;

    public static void Init(Context context)
    {
        Resources re = context.getResources();
        InputStream res = re.openRawResource(R.raw.db_android);
        Exercises = EditXML.parseExercises(res);
        res = re.openRawResource(R.raw.db_android);
        Programs = EditXML.parsePrograms(res);
        res = re.openRawResource(R.raw.db_android);
        TestQuestions = EditXML.parseQuestions(res);
    }
}
