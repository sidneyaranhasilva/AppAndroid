package br.com.caelum.cadastro2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.cadastro2.br.com.caelum.cadastro2.dao.AlunoDAO;

/**
 * Created by SIDNEY on 20/03/2016.
 */
public class SMSReceiver  extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
       Object[] mensagens = (Object[]) intent.getExtras().get("pdus");

        byte[] mensagem = (byte[]) mensagens[0];

        SmsMessage sms = SmsMessage.createFromPdu(mensagem);


        String telefone = sms.getOriginatingAddress();

        Boolean smsEhDeAluno = new AlunoDAO(context).ehAluno(telefone);

        if(smsEhDeAluno){
            MediaPlayer musica =  MediaPlayer.create(context, R.raw.banda);
            musica.start();
        }else{
            Toast.makeText(context, "Nova mensagem", Toast.LENGTH_SHORT).show();
        }

    }
}
