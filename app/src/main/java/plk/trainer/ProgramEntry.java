package plk.trainer;

public class ProgramEntry {
    public int ExerciseId;
    public String Repeats;

    ProgramEntry(int exercise_id, String repeats)//times-подходы repeats-колчество раз
    {
        ExerciseId = exercise_id;
        Repeats = repeats;
    }
}
