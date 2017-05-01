package drew.mycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String answerGiven = "";
    Calculizer Calc = new Calculizer();
    String prevOp;
    String prevAnswer;
    boolean takeInput = true;
    boolean numAComplete = false;
    boolean op1Complete = false;
    boolean numBComplete = false;
    boolean op2Complete = false;
    boolean op2CompleteEqu = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) (findViewById(R.id.button));
        Button b2 = (Button) (findViewById(R.id.button2));
        Button b3 = (Button) (findViewById(R.id.button3));
        Button b4 = (Button) (findViewById(R.id.button4));
        Button b5 = (Button) (findViewById(R.id.button5));
        Button b6 = (Button) (findViewById(R.id.button6));
        Button b7 = (Button) (findViewById(R.id.button7));
        Button b8 = (Button) (findViewById(R.id.button8));
        Button b9 = (Button) (findViewById(R.id.button9));
        Button b0 = (Button) (findViewById(R.id.button0));
        Button bDiv = (Button) (findViewById(R.id.button10));
        Button bMult = (Button) (findViewById(R.id.button11));
        Button bMin = (Button) (findViewById(R.id.button12));
        Button bPlus = (Button) (findViewById(R.id.button13));
        Button bEqual = (Button) (findViewById(R.id.button14));
        Button bClear = (Button) (findViewById(R.id.button15));
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        bDiv.setOnClickListener(this);
        bMult.setOnClickListener(this);
        bMin.setOnClickListener(this);
        bPlus.setOnClickListener(this);
        bEqual.setOnClickListener(this);
        bClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                answerGiven = "1";
                updateNum();
                break;
            case R.id.button2:
                answerGiven = "2";
                updateNum();
                break;
            case R.id.button3:
                answerGiven = "3";
                updateNum();
                break;
            case R.id.button4:
                answerGiven = "4";
                updateNum();
                break;
            case R.id.button5:
                answerGiven = "5";
                updateNum();
                break;
            case R.id.button6:
                answerGiven = "6";
                updateNum();
                break;
            case R.id.button7:
                answerGiven = "7";
                updateNum();
                break;
            case R.id.button8:
                answerGiven = "8";
                updateNum();
                break;
            case R.id.button9:
                answerGiven = "9";
                updateNum();
                break;
            case R.id.button0:
                answerGiven = "0";
                updateNum();
                break;
            case R.id.button10:
                answerGiven = "/"; //Division
                updateOp();
                setAnswer();
                break;
            case R.id.button11:
                answerGiven = "*"; //Multiplication
                updateOp();
                setAnswer();
                break;
            case R.id.button12:
                answerGiven = "-"; //Subtraction
                updateOp();
                setAnswer();
                break;
            case R.id.button13:
                answerGiven = "+"; //Addition
                updateOp();
                setAnswer();
                break;
            case R.id.button14:
                answerGiven = "="; //Equals
                updateOp();
                setAnswer();
                break;
            case R.id.button15:
                answerGiven = "c"; // Clear
                clear();
            default:
                answerGiven = "";
                break;
        }
    }

    public void updateNum() {
        takeInput = true;
        TextView display = (TextView) (findViewById(R.id.textView));

        if (takeInput) {

            if (!numAComplete) {
                Calc.numAInput(answerGiven);
                display.setText(Calc.getNumA());
            }

            if (numAComplete && op1Complete) {
                Calc.numBInput(answerGiven);
                display.setText(Calc.getNumB());
                numBComplete = Calc.getNumBString();
            }

            if (Calc.getNumAFin() || Calc.getNumBFin()) {
                takeInput = false;
            }
        }
    }

    public void updateOp() {
        TextView display = (TextView) (findViewById(R.id.textView));
        TextView opDisp = (TextView) (findViewById(R.id.textView2));
        numAComplete = Calc.getNumAString();

        if (numAComplete && !op1Complete) {
            Calc.opInput(answerGiven, false);
            display.setText(Calc.getAction1());
            Log.i("poopy", "numAComp," + Calc.getAction1());
            op1Complete = true;
            if (Calc.getopString()) {
                opDisp.setText(Calc.getAction1());
            }
        }

        if (numBComplete && op1Complete) {
            Log.i("poopy", "numBComp");
            Calc.opInput(answerGiven, true);
            if (Calc.getopString()) {
                opDisp.setText(Calc.getAction2());
                Log.i("poopy", "numBComp1" + Calc.getAction1());
                op2Complete = true;
            }else{
                op2CompleteEqu = true;
            }
        }
    }

    public void setAnswer(){
        TextView display = (TextView) (findViewById(R.id.textView));

        if (op2Complete){
            Log.i("poopy", "op2Comp");
            Calc.doMath();
            Calc.resetAction();
            Calc.resetAnswer();
            numAComplete = true;
            op1Complete = true;
            op2Complete = false;
            numBComplete = false;
            prevAnswer = Calc.getNumA();
            prevOp = Calc.getAction2();
            Calc = new Calculizer();
            Calc.passStuff(prevOp, prevAnswer);
            display.setText(prevAnswer);
        }

        if (op2CompleteEqu){
            Log.i("poopy", "op2CompEqu");
            Calc.doMath();
            Calc.resetAction();
            Calc.resetAnswer();
            display.setText(Calc.getNumA());
            takeInput = true;
            numAComplete = false;
            op1Complete = false;
            numBComplete = false;
            op2Complete = false;
            op2CompleteEqu = false;
            Calc = new Calculizer();
        }
    }

    public void clear(){
        TextView display = (TextView) (findViewById(R.id.textView));
        TextView opDisp = (TextView) (findViewById(R.id.textView2));
        Calc.clear();
        display.setText(Calc.getNumA());
        opDisp.setText(Calc.getAction1());
        takeInput = true;
        numAComplete = false;
        op1Complete = false;
        numBComplete = false;
        op2Complete = false;
        op2CompleteEqu = false;
        Calc = new Calculizer();
    }
}

