package leonardo.pmo.unibratec.slideactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadAlunoActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etNota;
    private EditText etMatricula;
    private EditText etDtaNascimento;
    private Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_aluno);


        etNome = (EditText) findViewById(R.id.et_nome);
        etNota = (EditText) findViewById(R.id.et_nota);
        etMatricula = (EditText) findViewById(R.id.et_matricula);
        etDtaNascimento = (EditText) findViewById(R.id.et_dtaNascimento);
        Button button = (Button) findViewById(R.id.bt_salvar);

        //pegar o aluno que está chegando.
        aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        if (aluno != null) {
            etNome.setText(aluno.getNome());
            etNota.setText(aluno.getNota());
            etMatricula.setText(aluno.getMatricula());
            etDtaNascimento.setText(aluno.getDtaNascimento());
        } else {
            aluno = new Aluno();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aluno.setNome(etNome.getText().toString());
                aluno.setNota(etNota.getText().toString());
                aluno.setMatricula(etMatricula.getText().toString());
                aluno.setDtaNascimento(etDtaNascimento.getText().toString());
                Dao dao = new Dao(CadAlunoActivity.this);
                dao.salvar(aluno);
                finish();
            }
        });


    }
}
