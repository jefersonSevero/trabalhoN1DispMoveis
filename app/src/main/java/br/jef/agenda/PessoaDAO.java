package br.jef.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public static void inserir(Context context, Pessoa pessoa){
        ContentValues valores = new ContentValues();
        valores.put("nome", pessoa.getNome());
        valores.put("fone", pessoa.getFone());
        valores.put("email", pessoa.getEmail());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("pessoas", null, valores);
    }

    public static void editar(Context context, Pessoa pessoa){
        ContentValues valores = new ContentValues();
        valores.put("nome", pessoa.getNome());
        valores.put("fone", pessoa.getFone());
        valores.put("email", pessoa.getEmail());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("pessoas", valores, "id = " + pessoa.getId(), null);
    }

    public static void excluir(Context context, int idPessoa){
        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("pessoas","id = " + idPessoa, null);
    }

    public static List<Pessoa> getPessoas(Context context){
        List<Pessoa> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM pessoas ORDER BY nome", null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                Pessoa pess = new Pessoa();
                pess.setId(cursor.getInt(0));
                pess.setNome(cursor.getString(1));
                pess.setFone(cursor.getString(2));
                pess.setEmail(cursor.getString(3));
                lista.add(pess);
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public static Pessoa getCandidatoById(Context context, int idPessoa){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM pessoas where id = " + idPessoa, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            Pessoa pess = new Pessoa();
            pess.setId(cursor.getInt(0));
            pess.setNome(cursor.getString(1));
            pess.setFone(cursor.getString(2));
            pess.setEmail(cursor.getString(3));

            return pess;
        }else{
            return null;
        }
    }
}
