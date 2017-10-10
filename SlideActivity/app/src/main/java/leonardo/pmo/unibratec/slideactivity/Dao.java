package leonardo.pmo.unibratec.slideactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo Marques on 10/10/2017.
 */

public class Dao extends SQLiteOpenHelper {
    public Dao(Context context) {
        super(context, "Activity", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table aluno(id integer primary key, nome TEXT, nota TEXT, matricula TEXT, dtaNasc TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table aluno;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void salvar(Aluno aluno) {
        ContentValues cv = getContentValues(aluno);
        SQLiteDatabase db = getWritableDatabase();
        if (aluno.getId() == null) {
            db.insert("aluno", null, cv);
        } else {
            atualizar(aluno);
        }

    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues cv = new ContentValues();
        cv.put("nome", aluno.getNome());
        cv.put("nota", aluno.getNota());
        cv.put("matricula", aluno.getMatricula());
        cv.put("dtaNasc", aluno.getDtaNascimento());
        return cv;
    }

    public Aluno atualizar(Aluno aluno) {
        ContentValues cv = getContentValues(aluno);
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        int retorno = db.update("aluno", cv, "id = ?", params);
        if (retorno > 0) {
            return aluno;
        }
        return null;
    }

    public List<Aluno> buscarAlunos() {
        String sql = "select * from aluno;";
        List<Aluno> alunos = new ArrayList<Aluno>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setNota(cursor.getString(cursor.getColumnIndex("nota")));
            aluno.setMatricula(cursor.getString(cursor.getColumnIndex("matricula")));
            aluno.setDtaNascimento(cursor.getString(cursor.getColumnIndex("dtaNasc")));
            alunos.add(aluno);
        }
        return alunos;
    }
}