package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the PV entity.
 */
public class PVDTO implements Serializable {

    private Long id;

    private String pvNumero;

    private Long idComercio;

    private Long trxsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPvNumero() {
        return pvNumero;
    }

    public void setPvNumero(String pvNumero) {
        this.pvNumero = pvNumero;
    }

    public Long getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(Long idComercio) {
        this.idComercio = idComercio;
    }

    public Long getTrxsId() {
        return trxsId;
    }

    public void setTrxsId(Long trxsId) {
        this.trxsId = trxsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PVDTO pVDTO = (PVDTO) o;
        if(pVDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pVDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PVDTO{" +
            "id=" + getId() +
            ", pvNumero='" + getPvNumero() + "'" +
            ", idComercio=" + getIdComercio() +
            "}";
    }
}
