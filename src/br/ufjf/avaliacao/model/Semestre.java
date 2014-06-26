package br.ufjf.avaliacao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * DTO da Tabela {@code Disciplina} contém os atributos e relacionamentos da
 * mesma.
 * 
 */
@Entity
@Table(name = "Semestre")
public class Semestre implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Campo com ID da disciplina. Relaciona com a coluna {@code idSemestre}
	 * do banco e é gerado por autoincrement do MySQL através das anotações
	 * {@code @GeneratedValue(generator = "increment")} e
	 * {@code @GenericGenerator(name = "increment", strategy = "increment")}
	 * 
	 */
	@Id
	@Column(name = "idSemestre", unique = true, nullable = false)
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int idSemestre;

	/**
	 * Campo com o nome do semestre. Relaciona com a coluna
	 * {@code nomeSemestre} do banco através da anotação
	 * {@code @Column(name = "nomeSemestre", length = 45, nullable = false)}.
	 */
	@Column(name = "nome", length = 45, nullable = false)
	private String nomeSemestre;

	
	/**
	 * Campo com a data final do semestre. Relaciona com a coluna
	 * {@code dataFinal} do banco através da anotação
	 * {@code @Column(name = "dataFinal", nullable = false)}.
	 */
	@Column(name = "dataFinal", nullable = false)
	private Date dataFinalSemestre;

	@Transient
	private boolean editingStatus;

	public Semestre() {

	}

	public int getIdSemestre() {
		return idSemestre;
	}


	public void setIdSemestre(int idSemestre) {
		this.idSemestre = idSemestre;
	}


	public String getNomeSemestre() {
		return nomeSemestre;
	}


	public void setNomeSemestre(String nomeSemestre) {
		this.nomeSemestre = nomeSemestre;
	}


	public Date getDataFinalSemestre() {
		return dataFinalSemestre;
	}

	public void setDataFinalSemestre(Date dataFinalSemestre) {
		this.dataFinalSemestre = dataFinalSemestre;
	}

	

}