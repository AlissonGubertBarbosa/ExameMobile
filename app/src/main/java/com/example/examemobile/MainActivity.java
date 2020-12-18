package com.example.examemobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.examemobile.adaptador.Adaptador;
import com.example.examemobile.modelo.cao;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView CãoRecy;
    private EditText ednome;
    private EditText edraca;
    private EditText edgenero;

    private List<cao> listacao = new ArrayList<>();
    private FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ednome = findViewById(R.id.nomeed);
        edraca = findViewById(R.id.racaed);
        edgenero = findViewById(R.id.generoed);

        IniciarFirebase();


        CãoRecy = findViewById(R.id.recy);
        criar();

        Adaptador adaptador = new Adaptador(this.listacao);


        //configurar layout

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        CãoRecy.setLayoutManager(layoutManager);
        CãoRecy.setHasFixedSize(true);
        CãoRecy.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        CãoRecy.setAdapter(adaptador);
    }

    private void IniciarFirebase()
    {
        db = FirebaseDatabase.getInstance();
        db.setPersistenceEnabled(true);
        FirebaseApp.initializeApp(this);
        reference = db.getReference();
    }

    public void criar() {
        cao Caozinho = new cao("Togo", "pincher", "masc");
        this.listacao.add(Caozinho);

        Caozinho = new cao("lili", "pincher", "fem");
        this.listacao.add(Caozinho);

        Caozinho = new cao("rex", "pincher", "masc");
        this.listacao.add(Caozinho);

        Caozinho = new cao("spike", "pincher", "fem");
        this.listacao.add(Caozinho);
    }

    public void salvar(View view)
    {
        cao Caozinho = new cao();
        Caozinho.setNome(ednome.getText().toString());
        Caozinho.setGenero(edgenero.getText().toString());
        Caozinho.setRaca(edraca.getText().toString());

        reference.child("cao").child(Caozinho.getNome()).setValue(Caozinho);
        Toast.makeText(getApplicationContext(),"Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
    }
}