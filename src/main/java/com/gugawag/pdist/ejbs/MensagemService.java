package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;

@Stateless(name = "mensagemService")
@Remote
public class MensagemService {

    @EJB
    private MensagemDAO mensagemDao;

    private static final List<String> PALAVROES = Arrays.asList("palavrao1", "palavrao2", "palavrao3");

    public List<Mensagem> listar() {
        return mensagemDao.listar();
    }

    public void inserir(long id, String mensagem) {
        Mensagem novaMensagem = new Mensagem(id, mensagem);
        mensagemDao.inserir(novaMensagem);

        if (contemPalavrao(mensagem)) {
            throw new RuntimeException("A mensagem não pode conter palavrões!");
        }
    }

    private boolean contemPalavrao(String mensagem) {
        for (String palavrao : PALAVROES) {
            if (mensagem.toLowerCase().contains(palavrao.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
