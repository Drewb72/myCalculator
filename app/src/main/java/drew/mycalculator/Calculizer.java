package drew.mycalculator;

public class Calculizer {

    private String action1 = "";
    private String action2 = "";
    private String action;
    private String numA = "";
    private String numB = "";
    private float intA;
    private float intB;
    private float answer;
    private boolean numAFin;
    private boolean numBFin;
    private boolean opFin;
    private boolean numAString = false;
    private boolean numBString = false;
    private boolean opString;
    private boolean act1Fin;

    public void passStuff(String operator, String num1){
        action1 = operator;
        numA = num1;
    }

    public void clear(){
        numA = "";
        numB = "";
        action1 = "";
        action2 = "";
        numAString = false;
        numBString = false;
    }

    public void opInput(String answerGiven, boolean opComp) {
        if ("-/*+".contains(answerGiven)){
            opString = true;
        }else{
            opString = false;
        }

        if (opComp){
            action2 = answerGiven;
        }else{
            action1 = answerGiven;
        }
    }

    public void numAInput (String answerGiven) {
        if ("0123456789".contains(answerGiven)) {
            numAString = true;
            numAFin = false;
        }else{
            numAString = false;
        }

        if (numAString && numA.length() < 9) {
                numA = numA.concat(answerGiven);
                intA = Float.parseFloat(numA);
        }

        if (numA.length() == 9) {
                numAFin = true;
        }
    }

    public void numBInput(String answerGiven) {
        if ("0123456789".contains(answerGiven)) {
            opFin = true;
            numBString = true;
        }else{
            numBString = false;
        }

        if (numBString && numB.length() < 9) {
            numB = numB.concat(answerGiven);
            intB = Float.parseFloat(numB);
        }

        if (numB.length() == 9) {
            numBFin = true;
        }
    }

    public void resetAction(){
        action1 = action1.replace(action1, action2);
    }

    public void resetAnswer() {
        if (Math.round(answer) == answer) {
            answer = (int) answer;
            numA = Integer.toString((int)answer);
        } else {
            numA = Float.toString(answer);
        }
    }

    public void doMath() {
        intA = Float.parseFloat(numA);
        intB = Float.parseFloat(numB);

        if (action1 == "/") {
            answer = intA / intB;
        }
        if (action1 == "*") {
            answer = intA * intB;
        }
        if (action1 == "-") {
            answer = intA - intB;
        }
        if (action1 == "+") {
            answer = intA + intB;
        }

    }

    public float getIntA() {
        return intA;
    }
    public float getIntB() {
        return intB;
    }
    public String getNumA () {
        return numA;
    }
    public String getNumB() {
        return numB;
    }
    public boolean getNumAFin(){return numAFin; }
    public boolean getNumBFin(){return numBFin; }
    public boolean getopFin(){return opFin; }
    public boolean getopString(){return opString; }
    public String getAction1(){
        return action1;
    }
    public String getAction2(){
        return action2;
    }
    public boolean getNumAString(){return numAString;}
    public boolean getNumBString(){return numBString;}
}