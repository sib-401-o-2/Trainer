package plk.trainer;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Storage {
    public static Dictionary<Integer, Exercise> Exercises;
    public static Dictionary<Integer, Program> Programs;
    public static Dictionary<Integer, Question> TestQuestions;

    public static void Init()
    {
        Exercises = new Hashtable<>();
        Programs = new Hashtable<>();
        TestQuestions = new Hashtable<>();

        Exercises.put(0, new Exercise("Жим лежа", "Лечь на спину и жать"));
        Exercises.put(1, new Exercise("Становая тяга", "тяни"));
        Exercises.put(2, new Exercise("Присяд", "приседай"));
        Exercises.put(3, new Exercise("Армейский жим", "жми вверх"));
        Exercises.put(4, new Exercise("Тяга штанги к подбородку", "тяни"));
        Exercises.put(5, new Exercise("Французский жим", "за голову лежа"));

        ProgramEntry[][] s1 = {
                {
                        new ProgramEntry(0, 4, 12)
                },
                {
                        new ProgramEntry(1, 2, 10),
                        new ProgramEntry(1, 1, 8),
                        new ProgramEntry(1, 1, 6)
                },
                {
                        new ProgramEntry(2, 4, 12)
                }
            };
        ProgramEntry[][] s2 = {
                {
                        new ProgramEntry(3, 4, 12)
                },
                {
                        new ProgramEntry(4, 4, 12)
                },
                {
                        new ProgramEntry(5, 4, 12)
                },
        };
        Programs.put(-1, new Program("Стандарт 1", s1));
        Programs.put(-2, new Program("Стандарт 2", s2));

        String[] ans = {"Да", "Нет", "Наверное"};

        TestQuestions.put(0, new Question("азаза?", ans));
        TestQuestions.put(1, new Question("азаза2?", ans));
        TestQuestions.put(2, new Question("азаза3?", ans));
    }
}
