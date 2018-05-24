package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Comercios entity.
 */
public class ComerciosDTO implements Serializable {

    private Long id;

    private String comerciosName;

    private Long pVId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComerciosName() {
        return comerciosName;
    }

    public void setComerciosName(String comerciosName) {
        this.comerciosName = comerciosName;
    }

    public Long getPVId() {
        return pVId;
    }

    public void setPVId(Long pVId) {
        this.pVId = pVId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComerciosDTO comerciosDTO = (ComerciosDTO) o;
        if(comerciosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comerciosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComerciosDTO{" +
            "id=" + getId() +
            ", comerciosName='" + getComerciosName() + "'" +
            "}";
    }
}
