package br.com.planningtravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

import br.com.planningtravel.enums.RoleName;

@Entity
@Table(name = "roles")
public class Role extends EntityBase<Long> {

	private static final long serialVersionUID = 7003096215309580996L;

	@NaturalId
	@Enumerated(EnumType.STRING)
	@NotNull(message="Nome é obrigatório")
	@Length(min=3, max=50, message="Nome deve ter entre 3 e 60 caracteres")
	@Column(nullable=false, length=60, unique=true)
	private RoleName name;

    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

}