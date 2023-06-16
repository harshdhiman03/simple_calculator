package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener
{
    TextView resultTv,solutionTV;
    MaterialButton C,openbracket,closebracket,divide,mul,add,sub,equals,b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,ac,dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTV=findViewById(R.id.solution_tv);
        assignId(b0,R.id.button_0);
        assignId(b1,R.id.button_1);
        assignId(b2,R.id.button_2);
        assignId(b3,R.id.button_3);
        assignId(b4,R.id.button_4);
        assignId(b5,R.id.button_5);
        assignId(b6,R.id.button_6);
        assignId(b7,R.id.button_7);
        assignId(b8,R.id.button_8);
        assignId(b9,R.id.button_9);
        assignId(C,R.id.button_c);
        assignId(openbracket,R.id.button_open_bracket);
        assignId(closebracket,R.id.button_close_bracket);
        assignId(divide,R.id.button_divide);
        assignId(mul,R.id.button_multiple);
        assignId(sub,R.id.button_sub);
        assignId(dot,R.id.button_dot);
        assignId(add,R.id.button_add);
        assignId(ac,R.id.button_ac);
        assignId(equals,R.id.button_equals);



    }
    void assignId(MaterialButton btn,int id){
        btn =findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton Button=(MaterialButton)view;
        String buttontext = Button.getText().toString();
        String datacal = solutionTV.getText().toString();
        if (buttontext.equals("AC")){
            solutionTV.setText(" ");
            resultTv.setText("0");
        }
        if (buttontext.equals("=")){
            solutionTV.setText(resultTv.getText());
            return;
        }
        if (buttontext.equals("C")){
            datacal=datacal.substring(0,datacal.length()-1);
        }
        else {
            datacal = datacal+buttontext;
        }


        solutionTV.setText(datacal);
        String fresult = getr(datacal);
        if (fresult.equals("ERROR")){
            resultTv.setText(fresult);
        }

    }
    String getr(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String fresult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if (fresult.endsWith(".0")){
               fresult=fresult.replace(".0","");
           }
            return fresult;
        }
        catch (Exception e){
            return "ERROR";
        }

    }
}