package br.com.empiricus.testejuliojr.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_emails")
public class Emails {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		@Column(unique = true, nullable = false)
		@Size(max = 150)
		private String email;
		
		
		private LocalDate data_criacao;
		
		
		private LocalDate data_atualizacao;
		
		@ManyToOne
		@JoinColumn(name = "user_id", nullable = false)
		@JsonIgnoreProperties("emails")
		private Usuarios usuarios;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public LocalDate getData_criacao() {
			return data_criacao;
		}

		public void setData_criacao(LocalDate data_criacao) {
			this.data_criacao = data_criacao;
		}

		public LocalDate getData_atualizacao() {
			return data_atualizacao;
		}

		public void setData_atualizacao(LocalDate data_atualizacao) {
			this.data_atualizacao = data_atualizacao;
		}

		public Usuarios getUsuarios() {
			return usuarios;
		}

		public void setUsuarios(Usuarios usuarios) {
			this.usuarios = usuarios;
		}

		
}
