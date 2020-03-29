package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText field_peso, field_altura;
    private SeekBar bar;
    private TextView text_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        field_altura = findViewById(R.id.field_altura);
        field_peso = findViewById(R.id.field_peso);
        bar = findViewById(R.id.bar);
        text_resultado = findViewById(R.id.text_resultado);
    }

    public void calcular(View view){

        if (!field_peso.getText().toString().equals("") && !field_altura.getText().toString().equals("")){

            double peso = Double.parseDouble(field_peso.getText().toString());
            double altura = Double.parseDouble(field_altura.getText().toString());

            if(peso > 0 && altura > 0){
                double imc = peso/(altura*altura);
                String enunciado = imc+", ";

                if(imc < 16 ){
                    enunciado += "Criterio de ingreso en hospital.";
                }else if(imc >= 16 && imc <17){
                    enunciado += "Infrapeso.";
                }else if (imc >= 17 && imc < 18){
                    enunciado += "Bajo peso.";
                }else if (imc >= 18 && imc < 25){
                    enunciado += "Peso normal (saludable).";
                }else if (imc >= 25 && imc < 30){
                    enunciado += "Sobrepeso (obesidad de grado I).";
                }else if (imc >= 30 && imc < 35){
                    enunciado += "Sobrepeso crónico (obesidad de grado II).";
                }else if (imc >= 35 && imc < 40){
                    enunciado += "Obesidad premórbida (obesidad de grado III).";
                }else if (imc >= 40){
                    enunciado += "Obecidad premórbida (obseidad de grado IV).";
                    bar.setMax((int)imc+1);
                }

                bar.setProgress((int)imc);
                text_resultado.setText(enunciado);
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);

            }else{
                Toast.makeText(this, "Los valores deben ser mayor a cero (0).", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Por favor ingrese los valores.", Toast.LENGTH_SHORT).show();
        }
    }
}
