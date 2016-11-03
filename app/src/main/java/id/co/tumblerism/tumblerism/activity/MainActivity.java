package id.co.tumblerism.tumblerism.activity;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import id.co.tumblerism.tumblerism.R;
import id.co.tumblerism.tumblerism.model.api.RestClient;
import id.co.tumblerism.tumblerism.model.json.Tumbler;
import id.co.tumblerism.tumblerism.model.json.TumblerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "MainActivity";
    Intent intent;
    Button buttonScan;
    String scanResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonScan = (Button)findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(this);
        intent = new Intent(MainActivity.this, WelcomeActivity.class);
        RestClient.initialize();
    }

    @Override
    public void onClick(View view) {
        if(view == buttonScan){
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(false);
            integrator.initiateScan();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                Log.d("cancel", "cancelled");
            } else {
                scanResult = result.getContents();
                Log.d("hasilscan", scanResult);
                RestClient.tumblerService.getTumbler(scanResult).enqueue(new Callback<TumblerResponse>() {
                    @Override
                    public void onResponse(Call<TumblerResponse> call, Response<TumblerResponse> response) {
                        if(response.isSuccessful()){
                            Tumbler tumbler = response.body().getData();
                            Log.d(TAG, "onResponse = "+ tumbler);
                            if (response.body().getStatus().equalsIgnoreCase("success")){
                                intent.putExtra("tumbler", tumbler);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getBaseContext(), "Wrong QR code", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TumblerResponse> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "Server error", Toast.LENGTH_LONG).show();
                        Log.d("response",t.toString());
                    }
                });
            }
        } else {
            //Log.d("hasilscan", "kosong");
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
