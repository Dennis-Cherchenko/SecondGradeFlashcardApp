package dennischerchenko.com.secondgradeflashcardapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.reflect.Array;

import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    private static int[] numbersArray = new int[20];
    private static int[] answersArray = new int[10];
    private static int problemsCompleted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnGenerateProblems = (Button) findViewById(R.id.btnGenerateProblems);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnGenerateProblems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOperationSelected() == true){
                    setupNewTest();
                }else{
                    Toast.makeText(getApplicationContext(), "Please select whether you want to do addition or subtraction", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtAnswer = (TextView) findViewById(R.id.txtAnswer);

                if (isInSession() == true) {
                    if (txtAnswer.getText() == null || txtAnswer.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                    } else {

                        answersArray[problemsCompleted] = Integer.parseInt(txtAnswer.getText().toString());

                        problemsCompleted++;
                        nextProblem();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please press 'Generate 10 Problems'", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void nextProblem(){
        TextView txtAnswer = (TextView) findViewById(R.id.txtAnswer);
        TextView lblFirstNumber = (TextView) findViewById(R.id.lblFirstNumber);
        TextView lblSecondNumber = (TextView) findViewById(R.id.lblSecondNumber);

        if(problemsCompleted < 10){
            lblFirstNumber.setText(Integer.toString(numbersArray[problemsCompleted * 2]));
            lblSecondNumber.setText(Integer.toString(numbersArray[(problemsCompleted * 2) + 1]));
        }else{
            testCompleted();
        }

        txtAnswer.setText("");
    }

    private void testCompleted(){
        RadioButton rbAddition = (RadioButton) findViewById(R.id.rbAddition);
        RadioButton rbSubtraction = (RadioButton) findViewById(R.id.rbSubtraction);

        Toast.makeText(getApplicationContext(), "You got " + calculateScore() + " our of 10  problems correct. Press 'Generate 10 Random Problems' to start new test", Toast.LENGTH_SHORT).show();
        rbAddition.setChecked(false);
        rbSubtraction.setChecked(false);
        uncheckOperations();
        problemsCompleted = 0;
        setupNewTest();
    }

    private void setupNewTest(){
        TextView txtAnswer = (TextView) findViewById(R.id.txtAnswer);
        txtAnswer.setText("");

        if(isInSession() == false){
            Toast.makeText(getApplicationContext(), "Select whether you want to do Addition of Subtraction", Toast.LENGTH_SHORT).show();
        }else{
            for(int i = 0; i < 20; i++){
                numbersArray[i] = (int) (Math.round(Math.random() * 100));
            }

            for(int i = 0; i < 10; i++){
                answersArray[i] = 0;
            }

            nextProblem();
        }
    }

    private String calculateScore(){

        int countCorrect = 0;

        if(getOperation().equals("+")){

            for(int i = 0; i < 10; i++){
                if(numbersArray[2*i] + numbersArray[2*i+1] == answersArray[i]){
                    countCorrect++;
                }
            }
        }else{
            for(int i = 0; i < 10; i++){
                if(numbersArray[2*i] - numbersArray[2*i+1] == answersArray[i]){
                    countCorrect++;
                }
            }
        }


        return Integer.toString(countCorrect);
    }

    private boolean isInSession(){
        return isOperationSelected();
    }

    private void uncheckOperations(){
        RadioButton rbAddition = (RadioButton) findViewById(R.id.rbAddition);
        RadioButton rbSubtraction = (RadioButton) findViewById(R.id.rbSubtraction);

        rbAddition.setActivated(false);
        rbSubtraction.setActivated(false);
    }

    private boolean isOperationSelected(){
        RadioButton rbAddition = (RadioButton) findViewById(R.id.rbAddition);
        RadioButton rbSubtraction = (RadioButton) findViewById(R.id.rbSubtraction);

        return rbAddition.isChecked() || rbSubtraction.isChecked();
    }

    private String getOperation(){
        RadioButton rbAddition = (RadioButton) findViewById(R.id.rbAddition);
        RadioButton rbSubtraction = (RadioButton) findViewById(R.id.rbSubtraction);

        if(rbAddition.isChecked() == true){
            return "+";
        }else if(rbSubtraction.isChecked() == true){
            return "-";
        }else{
            return "";
        }
    }
}
