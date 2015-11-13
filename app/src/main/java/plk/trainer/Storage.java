package plk.trainer;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Storage {
    public static Dictionary<Integer, Exercise> Exercises;
    public static Dictionary<Integer, Program> Programs;

    public Storage()
    {
        Exercises = new Hashtable<>();
        Programs = new Hashtable<>();

        Exercises.put(0, new Exercise("Жим лежа", "Лечь на спину и жать"));
        Exercises.put(1, new Exercise("Становая тяга", "тяни"));
        Exercises.put(2, new Exercise("Присяд", "приседай"));
        Exercises.put(3, new Exercise("Армейский жим", "жми вверх"));
        Exercises.put(4, new Exercise("Тяга штанги к подбородку", "тяни"));
        Exercises.put(5, new Exercise("Французский жим", "за голову лежа"));

        int[][] s1 = {
                {
                        0
                },
                {
                        1
                },
                {
                        2
                }
            };
        int[][] s2 = {
                {
                        3
                },
                {
                        4
                },
                {
                        5
                },
        };
        Programs.put(-1, new Program("Стандарт 1", s1));
        Programs.put(-2, new Program("Стандарт 2", s2));
    }
}
