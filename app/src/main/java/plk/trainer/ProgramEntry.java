package plk.trainer;

public class ProgramEntry {
    public int ExerciseId;
    public int Times;
    public int Repeats;

    ProgramEntry(int exercise_id, int times, int repeats)//times-подходы repeats-колчество раз
    {
        ExerciseId = exercise_id;
        Times = times;
        Repeats = repeats;
    }
}
