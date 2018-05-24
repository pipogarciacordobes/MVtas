package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Trxs.
 */
@Entity
@Table(name = "trxs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Trxs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "pv_data")
    private String pvData;

    @Column(name = "id_pv")
    private Long idPV;

    @OneToMany(mappedBy = "trxs")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PV> idPVS = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPvData() {
        return pvData;
    }

    public Trxs pvData(String pvData) {
        this.pvData = pvData;
        return this;
    }

    public void setPvData(String pvData) {
        this.pvData = pvData;
    }

    public Long getIdPV() {
        return idPV;
    }

    public Trxs idPV(Long idPV) {
        this.idPV = idPV;
        return this;
    }

    public void setIdPV(Long idPV) {
        this.idPV = idPV;
    }

    public Set<PV> getIdPVS() {
        return idPVS;
    }

    public Trxs idPVS(Set<PV> pVS) {
        this.idPVS = pVS;
        return this;
    }

    public Trxs addIdPV(PV pV) {
        this.idPVS.add(pV);
        pV.setTrxs(this);
        return this;
    }

    public Trxs removeIdPV(PV pV) {
        this.idPVS.remove(pV);
        pV.setTrxs(null);
        return this;
    }

    public void setIdPVS(Set<PV> pVS) {
        this.idPVS = pVS;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Trxs trxs = (Trxs) o;
        if (trxs.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trxs.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Trxs{" +
            "id=" + getId() +
            ", pvData='" + getPvData() + "'" +
            ", idPV=" + getIdPV() +
            "}";
    }
}
