package com.novoconversor.marcos.novoconversor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static Locale novoDollar = new Locale("us", "US");
    private static Locale novoEuro = new Locale("fr", "FR");
    private static final NumberFormat cotacaoFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat cotaDollar = NumberFormat.getCurrencyInstance(novoDollar);
    private static final NumberFormat cotaEuro = NumberFormat.getCurrencyInstance(novoEuro);

        private TextView textDollar, textEuro, textReal;
        private double dollar = 0.0;
        private double euro = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textReal = this.findViewById(R.id.textView_real);
        textDollar = this.findViewById(R.id.textView_dollar);
        textEuro = this.findViewById(R.id.textView_euro);
        textDollar.setText(cotaDollar.format(0));
        textEuro.setText(cotaEuro.format(0));
        EditText editReal = this.findViewById(R.id.editText_real);

        editReal.addTextChangedListener(editRealTextWatcher);

    }

    private void Calcular(){

        double valorDollar = dollar / 3.30;
        double valorEuro = euro / 4.09;

        textDollar.setText(cotaDollar.format(valorDollar));
        textEuro.setText(cotaEuro.format(valorEuro));
    }

    private final TextWatcher editRealTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try{
                dollar = Double.parseDouble(s.toString()) / 100;
                textReal.setText(cotacaoFormat.format(dollar));

                euro = Double.parseDouble(s.toString()) / 100;
                textReal.setText(cotacaoFormat.format(euro));

            }catch (NumberFormatException e){

                textReal.setText("");
                dollar = 0.0;
                euro = 0.0;

            }

            Calcular();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
