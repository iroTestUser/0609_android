package com.example.exam_shop2022;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_product_main;
    EditText edit_count;
    TextView txt_price, txt_delivery, txt_pay;
    CheckBox chk_agree;

    int val_price;                  // 제품의 총 가격(배송비 미포함)
    int val_delivery;               // 배송비 ( 제품 총 가격이 10000원 이상이면 0원, 미만이면 2500원)
    int val_pay;                    // 총 결제 금액
    int selected_product=2000;      // 선택한 제품 한개의 가격
    int selected_count;             // 선택한 수량

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);
        findViewById(R.id.radio4).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);

        chk_agree = findViewById(R.id.chk_agree);
        txt_price=findViewById(R.id.txt_price);
        txt_pay=findViewById(R.id.txt_pay);
        txt_delivery=findViewById(R.id.txt_delivery);
        edit_count=findViewById(R.id.edit_count);
        img_product_main=findViewById(R.id.img_product_main);

        edit_count.setText(1+"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.radio1:
                img_product_main.setImageResource(R.drawable.coffee1);
                selected_product=2000;
                sumTotal();
                break;
            case R.id.radio2:
                img_product_main.setImageResource(R.drawable.coffee2);
                selected_product=3000;
                sumTotal();
                break;
            case R.id.radio3:
                img_product_main.setImageResource(R.drawable.coffee3);
                selected_product=4000;
                sumTotal();
                break;
            case R.id.radio4:
                img_product_main.setImageResource(R.drawable.coffee4);
                selected_product=5000;
                sumTotal();
                break;
            case R.id.btn_minus:
                int count1 = Integer.parseInt(edit_count.getText().toString());
                if(count1==1){
                    Toast.makeText(this, "최소수량은 1개 입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    count1-=1;
                    edit_count.setText(String.valueOf(count1));
                }
                sumTotal();
                break;
            case R.id.btn_pay:
                if(chk_agree.isChecked()){
                    sumTotal();
                    Toast.makeText(this, val_pay+"원을 결제하겠습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,SubActivity.class);
                    intent.putExtra("val_pay",val_pay);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "결제 동의 버튼을 체크해 주세요.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_plus:
                int count2 = Integer.parseInt(edit_count.getText().toString());
                if(count2==5){
                    Toast.makeText(this, "최대수량은 5개 입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    count2+=1;
                    edit_count.setText(String.valueOf(count2));
                    sumTotal();
                }
                break;
        }
    }

    private void sumTotal(){
        selected_count=Integer.parseInt(edit_count.getText().toString());

        if((selected_count*selected_product)>=10000){
            txt_delivery.setText("0원");
            val_delivery=0;
        }
        else{
            txt_delivery.setText("2500원");
            val_delivery=2500;
        }
        txt_price.setText(selected_product*selected_count+"원");
        txt_pay.setText(selected_product*selected_count+val_delivery+"원");

        val_price=selected_product*selected_count;
        val_pay=selected_product*selected_count+val_delivery;
    }
}