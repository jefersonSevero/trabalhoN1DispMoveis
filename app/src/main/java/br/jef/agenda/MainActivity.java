package br.jef.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvPessoas;
    private List<Pessoa> listaPessoas;
    private ArrayAdapter adapter;

    private EditText etNome;
    private EditText etFone;
    private EditText etEmail;
    private Button btInserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPessoas = findViewById(R.id.lvPessoas);
        etNome = findViewById(R.id.etNome);
        etFone = findViewById(R.id.etFone);
        etEmail = findViewById(R.id.etEmail);
        btInserir = findViewById(R.id.btInserir);

        carregarLista();
        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
                carregarLista();
            }
        });
    }

    private void salvar(){
        String nome = etNome.getText().toString();
        String fone = etFone.getText().toString();
        String email = etEmail.getText().toString();

        if (nome.isEmpty() || fone.isEmpty()){
            Toast.makeText(this, "Para cadastrar uma pessoa, informar nome e fone!", Toast.LENGTH_LONG).show();

        }else{
            Pessoa pess = new Pessoa();
            pess.setNome(nome);
            pess.setFone(fone);
            pess.setEmail(email);

            PessoaDAO.inserir(this,pess);

            etNome.setText("");
            etFone.setText("");
            etEmail.setText("");

        }
    }

    //ARRAYLIST
    private void carregarLista() {

        listaPessoas = PessoaDAO.getPessoas(this);

        if (listaPessoas.size() == 0) {
            Pessoa fake = new Pessoa("Lista vazia");
            listaPessoas.add(fake);
            lvPessoas.setEnabled(false);
        }else{
            lvPessoas.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPessoas);
        lvPessoas.setAdapter(adapter);
    }

    //CLIQUE NA LISTVIEW
    public void itemClick(AdapterView<?> parent, View view, int position, long id){
        int idProduto = listaPessoas.get(position).getId();
    }
}
