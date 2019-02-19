package pt.ismai.pedro.appbraintrainer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Random rand = new Random();
    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int score = 0;
    int numberOfQuestions = 0;
    int locationOfCorrectAnswer;
    TextView timer;

    public void startMethod (View view){

        startButton.setVisibility(view.INVISIBLE);

    }
    public void generateQuestions(){

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for(int i = 0 ; i < 4; i++){

            if(i == locationOfCorrectAnswer){

                answers.add(a + b);
            }else{

                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == (a+b)){

                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++;
            resultTextView.setText("Correct!");
        }else{

            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        generateQuestions();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.button);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.textView4);
        pointsTextView = findViewById(R.id.pointsTextView);
        button0 = findViewById(R.id.button1);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button3);
        button3 = findViewById(R.id.button4);
        timer = findViewById(R.id.timerTextView);

        generateQuestions();

        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText(String.valueOf( millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                timer.setText("0s");
                resultTextView.setText("Your Score " + Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
            }
        }.start();
    }
}
