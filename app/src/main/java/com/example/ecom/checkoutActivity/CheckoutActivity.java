package com.example.ecom.checkoutActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecom.R;
import com.example.ecom.homeActivity.MainActivity;


public class CheckoutActivity extends AppCompatActivity {

    private Button button;
    private TextView order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        String orderId = intent.getStringExtra("orderId");
        order = findViewById(R.id.orderId);
        order.setText(orderId);


        button = findViewById(R.id.continueShopping);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
