package leonardo.pmo.unibratec.slideactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Aluno aluno;
    private ListView lvAlunos;
    private ArrayList<Aluno> alunos;
    private ArrayAdapter<Aluno> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        carregaLista();
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadAlunoActivity.class);
                startActivity(intent);
            }
        });

        lvAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this, CadAlunoActivity.class);
                it.putExtra("aluno", adapter.getItem(position));
                startActivity(it);
            }
        });

        registerForContextMenu(lvAlunos);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_aluno, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno aluno = adapter.getItem(info.position);
        switch (item.getItemId()) {
            case R.id.mi_nome:
                Toast.makeText(this, "Aluno(a): " + aluno, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.mi_pagina:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.unibratec.edu.br"));
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void carregaLista() {
        lvAlunos = (ListView) findViewById(R.id.lv_aluno);
        Dao dao = new Dao(this);
        alunos = (ArrayList<Aluno>) dao.buscarAlunos();
        adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lvAlunos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }
}
