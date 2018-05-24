package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Comercios.
 */
@Entity
@Table(name = "comercios")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Comercios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "comercios_name")
    private String comerciosName;

    @ManyToOne
    private PV pV;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComerciosName() {
        return comerciosName;
    }

    public Comercios comerciosName(String comerciosName) {
        this.comerciosName = comerciosName;
        return this;
    }

    public void setComerciosName(String comerciosName) {
        this.comerciosName = comerciosName;
    }

    public PV getPV() {
        return pV;
    }

    public Comercios pV(PV pV) {
        this.pV = pV;
        return this;
    }

    public void setPV(PV pV) {
        this.pV = pV;
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
        Comercios comercios = (Comercios) o;
        if (comercios.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comercios.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Comercios{" +
            "id=" + getId() +
            ", comerciosName='" + getComerciosName() + "'" +
            "}";
    }
}
