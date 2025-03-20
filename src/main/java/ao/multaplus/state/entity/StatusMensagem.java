package ao.multaplus.state.entity;

import org.springframework.stereotype.Component;

@Component
public class StatusMensagem {
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

