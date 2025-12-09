package grupo2.com.ecoPoint.dto;

import java.util.List;

public class SolicitacaoDTO {
    private Long geradoraId;
    private Long coletoraId;
    private String dataAgendada;
    private List<Long> itensIds;

    public Long getGeradoraId() {
        return geradoraId;
    }

    public void setGeradoraId(Long geradoraId) {
        this.geradoraId = geradoraId;
    }

    public Long getColetoraId() {
        return coletoraId;
    }

    public void setColetoraId(Long coletoraId) {
        this.coletoraId = coletoraId;
    }

    public String getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(String dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public List<Long> getItensIds() {
        return itensIds;
    }

    public void setItensIds(List<Long> itensIds) {
        this.itensIds = itensIds;
    }
}
