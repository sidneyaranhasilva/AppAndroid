package br.com.caelum.cadastro2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import br.com.caelum.cadastro2.br.com.caelum.cadastro2.dao.AlunoDAO;
import br.com.caelum.cadastro2.br.com.caelum.cadastro2.modelo.Aluno;

/**
 * Created by SIDNEY on 26/01/2016.
 */
public class FormularioActivyt extends Activity {

    private FormularioHelper helper;
    private Button btnGravar;
    private String caminhoArquivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        helper = new FormularioHelper(this);

        final Aluno alunoParaAlteracao = (Aluno) getIntent().getSerializableExtra("alunoSelecionado");


        if(alunoParaAlteracao != null) {
            helper.colocaAlunoNoFormulario(alunoParaAlteracao);
        }





        btnGravar = (Button) findViewById(R.id.botao);




        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Aluno aluno = helper.pegaAlunoFormulario();


                AlunoDAO dao = new AlunoDAO(FormularioActivyt.this);


                if(alunoParaAlteracao != null){

                    aluno.setId(alunoParaAlteracao.getId());
                    btnGravar.setText("Alterar");
                    dao.atualizar(aluno);

                }else {

                    dao.insere(aluno);

                }

                dao.close();



                Toast.makeText(FormularioActivyt.this, "Aluno " + aluno.getNome() + " Gravado com sucesso   ",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        });


        ImageView foto = helper.getFoto();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent irParaACamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoArquivo = getExternalFilesDir(null) + "/" + System.currentTimeMillis()   + "1.png";
                File arquivo = new File(caminhoArquivo);

                Uri localFoto = Uri.fromFile(arquivo);
                irParaACamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);

                startActivityForResult(irParaACamera, 123);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 123){
            if(resultCode == Activity.RESULT_OK){
                helper.carregaImagem(caminhoArquivo);
            }else {
                caminhoArquivo = null;
            }

        }

    }
}
