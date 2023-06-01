package br.edu.ifsuldeminas.mch.fuel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                String valorEtanolString = textInputEditTextEtanol.getText().toString();
                String valorGasString = textInputEditTextGas.getText().toString();


                if(valorEtanolString.equals("")){
                    Toast.makeText(getApplicationContext(),"Valor do etanol não pode ser vazio",Toast.LENGTH_LONG).show();
                    return;
                }

                if(valorGasString.equals("")){
                    Toast.makeText(getApplicationContext(),"Valor da gasolina não pode ser vazio",Toast.LENGTH_LONG).show();
                    return;
                }

                Double valorEtanol = Double.parseDouble(valorEtanolString);
                Double valorGas = Double.parseDouble(valorGasString);

                String mensagem = "";

                if((valorEtanol/valorGas)>= 0.7){
                    mensagem = "Melhor usar gasolina";
                    imageViewFuel.setImageResource(R.drawable.gas);
                }else{
                    mensagem = "Melhor usar etanol";
                    imageViewFuel.setImageResource(R.drawable.ethanol);
                }

                imageViewFuel.setVisibility(ImageView.VISIBLE);
                imageViewShare.setVisibility(ImageView.VISIBLE);

                textViewMessage.setText(mensagem);
                textViewMessage.setVisibility(TextView.VISIBLE);

                imageViewShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Preços de qual posto");

                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.alert_dialog_gas_station_view, null);

                        builder.setView(layout);

                        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                EditText nomePosto = layout.findViewById(R.id.editTextAlertDialogGasStationId);
                                String nomePostoString = nomePosto.getText().toString();


                                String valorEtanolString = textInputEditTextEtanol.getText().toString();
                                String valorGasString = textInputEditTextGas.getText().toString();

                                Double valorEtanol = Double.parseDouble(valorEtanolString);
                                Double valorGas = Double.parseDouble(valorGasString);

                                Double relacao = (valorEtanol/valorGas)*100;

                                String opcao =  relacao >= 70 ? "Gasolina" : "Etanol";

                                String mensagem = String.format("Preços no posto %s: Etanol: R$ %.2f e Gasolina: R$ %.2f. Melhor usar %s, relação %.2f%%", nomePostoString,valorEtanol, valorGas,opcao,relacao);

//                                Toast toast = Toast.makeText(getApplicationContext(),mensagem,Toast.LENGTH_LONG);
//                                toast.show();

                                Intent intentMensagem = new Intent();
                                intentMensagem.setAction(Intent.ACTION_SEND);
                                intentMensagem.putExtra(Intent.EXTRA_TEXT, mensagem);
                                intentMensagem.setType("text/plain");

                                Intent chooserIntent = Intent.createChooser(intentMensagem, "Compartilhar");
                                startActivity(chooserIntent);       
                            }
                        });
                        builder.setNegativeButton("Cancelar", null);

                        builder.setView(layout);

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        imageViewFuel.setVisibility(ImageView.INVISIBLE);
        textViewMessage.setVisibility(ImageView.INVISIBLE);
        imageViewShare.setVisibility(ImageView.INVISIBLE);
    }
}