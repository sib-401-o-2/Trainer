package plk.trainer;


public class Question {
    String Name;
    String[] Answers;
    int CurrentAnswer = 0;

    public Question(String name, String[] answers)
    {
        Name = name;
        Answers = answers;
    }
}
