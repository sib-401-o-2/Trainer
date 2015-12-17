package plk.trainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TestRowView extends LinearLayout {
    Context mContext;
    int mCount = 4, mSelected = 0;
    RadioButton[] mRadioButtons = {null,null,null,null};
    LinearLayout mBaseLayout;
    Question mCurrentQuestion;

    public TestRowView(Context context) {
        super(context);

        mContext = context;
        LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mBaseLayout = (LinearLayout)inf.inflate(R.layout.test_question_layout, this);

        RadioGroup r = (RadioGroup) mBaseLayout.findViewById(R.id.test_question_rg);
        for (int i = 0; i < 4; i++) {
            inf.inflate(R.layout.test_question_rb_layout, r);
            RadioButton rb = (RadioButton) r.findViewById(R.id.test_question_rb);
            rb.setId(i);
            rb.setVisibility(GONE);
            final int finalI = i;
            rb.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelected = finalI;
                    if (mCurrentQuestion != null)
                    {
                        mCurrentQuestion.CurrentAnswer = finalI;
                    }
                }
            });
            mRadioButtons[i] = rb;
        }
    }

    public void SetQuestion(Question question)
    {
        mCurrentQuestion = question;
        mCount = question.Answers.length;
        mSelected = 0;

        for (int i = 0; i < 4; i++)
        {
            if (i < mCount)
            {
                mRadioButtons[i].setVisibility(VISIBLE);
                mRadioButtons[i].setText(question.Answers[i]);
            }
            else
            {
                mRadioButtons[i].setVisibility(GONE);
            }
        }

        mRadioButtons[question.CurrentAnswer].setChecked(true);

        TextView v = (TextView)mBaseLayout.findViewById(R.id.test_question_name);
        v.setText(question.Name);
    }
}
