package br.com.caelum.cadastro2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import br.com.caelum.cadastro2.br.com.caelum.cadastro2.modelo.Aluno;

/**
 * Created by SIDNEY on 27/01/2016.
 */
public class FormularioHelper {


    private EditText campoNome;
    private EditText campoSite;
    private EditText campoEndereco;
    private EditText campoTelefone;
    private SeekBar campoNota;
    private Aluno aluno;
    private ImageView foto;



    public FormularioHelper(FormularioActivyt activyt) {

        aluno = new Aluno();

        campoNome = (EditText) activyt.findViewById(R.id.nome);
        campoSite = (EditText) activyt.findViewById(R.id.site);
        campoEndereco = (EditText) activyt.findViewById(R.id.endereco);
        campoTelefone = (EditText) activyt.findViewById(R.id.telefone);
        campoNota = (SeekBar) activyt.findViewById(R.id.nota);
        foto = (ImageView) activyt.findViewById(R.id.foto);

    }


    public Aluno pegaAlunoFormulario(){
        String nome = campoNome.getText().toString();
        String siteAluno = campoSite.getText().toString();
        String enderecoAluno = campoEndereco.getText().toString();
        String telefone = campoTelefone.getText().toString();
        int notaAluno = campoNota.getProgress();



        aluno.setNome(nome);
        aluno.setSite(siteAluno);
        aluno.setEndereco(enderecoAluno);
        aluno.setTelefone(telefone);
        aluno.setNota(Integer.valueOf(notaAluno));

        return aluno;
    }

    public void colocaAlunoNoFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoSite.setText(aluno.getSite());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoNota.setProgress(aluno.getNota());

        if(aluno.getCaminhoFoto() != null){
            carregaImagem(aluno.getCaminhoFoto());
        }

    }



    public ImageView getFoto(){


        return foto;
    }

    public void carregaImagem(String caminhoArquivo) {
        aluno.setCaminhoFoto(caminhoArquivo);

        Bitmap imagem = BitmapFactory.decodeFile(caminhoArquivo);
        Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 200, 200, true);

        foto.setImageBitmap(imagemReduzida);
    }
}
