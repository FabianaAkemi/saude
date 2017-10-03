package com.tcc.saude.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by akemi on 02/03/17.
 */
@Entity
@Table(name = "triagem")
public class Triagem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//muitos para muitos
	@NotBlank
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@NotBlank
	@Column(name = "dor_cabeca")
	private Boolean dorCabeca;

	@NotBlank
	private Boolean articulacao;

	@NotBlank
	@Column(name = "dor_corpo")
	private Boolean dorCorpo;

	@NotBlank
	private Boolean calafrio;

	@NotBlank
	private Boolean sonolencia;

	@NotBlank
	private Boolean renal;

	@NotBlank
	private Boolean motor;
	
	@NotBlank
	private Boolean hepatite;

	@NotBlank
	private Boolean diabetes;

	@NotBlank
	private Boolean hipertensao;
	
	@NotBlank
	private Boolean deficiencia;

	@NotBlank
	private Boolean febre;
	
	@NotBlank
	@Column(name = "temperatura_febre")
	private String temperaturaFebre;

	@NotBlank
	@Column(name = "problema_respiratorio")
	private Boolean problemaRespiratorio;

	@Column(name = "problema_repiracao_ex")
	private String problemaRespiratorioEx;

	@NotBlank
	@Column(name = "problema_neurologico")
	private Boolean problemaNeurologico;

	@Column(name = "problema_neurologico_ex")
	private String problemaNeurologicoEx;

	@NotBlank
	@Column(name = "problema_cardiaco")
	private Boolean problemaCardiaco;

	@Column(name = "problema_cardiaco_ex")
	private String problemaCardiacoEx;

	@NotBlank
	private Boolean dst;

	@Column(name = "dst_ex")
	private String dstEx;

	@NotBlank
	private Boolean cancer;

	@Column(name = "cancer_ex")
	private String cancerEx;

	@NotBlank
	private Boolean alergia;

	@Column(name = "alergia_ex")
	private String alergiaEx;

	@NotBlank
	private Boolean medicacao;

	@Column(name = "medicacao_ex")
	private String medicacaoEx;

	@NotBlank
	private Boolean outros;

	@Column(name = "outros_ex")
	private String outrosEx;
	
	@Column(name = "data_criacao")
	private Date dataCriacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Boolean getDorCabeca() {
		return dorCabeca;
	}

	public void setDorCabeca(Boolean dorCabeca) {
		this.dorCabeca = dorCabeca;
	}

	public Boolean getArticulacao() {
		return articulacao;
	}

	public void setArticulacao(Boolean articulacao) {
		this.articulacao = articulacao;
	}

	public Boolean getDorCorpo() {
		return dorCorpo;
	}

	public void setDorCorpo(Boolean dorCorpo) {
		this.dorCorpo = dorCorpo;
	}

	public Boolean getCalafrio() {
		return calafrio;
	}

	public void setCalafrio(Boolean calafrio) {
		this.calafrio = calafrio;
	}

	public Boolean getFebre() {
		return febre;
	}

	public void setFebre(Boolean febre) {
		this.febre = febre;
	}

	public String getTemperaturaFebre() {
		return temperaturaFebre;
	}

	public void setTemperaturaFebre(String temperaturaFebre) {
		this.temperaturaFebre = temperaturaFebre;
	}

	public Boolean getSonolencia() {
		return sonolencia;
	}

	public void setSonolencia(Boolean sonolencia) {
		this.sonolencia = sonolencia;
	}

	public Boolean getProblemaRespiratorio() {
		return problemaRespiratorio;
	}

	public void setProblemaRespiratorio(Boolean problemaRespiratorio) {
		this.problemaRespiratorio = problemaRespiratorio;
	}

	public String getProblemaRespiratorioEx() {
		return problemaRespiratorioEx;
	}

	public void setProblemaRespiratorioEx(String problemaRespiratorioEx) {
		this.problemaRespiratorioEx = problemaRespiratorioEx;
	}

	public Boolean getProblemaNeurologico() {
		return problemaNeurologico;
	}

	public void setProblemaNeurologico(Boolean problemaNeurologico) {
		this.problemaNeurologico = problemaNeurologico;
	}

	public String getProblemaNeurologicoEx() {
		return problemaNeurologicoEx;
	}

	public void setProblemaNeurologicoEx(String problemaNeurologicoEx) {
		this.problemaNeurologicoEx = problemaNeurologicoEx;
	}

	public Boolean getProblemaCardiaco() {
		return problemaCardiaco;
	}

	public void setProblemaCardiaco(Boolean problemaCardiaco) {
		this.problemaCardiaco = problemaCardiaco;
	}

	public String getProblemaCardiacoEx() {
		return problemaCardiacoEx;
	}

	public void setProblemaCardiacoEx(String problemaCardiacoEx) {
		this.problemaCardiacoEx = problemaCardiacoEx;
	}

	public Boolean getRenal() {
		return renal;
	}

	public void setRenal(Boolean renal) {
		this.renal = renal;
	}

	public Boolean getMotor() {
		return motor;
	}

	public void setMotor(Boolean motor) {
		this.motor = motor;
	}

	public Boolean getDst() {
		return dst;
	}

	public void setDst(Boolean dst) {
		this.dst = dst;
	}

	public String getDstEx() {
		return dstEx;
	}

	public void setDstEx(String dstEx) {
		this.dstEx = dstEx;
	}

	public Boolean getCancer() {
		return cancer;
	}

	public void setCancer(Boolean cancer) {
		this.cancer = cancer;
	}

	public String getCancerEx() {
		return cancerEx;
	}

	public void setCancerEx(String cancerEx) {
		this.cancerEx = cancerEx;
	}

	public Boolean getHepatite() {
		return hepatite;
	}

	public void setHepatite(Boolean hepatite) {
		this.hepatite = hepatite;
	}

	public Boolean getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(Boolean diabetes) {
		this.diabetes = diabetes;
	}

	public Boolean getHipertensao() {
		return hipertensao;
	}

	public void setHipertensao(Boolean hipertensao) {
		this.hipertensao = hipertensao;
	}

	public Boolean getAlergia() {
		return alergia;
	}

	public void setAlergia(Boolean alergia) {
		this.alergia = alergia;
	}

	public String getAlergiaEx() {
		return alergiaEx;
	}

	public void setAlergiaEx(String alergiaEx) {
		this.alergiaEx = alergiaEx;
	}

	public Boolean getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(Boolean deficiencia) {
		this.deficiencia = deficiencia;
	}

	public Boolean getMedicacao() {
		return medicacao;
	}

	public void setMedicacao(Boolean medicacao) {
		this.medicacao = medicacao;
	}

	public String getMedicacaoEx() {
		return medicacaoEx;
	}

	public void setMedicacaoEx(String medicacaoEx) {
		this.medicacaoEx = medicacaoEx;
	}

	public Boolean getOutros() {
		return outros;
	}

	public void setOutros(Boolean outros) {
		this.outros = outros;
	}

	public String getOutrosEx() {
		return outrosEx;
	}

	public void setOutrosEx(String outrosEx) {
		this.outrosEx = outrosEx;
	}
	

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triagem other = (Triagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}