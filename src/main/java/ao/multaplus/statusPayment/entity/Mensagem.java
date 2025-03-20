package ao.multaplus.statusPayment.entity;

import org.springframework.stereotype.Component;

@Component
public class Mensagem {
    private String mensagem;

    public String getmensagem() {
        return mensagem;
    }

    public void setmensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
