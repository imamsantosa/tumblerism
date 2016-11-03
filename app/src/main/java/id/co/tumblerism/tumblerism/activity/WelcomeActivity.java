package id.co.tumblerism.tumblerism.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.co.tumblerism.tumblerism.R;
import id.co.tumblerism.tumblerism.model.json.Tumbler;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    Tumbler tumbler;
    TextView textBalance;
    Button buttonBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tumbler = (Tumbler) getIntent().getSerializableExtra("tumbler");
        textBalance = (TextView)findViewById(R.id.textBalance);
        buttonBuy = (Button)findViewById(R.id.buttonBuy);

        textBalance.setText("Rp "+String.valueOf(tumbler.getSaldo()));
        buttonBuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonBuy){
            Intent intent = new Intent(WelcomeActivity.this, ChooseProductActivity.class);
            intent.putExtra("tumbler", tumbler);
            startActivity(intent);
        }
    }
}
