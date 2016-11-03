package id.co.tumblerism.tumblerism.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.co.tumblerism.tumblerism.R;
import id.co.tumblerism.tumblerism.model.api.RestClient;
import id.co.tumblerism.tumblerism.model.api.service.ProductService;
import id.co.tumblerism.tumblerism.model.json.Product;
import id.co.tumblerism.tumblerism.model.json.ProductList;
import id.co.tumblerism.tumblerism.model.json.Tumbler;
import id.co.tumblerism.tumblerism.model.json.TumblerResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseProductActivity extends AppCompatActivity implements View.OnClickListener {

    List<Product> productList;
    Tumbler tumbler;
    RecyclerView recyclerProduct;
    Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);
        Log.d("cobaa","coba");

        recyclerProduct = (RecyclerView)findViewById(R.id.recyclerProduct) ;
        buttonCancel = (Button)findViewById(R.id.buttonCancel);
        tumbler = (Tumbler) getIntent().getSerializableExtra("tumbler");
        productList = new ArrayList<Product>();
        buttonCancel.setOnClickListener(this);

        RestClient.initialize();

        GetProductTask task = new GetProductTask();
        task.execute();
    }

    public Tumbler getTumbler() {
        return tumbler;
    }

    public void balanceToast(){
        Toast.makeText(this, "Your balance is not enough to buy this product", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if(view == buttonCancel){
            finish();
        }
    }

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

        List<Product> productList;

        public class ProductViewHolder extends RecyclerView.ViewHolder{
            public ImageView imageLogo;
            public TextView textMerk, textHarga;
            public Button buttonBuy;

            public ProductViewHolder(View itemView) {
                super(itemView);
                imageLogo = (ImageView) itemView.findViewById(R.id.imageLogo);
                textHarga = (TextView) itemView.findViewById(R.id.textHarga);
                textMerk = (TextView) itemView.findViewById(R.id.textMerk);
                buttonBuy = (Button) itemView.findViewById(R.id.buttonBuy);
            }
        }

        public ProductAdapter(List<Product> productList) {this.productList = productList;}

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getBaseContext())
                    .inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {
            holder.textMerk.setText(productList.get(position).getMerk());
            holder.textHarga.setText("Rp "+ productList.get(position).getHarga());
            Glide.with(getBaseContext()).load(productList.get(position).getGambar()).into(holder.imageLogo);
            holder.buttonBuy.setOnClickListener(productList.get(position));
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

    }

    private class GetProductTask extends AsyncTask<Void, Void, List<Product>>{
        ProgressDialog progressDialog;

        @Override
        protected List<Product> doInBackground(Void... voids) {
            ProductList result;
            try {
                result = RestClient.productService.getProduct().execute().body();
                productList.add(result.getData().getP0001());
                productList.add(result.getData().getP0002());
                productList.add(result.getData().getP0003());
                productList.add(result.getData().getP0004());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("response get adapter1", String.valueOf(productList.size()));
            return productList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ChooseProductActivity.this);
            progressDialog.setMessage("Loading Products");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(List<Product> productList) {
            super.onPostExecute(productList);
            progressDialog.dismiss();
            ChooseProductActivity.this.productList = productList;
            setUp();
        }
    }

    public void setUp(){
        Log.d("response get adapter", String.valueOf(productList.size()));
        ProductAdapter productAdapter = new ProductAdapter(productList);
        recyclerProduct.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerProduct.setAdapter(productAdapter);
    }
}
