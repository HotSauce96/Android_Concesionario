package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

    }

    public void Vehiculo(View view) {
        try {
            Intent intvehiculo = new Intent(this, Vehiculo.class);
            startActivity(intvehiculo);
        }
        catch (Exception e){
            e.toString();
        }
    }

  public void Factura(View view) {
      Intent intfactura = new Intent(this, Factura.class);
       startActivity(intfactura);

  }
}