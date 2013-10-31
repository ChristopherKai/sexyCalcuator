package com.example.sexycalculator;

import java.util.Stack;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private StringBuilder userInput;
	private TextView userInputString;
	private TextView resultView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		userInput = new StringBuilder();
		userInputString = (TextView) findViewById(R.id.userInputString);
		resultView = (TextView) findViewById(R.id.resultView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void showUserInput()
	{
		String showString = userInput.toString();
		if (showString == "") showString = "0";
		userInputString.setText(showString);
		
	}
	private void showResult()
	{
		
		try
		{
			double result = Parse(userInput.toString());
			resultView.setText(""+result);
		}
		catch (Exception e)
		{
			resultView.setText("Âèµ°£¬Ëã´íÄñ");
		}
	}
	public void num0Button(View v){
		userInput.append("0");
		showUserInput();
	}
	public void num1Button(View v){
		userInput.append("1");
		showUserInput();
	}
	public void num2Button(View v){
		userInput.append("2");
		showUserInput();
	}
	public void num3Button(View v){
		userInput.append("3");
		showUserInput();
	}
	public void num4Button(View v){
		userInput.append("4");
		showUserInput();
	}
	public void num5Button(View v){
		userInput.append("5");
		showUserInput();
	}
	public void num6Button(View v){
		userInput.append("6");
		showUserInput();
	}
	public void num7Button(View v){
		userInput.append("7");
		showUserInput();
	}
	public void num8Button(View v){
		userInput.append("8");
		showUserInput();
	}
	public void num9Button(View v){
		userInput.append("9");
		showUserInput();
	}
	public void pointButton(View v){
		userInput.append(".");
		showUserInput();
	}
	public void addButton(View v){
		userInput.append("+");
		showUserInput();
	}
	public void subButton(View v){
		userInput.append("-");
		showUserInput();
	}
	public void mulButton(View v){
		userInput.append("*");
		showUserInput();
	}
	public void divButton(View v){
		userInput.append("/");
		showUserInput();
	}
	public void backButton(View v){
		try
		{
			int lastIndex = userInput.length() - 1;
			userInput.deleteCharAt(lastIndex);
		}
		catch (StringIndexOutOfBoundsException e )
		{
			userInput = new StringBuilder();
		}
		showUserInput();
	}
	public void delButton(View v)
	{
		userInput = new StringBuilder();
		showUserInput();
	}
	public void modButton(View v)
	{
		userInput.append("%");
		showUserInput();
	}
	public void negButton(View v)
	{
		String tmp = userInput.toString();
		userInput = new StringBuilder("-" + tmp);
		showUserInput();
	}
	public void equalButton(View v)
	{
		showResult();

	}
	
	private static String InfixToPostfix(String expression)
     {
         Stack<Character> operators = new Stack<Character>();
         StringBuilder result = new StringBuilder();
         for (int i = 0; i < expression.length(); i++)
         {
             char ch = expression.charAt(i);
             if (Character.toString(ch) == " ") continue;
             switch (ch)
             {
                 case '+':
                 case '-':
                     while (operators.size() > 0)
                     {
                         char c = operators.pop();   //pop Operator
                         if (c == '(')
                         {
                             operators.push(c);      //push Operator
                             break;
                         }
                         else
                         {
                             result.append(c);
                         }
                     }
                     operators.push(ch);
                     result.append(" ");
                     break;
                 case '*':
                 case '/':
                     while (operators.size() > 0)
                     {
                         char c = operators.pop();
                         if (c == '(')
                         {
                             operators.push(c);
                             break;
                         }
                         else
                         {
                             if (c == '+' || c == '-')
                             {
                                 operators.push(c);
                                 break;
                             }
                             else
                             {
                                 result.append(c);
                             }
                         }
                     }
                     operators.push(ch);
                     result.append(" ");
                     break;
                 case '(':
                     operators.push(ch);
                     break;
                 case ')':
                     while (operators.size() > 0)
                     {
                         char c = operators.pop();
                         if (c == '(')
                         {
                             break;
                         }
                         else
                         {
                             result.append(c);
                         }
                     }
                     break;
                 default:
                     result.append(ch);
                     break;
             }
         }
         while (operators.size() > 0)
         {
             result.append(operators.pop()); //pop All Operator
         }
         return result.toString();
     }
	
	private static double Parse(String expression)
    {
        String postfixExpression = InfixToPostfix(expression);
        Stack<Double> results = new Stack<Double>();
        double x, y;
        for (int i = 0; i < postfixExpression.length(); i++)
        {
            char ch = postfixExpression.charAt(i);
            if (Character.toString(ch) == " ") continue;
            switch (ch)
            {
                case '+':
                    y = results.pop();
                    x = results.pop();
                    results.push(x + y);
                    break;
                case '-':
                    y = results.pop();
                    x = results.pop();
                    results.push(x - y);
                    break;
                case '*':
                    y = results.pop();
                    x = results.pop();
                    results.push(x * y);
                    break;
                case '/':
                    y = results.pop();
                    x = results.pop();
                    results.push(x / y);
                    break;
                default:
                    int pos = i;
                    StringBuilder operand = new StringBuilder();
                    do
                    {
                        operand.append(postfixExpression.charAt(pos));
                        pos++;
                    } while (Character.isDigit(postfixExpression.charAt(pos)) || postfixExpression.charAt(pos) == ".".charAt(0));
                    i = --pos;
                    results.push(Double.parseDouble(operand.toString()));
                    break;
            }
        }
        return results.peek();
    }
}
