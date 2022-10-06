package com.example.concesionario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Vehiculo extends AppCompatActivity {

    EditText jetplaca,jetmarca,jetmodelo,jetvalor;
    CheckBox jcbactivo;
    // ClsSqLite admin=new ClsSqLite(this,"Concesionario.db",null,1);
    long resp;
    String placa,marca,modelo,valor;
    private SQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehiculo);

        //Ocultar la barra de menu y asociar objetos Java con Xml
        getSupportActionBar().hide();
        jetplaca=findViewById(R.id.etplaca);
        jetmarca=findViewById(R.id.etmarca);
        jetmodelo=findViewById(R.id.etmodelo);
        jetvalor=findViewById(R.id.etvalor);
        jcbactivo=findViewById(R.id.cbactivo);
    }

    public void Guardar(View view){
        placa=jetplaca.getText().toString();
        marca=jetmarca.getText().toString();
        modelo=jetmodelo.getText().toString();
        valor=jetvalor.getText().toString();
        if (placa.isEmpty() || marca.isEmpty()
                || modelo.isEmpty() || valor.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        }
        else{
            SQLiteDatabase db=admin.getWritableDatabase();
            ContentValues registro=new ContentValues();
            registro.put("placa",placa);
            registro.put("marca",marca);
            registro.put("modelo",modelo);
            registro.put("valor",Integer.parseInt(valor));
            resp=db.insert("TblAutomovil",null,registro);
            if (resp > 0){
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                Limpiar_campos();
            }
            else{
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
    }

    public void Consultar(View view){
        placa=jetplaca.getText().toString();
        if (placa.isEmpty()){
            Toast.makeText(this, "Placa es requerida para busqueda", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        }
        else{
            SQLiteDatabase db=admin.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from TbLAutomovil where placa='" + placa + "'",null);
            if (fila.moveToNext()){

            }
            else{
                Toast.makeText(this, "Vehiculo no registrado", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
    }

    public void Anular(View view){
        if (resp > 0){
            Toast.makeText(this, "Debe buscar primero", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        }
        else{
            SQLiteDatabase db=admin.getWritableDatabase();
            ContentValues registro=new ContentValues();
            registro.put("estado","no");
            resp=db.update("TblAutomovil",registro,"placa='" + placa + "'",null);
            if (resp > 0){
                Toast.makeText(this, "Registro anulado", Toast.LENGTH_SHORT).show();
                Limpiar_campos();
            }
            else
                Toast.makeText(this, "Error eliminando registro", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
    public void Cancelar(View view){
        Limpiar_campos();
    }

    public void Regresar(View view){
        Intent intmain=new Intent(this,MainActivity.class);
        startActivity(intmain);
    }



    public void Limpiar_campos(){
        jetplaca.setText("");
        jetvalor.setText("");
        jetmodelo.setText("");
        jetmarca.setText("");
        jcbactivo.setChecked(false);
        jetplaca.requestFocus();
    }

}
