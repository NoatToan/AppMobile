package com.example.exercise1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnPlaceOrder;
    CheckBox cbChicken, cbBeef, cbWhiteFish, cbCheese, cbSeaFood, cbPicodeGallo, cbBeans,
            cbRice, cbLBT, cbGuacamole, cbSoda, cbCerveza, cbMargarita, cbTequilla;
    RadioButton rbLarge, rbMedium, rbCorn, rbFlour;
    String Size, Tortilla;
    List<CheckBox> listFillings, listBeverage;
    String Fillings, Beverage;

    String Address = "0969498661";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!checkPermission(Manifest.permission.SEND_SMS)) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},200);
        }
        PrepareId();
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetOrders();
                showConfirmDialog();
            }
        });
    }

    public void showConfirmDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Cornfirm Order")
                .setMessage(ConfirmMessage())
                .setPositiveButton("Order", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SendOrder();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void showSuccessDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Successful")
                .setMessage("Thank you for buying.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void SendOrder() {
        String order = Size + "," + Tortilla + "," + Fillings + "," + Beverage;
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(Address, null, order, null, null);
            showSuccessDialog();
        }
        else{
            //Toast.makeText(this,"Permission Denied!",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},200);
        }
    }

    private boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return check == PackageManager.PERMISSION_GRANTED;
    }

    public String ConfirmMessage() {
        String message = "---------------------------------------\n" +
                "Size: " + Size + "\n" +
                "Tortilla: " + Tortilla + "\n" +
                "Fillings: " + Fillings + "\n" +
                "Beverage: " + Beverage + "\n" +
                "---------------------------------------";
        return message;
    }

    public void GetOrders() {
        Fillings = "";
        Beverage = "";

        Size = rbLarge.isChecked() ? rbLarge.getText().toString() :
                rbMedium.getText().toString();
        Tortilla = rbCorn.isChecked() ? rbCorn.getText().toString() :
                rbFlour.getText().toString();

        for (int i = 0; i < listFillings.size(); i++) {
            if (listFillings.get(i).isChecked()) {
                Fillings += listFillings.get(i).getText().toString() + ", ";
            }
        }

        for (int i = 0; i < listBeverage.size(); i++) {
            if (listBeverage.get(i).isChecked()) {
                Beverage += listBeverage.get(i).getText().toString() + ", ";
            }
        }

        Fillings = Fillings == "" ? "None" : Fillings.substring(0, Fillings.length() - 2);
        Beverage = Beverage == "" ? "None" : Beverage.substring(0, Beverage.length() - 2);
    }

    public void PrepareId() {
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        cbChicken = findViewById(R.id.cbChicken);
        cbBeef = findViewById(R.id.cbBeef);
        cbWhiteFish = findViewById(R.id.cbWhiteFish);
        cbCheese = findViewById(R.id.cbCheese);
        cbSeaFood = findViewById(R.id.cbSeaFood);
        cbPicodeGallo = findViewById(R.id.cbPicodeGallo);
        cbBeans = findViewById(R.id.cbBeans);
        cbRice = findViewById(R.id.cbRice);
        cbLBT = findViewById(R.id.cbLBT);
        cbGuacamole = findViewById(R.id.cbGuacamole);

        cbSoda = findViewById(R.id.cbSoda);
        cbCerveza = findViewById(R.id.cbCerveza);
        cbMargarita = findViewById(R.id.cbMargarita);
        cbTequilla = findViewById(R.id.cbTequila);

        rbLarge = findViewById(R.id.rbLarge);
        rbMedium = findViewById(R.id.rbMedium);
        rbCorn = findViewById(R.id.rbCorn);
        rbFlour = findViewById(R.id.rbFlour);

        listFillings = Arrays.asList(cbChicken, cbBeef, cbWhiteFish, cbCheese, cbSeaFood, cbPicodeGallo,
                cbBeans, cbRice, cbLBT, cbGuacamole);
        listBeverage = Arrays.asList(cbSoda, cbCerveza, cbMargarita, cbTequilla);
    }
}
