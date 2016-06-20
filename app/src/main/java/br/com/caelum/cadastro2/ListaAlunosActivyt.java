package br.com.caelum.cadastro2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import br.com.caelum.cadastro2.adapter.AlunoAdapter;
import br.com.caelum.cadastro2.br.com.caelum.cadastro2.dao.AlunoDAO;
import br.com.caelum.cadastro2.br.com.caelum.cadastro2.modelo.Aluno;

/**
 * Created by SIDNEY on 26/01/2016.
 */
public class ListaAlunosActivyt  extends Activity{

    private Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);
        //buscar a lista
        ListView lista = (ListView) findViewById(R.id.lista);
        registerForContextMenu(lista);
        //Array de String com nomes para adicionar a lista.
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos =  dao.getLista();



        carregaLista();







        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {

                Aluno AlunoParaSerAlterado = (Aluno) adapter.getItemAtPosition(posicao);

               Intent irParaOFormulario = new Intent(ListaAlunosActivyt.this, FormularioActivyt.class);
                irParaOFormulario.putExtra("alunoSelecionado", AlunoParaSerAlterado);

                startActivity(irParaOFormulario);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {


                aluno = (Aluno) adapter.getItemAtPosition(posicao);
                return false;
            }
        });




    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CICLO DE VIDA", "onResume");


        carregaLista();
    }

    private void carregaLista() {
        ListView lista = (ListView) findViewById(R.id.lista);
        //Array de String com nomes para adicionar a lista.
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos =  dao.getLista();
        AlunoAdapter adapter = new AlunoAdapter(alunos, this);

        lista.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_lista_alunos, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())  {
            case R.id.novo:
                Intent irParaFormulario = new Intent(this, FormularioActivyt.class);
                startActivity(irParaFormulario);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        MenuItem ligar = menu.add("Ligar");
        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                /*Intent irParaATelaDeDiscagem =  new Intent(Intent.ACTION_CALL);
                Uri telefoneDoAluno = Uri.parse("tel:" + aluno.getTelefone());
                irParaATelaDeDiscagem.setData(telefoneDoAluno);
                startActivity(irParaATelaDeDiscagem);*/

                Uri uri = Uri.parse("tel:" + aluno.getTelefone());
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);


                return false;
            }
        });






        menu.add("Enviar SMS");
        menu.add("Achar no Mapa");
        MenuItem Site = menu.add("Navegar no site");
        Site.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {



                Uri uri = Uri.parse("http://facebook.com/" + aluno.getSite());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                return true;
            }
        });


        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivyt.this);
                dao.deletar(aluno);
                dao.close();
                carregaLista();
                Toast.makeText(ListaAlunosActivyt.this, "Aluno Deletado! ",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        menu.add("Enviar E-Mail");

    }
}