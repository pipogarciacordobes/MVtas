package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Trxs entity.
 */
public class TrxsDTO implements Serializable {

    private Long id;

    private String pvData;

    private Long idPV;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPvData() {
        return pvData;
    }

    public void setPvData(String pvData) {
        this.pvData = pvData;
    }

    public Long getIdPV() {
        return idPV;
    }

    public void setIdPV(Long idPV) {
        this.idPV = idPV;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrxsDTO trxsDTO = (TrxsDTO) o;
        if(trxsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trxsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrxsDTO{" +
            "id=" + getId() +
            ", pvData='" + getPvData() + "'" +
            ", idPV=" + getIdPV() +
            "}";
    }
}
