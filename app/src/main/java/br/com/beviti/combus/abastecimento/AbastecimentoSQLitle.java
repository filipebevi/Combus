package br.com.beviti.combus.abastecimento;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.beviti.combus.util.ConectaSQLite;

import java.util.ArrayList;
import java.util.List;

public class AbastecimentoSQLitle implements AbastecimentoDAO {

    private SQLiteDatabase le;
    private SQLiteDatabase escreve;

    public AbastecimentoSQLitle(Context context) {
        ConectaSQLite c = new ConectaSQLite(context);
        le = c.getReadableDatabase();
        escreve = c.getWritableDatabase();
    }

    @Override
    public boolean salvarAbastecimento(Abastecimento a) {
        boolean resultado = false;

        ContentValues cv = new ContentValues();
        cv.put("tipo", a.getTipo());
        cv.put("km", a.getKm());
        cv.put("litros", a.getLitros());
        cv.put("posto", a.getPosto());
        cv.put("total", a.getTotal());
        cv.put("valor", a.getValor());
        cv.put("data", a.getData().toString());

        if(a.getId()==null ){
            try {
                escreve.insert(ConectaSQLite.TABELA, null, cv);
                resultado = true;
            } catch (Exception e) {
                Log.i("ERRO", "ERRO NA INCLUSAO DO REGISTRO");
            }

        }else {
            try {
                escreve.update(ConectaSQLite.TABELA,cv,"id="+a.getId(),null);
                resultado = true;
            } catch (Exception e) {
                Log.i("ERRO", "ERRO NA ALTERACAO DO REGISTRO");
            }

        }






        return resultado;
    }

    @Override
    public boolean excluirAbastecimento(Abastecimento a) {
        //metodo para excluir o abastecimento;
        try {
            escreve.delete(ConectaSQLite.TABELA,"id="+a.getId(),null);
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    @Override
    public List<Abastecimento> listarAbastecimento() {
        List<Abastecimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + ConectaSQLite.TABELA + "; ";

        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()) {
            Abastecimento a = new Abastecimento();

            int id = c.getInt(c.getColumnIndex("id"));
            String tipo = c.getString(c.getColumnIndex("tipo"));
            String posto = c.getString(c.getColumnIndex("posto"));
            String data = c.getString(c.getColumnIndex("data"));
            Double km = c.getDouble(c.getColumnIndex("km"));
            Double valor = c.getDouble(c.getColumnIndex("valor"));
            Double litros = c.getDouble(c.getColumnIndex("litros"));

            //a.setData(data);
            //a.setId(id);
            a.setKm(km);
            a.setLitros(litros);
            a.setPosto(posto);
            a.setTipo(tipo);
            a.setValor(valor);

            lista.add(a);


        }

        return lista;
    }
}
