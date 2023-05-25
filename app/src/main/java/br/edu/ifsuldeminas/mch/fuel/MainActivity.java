package br.edu.ifsuldeminas.mch.fuel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextEtanol;
    private TextInputEditText textInputEditTextGas;
    private Button buttonCalcular;
    private ImageView imageViewFuel;
    private TextView textViewMessage;
    private ImageView imageViewShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputEditTextEtanol = findViewById(R.id.textInputEditTextEtanol);
        textInputEditTextGas = findViewById(R.id.textInputEditTextGas);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        imageViewFuel = findViewById(R.id.imageViewFuel);
        textViewMessage = findViewById(R.id.textViewMessage);
        imageViewShare = findViewById(R.id.imageViewShare);

        //Clique calcular

    }
}