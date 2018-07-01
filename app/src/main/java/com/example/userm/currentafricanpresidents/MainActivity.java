package com.example.userm.currentafricanpresidents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int NUMBER_OF_QUESTIONS = 6;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the submit button is clicked.
     */
    public void submitQuiz(View view) {
        //This statement checks if any of the questions have been answered
        if (isAnyOfQuestionsOneToFourAnswered() || isQuestionFiveAnswered() ||
                isQuestionSixAnswered()) {
            //This second if statement checks if the user checked more than two
            // check boxes for question five, a warning is displayed in a toast message if this
            // is true and the submitQuiz method is exited.
            if (areThereMoreThanTwoAnswersToQuestionSix()) {
                Toast.makeText(this,
                        R.string.toast_when_more_than_two_answers_were_given_for_question_6,
                        Toast.LENGTH_SHORT).show();
                clearAnswers();
                return;
            }
            //If at least one question has been answered and more than two answers were not given
            // for question six, the submitQuiz method checks if the right answers were given by
            // calling the following methods:
            rightAnswersToQuestionsOneToFour();//Checks for right answers to question 1 to 4
            // and increments score by one for each right answer.
            rightAnswerToQuestionFive();//Checks for right answer to question 5 and increments
            // score by one if the right answer was given.
            rightAnswersToQuestionSix();//Checks for right answers to question six and increments
            // score by one if the right answers were were checked.
            Toast.makeText(this, R.string.number_of_correct_answers + score +
                            R.string.possible_number_of_score + NUMBER_OF_QUESTIONS,
                    Toast.LENGTH_SHORT).show();
            score = 0; //This resets the score to zero after displaying the toast message
            // in cases where at least one question was answered
            clearAnswers();
        } else {//This else statement is executed when no question was
            // answered and the user clicks the submit button
            Toast.makeText(this,
                    R.string.toast_when_a_user_tries_to_submit_without_at_least_supplying_an_answer,
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method checks if questions one to four has been answered.
     */
    private boolean isAnyOfQuestionsOneToFourAnswered() {
        RadioGroup radioGroupForQuestionOne = (RadioGroup) findViewById(R.id.radio_group_1);
        boolean isQuestionOneAnswered = (radioGroupForQuestionOne.getCheckedRadioButtonId() != -1);
        RadioGroup radioGroupForQuestionTwo = (RadioGroup) findViewById(R.id.radio_group_2);
        boolean isQuestionTwoAnswered = (radioGroupForQuestionTwo.getCheckedRadioButtonId() != -1);
        RadioGroup radioGroupForQuestionThree = (RadioGroup) findViewById(R.id.radio_group_3);
        boolean isQuestionThreeAnswered = (
                radioGroupForQuestionThree.getCheckedRadioButtonId() != -1);
        RadioGroup radioGroupForQuestionFour = (RadioGroup) findViewById(R.id.radio_group_4);
        boolean isQuestionFourAnswered = (
                radioGroupForQuestionFour.getCheckedRadioButtonId() != -1);
        if (isQuestionOneAnswered || isQuestionTwoAnswered || isQuestionThreeAnswered ||
                isQuestionFourAnswered) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if question five has been answered.
     */
    private boolean isQuestionFiveAnswered() {
        EditText nameView = (EditText) findViewById(R.id.question_5_answer_view);
        String answerToQuestionSix = nameView.getText().toString();
        if (answerToQuestionSix.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method checks if question six has been answered.
     */
    private boolean isQuestionSixAnswered() {
        CheckBox kenyaCheckbox = (CheckBox) findViewById(R.id.wrong_option_1_for_question_6);
        boolean isKenyaCheckbox = kenyaCheckbox.isChecked();
        CheckBox gabonCheckbox = (CheckBox) findViewById(R.id.wrong_option_2_for_question_6);
        boolean isGabonCheckbox = gabonCheckbox.isChecked();
        CheckBox liberiaCheckbox = (CheckBox) findViewById(R.id.first_correct_answer_to_question_6);
        boolean isLiberiaCheckbox = liberiaCheckbox.isChecked();
        CheckBox mauritiusCheckbox = (CheckBox) findViewById(
                R.id.second_correct_answer_to_question_6);
        boolean isMauritiusCheckbox = mauritiusCheckbox.isChecked();
        if (isKenyaCheckbox || isGabonCheckbox || isLiberiaCheckbox || isMauritiusCheckbox) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if more than two answers were given for question six.
     */
    private boolean areThereMoreThanTwoAnswersToQuestionSix() {
        CheckBox kenyaCheckbox = (CheckBox) findViewById(R.id.wrong_option_1_for_question_6);
        boolean isKenyaCheckbox = kenyaCheckbox.isChecked();
        CheckBox gabonCheckbox = (CheckBox) findViewById(R.id.wrong_option_2_for_question_6);
        boolean isGabonCheckbox = gabonCheckbox.isChecked();
        CheckBox liberiaCheckbox = (CheckBox) findViewById(R.id.first_correct_answer_to_question_6);
        boolean isLiberiaCheckbox = liberiaCheckbox.isChecked();
        CheckBox mauritiusCheckbox = (CheckBox) findViewById(
                R.id.second_correct_answer_to_question_6);
        boolean isMauritiusCheckbox = mauritiusCheckbox.isChecked();

        int numbersChecked = 0;
        if (isKenyaCheckbox) {
            numbersChecked++;
        }
        if (isGabonCheckbox) {
            numbersChecked++;
        }
        if (isLiberiaCheckbox) {
            numbersChecked++;
        }
        if (isMauritiusCheckbox) {
            numbersChecked++;
        }
        if (numbersChecked > 2)//Checks if the number of check boxes checked for question five is
            // greater than two
            return true;
        else {
            return false;
        }
    }

    /**
     * This method checks if the right answers were checked for questions one to four.
     */
    private void rightAnswersToQuestionsOneToFour() {
        RadioButton rightAnswerOne = (RadioButton) findViewById(R.id.correct_answer_to_question_1);
        RadioButton rightAnswerTwo = (RadioButton) findViewById(R.id.correct_answer_to_question_2);
        RadioButton rightAnswerThree = (RadioButton) findViewById(
                R.id.correct_answer_to_question_3);
        RadioButton rightAnswerFour = (RadioButton) findViewById(R.id.correct_answer_to_question_4);

        if (rightAnswerOne.isChecked()) {
            score++; //Increments score by one if the right answer to question one is checked
        }
        if (rightAnswerTwo.isChecked()) {
            score++; //Increments score by one if the right answer to question two is checked
        }
        if (rightAnswerThree.isChecked()) {
            score++; //Increments score by one if the right answer to question three is checked
        }
        if (rightAnswerFour.isChecked()) {
            score++; //Increments score by one if the right answer to question four is checked
        }
    }

    /**
     * This method checks if the right answer was was given for question 5.
     */
    private void rightAnswerToQuestionFive() {
        EditText nameView = (EditText) findViewById(R.id.question_5_answer_view);
        String answerToQuestionSix = nameView.getText().toString();
        if (answerToQuestionSix.equalsIgnoreCase(getString(
                R.string.full_name_of_the_president_of_south_africa))) {
            score++;//Increments score by one if the right answer to question five is given
        }

    }

    /**
     * This method checks if the right answers where checked for question 6.
     */
    private void rightAnswersToQuestionSix() {
        CheckBox liberiaCheckbox = (CheckBox) findViewById(R.id.first_correct_answer_to_question_6);
        boolean isLiberiaCheckbox = liberiaCheckbox.isChecked();
        CheckBox mauritiusCheckbox = (CheckBox) findViewById(
                R.id.second_correct_answer_to_question_6);
        boolean isMauritiusCheckbox = mauritiusCheckbox.isChecked();
        if (isLiberiaCheckbox && isMauritiusCheckbox) {
            score++;//Increments score by one if the right answers to question five are checked
        }
    }

    /**
     * This method clears all the answers given by the user.
     */
    private void clearAnswers() {
        //Clears answers to questions one to four
        RadioGroup radioGroupForQuestionOne = (RadioGroup) findViewById(R.id.radio_group_1);
        RadioGroup radioGroupForQuestionTwo = (RadioGroup) findViewById(R.id.radio_group_2);
        RadioGroup radioGroupForQuestionThree = (RadioGroup) findViewById(R.id.radio_group_3);
        RadioGroup radioGroupForQuestionFour = (RadioGroup) findViewById(R.id.radio_group_4);
        radioGroupForQuestionOne.clearCheck();
        radioGroupForQuestionTwo.clearCheck();
        radioGroupForQuestionThree.clearCheck();
        radioGroupForQuestionFour.clearCheck();

        //Clears answers to question five
        CheckBox kenyaCheckbox = (CheckBox) findViewById(R.id.wrong_option_1_for_question_6);
        CheckBox gabonCheckbox = (CheckBox) findViewById(R.id.wrong_option_2_for_question_6);
        CheckBox liberiaCheckbox = (CheckBox) findViewById(R.id.first_correct_answer_to_question_6);
        CheckBox mauritiusCheckbox = (CheckBox) findViewById(
                R.id.second_correct_answer_to_question_6);
        kenyaCheckbox.setChecked(false);
        gabonCheckbox.setChecked(false);
        liberiaCheckbox.setChecked(false);
        mauritiusCheckbox.setChecked(false);

        //Clears answer to question six
        EditText nameView = (EditText) findViewById(R.id.question_5_answer_view);
        nameView.setText("");
    }
}


