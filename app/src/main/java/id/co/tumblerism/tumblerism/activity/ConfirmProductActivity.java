package id.co.tumblerism.tumblerism.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;

import id.co.tumblerism.tumblerism.R;
import id.co.tumblerism.tumblerism.model.api.RestClient;
import id.co.tumblerism.tumblerism.model.json.BuyResponse;
import id.co.tumblerism.tumblerism.model.json.Product;
import id.co.tumblerism.tumblerism.model.json.Tumbler;

public class ConfirmProductActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textMerk, textHarga;
    ImageView imageLogo;
    EditText editPassword;
    Button buttonConfirm, buttonCancel;
    Tumbler tumbler;
    Product product;
    String password;
    BuyResponse buyResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_product);

        textMerk = (TextView)findViewById(R.id.textMerk);
        textHarga = (TextView)findViewById(R.id.textHarga);
        imageLogo = (ImageView)findViewById(R.id.imageLogo);
        editPassword = (EditText)findViewById(R.id.editPassword);
        buttonCancel = (Button)findViewById(R.id.buttonCancel);
        buttonConfirm = (Button)findViewById(R.id.buttonConfirm);
        tumbler = (Tumbler)getIntent().getSerializableExtra("tumbler");
        product = (Product)getIntent().getSerializableExtra("product");


        textMerk.setText(product.getMerk());
        textHarga.setText("Rp "+product.getHarga());
        Glide.with(this).load(product.getGambar()).into(imageLogo);
        buttonConfirm.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);

        RestClient.initialize();


    }

    @Override
    public void onClick(View view) {
        if(view == buttonConfirm){
            password = String.valueOf(editPassword.getText());
            BuyProductTask task = new BuyProductTask();
            task.execute();
        } else if(view == buttonCancel){
            finish();
        }
    }

    public class BuyProductTask extends AsyncTask<Void,Void,BuyResponse>{
        ProgressDialog progressDialog;
        @Override
        protected BuyResponse doInBackground(Void... voids) {
            BuyResponse result = null;
            try {
                result = RestClient.buyService.buyProduct(product.getId(), tumbler.getId_tumbler(), password).execute().body();
                buyResponse = result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ConfirmProductActivity.this);
            progressDialog.setMessage("Processing");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(BuyResponse s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Log.d("response",s.getStatus()+" : "+s.getMessage());
            setUp(s);
        }
    }

    public void setUp(BuyResponse buyResponse){
        this.buyResponse = buyResponse;
        if (buyResponse.getStatus().equalsIgnoreCase("failed")){
            Toast.makeText(this,buyResponse.getMessage(),Toast.LENGTH_LONG).show();
        } else if (buyResponse.getStatus().equalsIgnoreCase("success")){
            Toast.makeText(this,buyResponse.getMessage(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ConfirmProductActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
