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
 * A PV.
 */
@Entity
@Table(name = "pv")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PV implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "pv_numero")
    private String pvNumero;

    @Column(name = "id_comercio")
    private Long idComercio;

    @OneToMany(mappedBy = "pV")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comercios> idComercios = new HashSet<>();

    @ManyToOne
    private Trxs trxs;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPvNumero() {
        return pvNumero;
    }

    public PV pvNumero(String pvNumero) {
        this.pvNumero = pvNumero;
        return this;
    }

    public void setPvNumero(String pvNumero) {
        this.pvNumero = pvNumero;
    }

    public Long getIdComercio() {
        return idComercio;
    }

    public PV idComercio(Long idComercio) {
        this.idComercio = idComercio;
        return this;
    }

    public void setIdComercio(Long idComercio) {
        this.idComercio = idComercio;
    }

    public Set<Comercios> getIdComercios() {
        return idComercios;
    }

    public PV idComercios(Set<Comercios> comercios) {
        this.idComercios = comercios;
        return this;
    }

    public PV addIdComercio(Comercios comercios) {
        this.idComercios.add(comercios);
        comercios.setPV(this);
        return this;
    }

    public PV removeIdComercio(Comercios comercios) {
        this.idComercios.remove(comercios);
        comercios.setPV(null);
        return this;
    }

    public void setIdComercios(Set<Comercios> comercios) {
        this.idComercios = comercios;
    }

    public Trxs getTrxs() {
        return trxs;
    }

    public PV trxs(Trxs trxs) {
        this.trxs = trxs;
        return this;
    }

    public void setTrxs(Trxs trxs) {
        this.trxs = trxs;
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
        PV pV = (PV) o;
        if (pV.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pV.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PV{" +
            "id=" + getId() +
            ", pvNumero='" + getPvNumero() + "'" +
            ", idComercio=" + getIdComercio() +
            "}";
    }
}
