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

    public static void Init(Context context)
    {
        TestQuestions = new Hashtable<>();

        Resources re = context.getResources();
        InputStream res = re.openRawResource(R.raw.db_android);
        Exercises = EditXML.parseExercises(res);
        res = re.openRawResource(R.raw.db_android);
        Programs = EditXML.parsePrograms(res);


        String[] ans = {"Да", "Нет", "Наверное"};

        TestQuestions.put(0, new Question("азаза?", ans));
        TestQuestions.put(1, new Question("азаза2?", ans));
        TestQuestions.put(2, new Question("азаза3?", ans));
    }
}
