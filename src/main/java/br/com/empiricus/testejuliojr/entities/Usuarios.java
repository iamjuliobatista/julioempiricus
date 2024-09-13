package br.com.empiricus.testejuliojr.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuarios {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		
		@Column(nullable = false)
		@Size(min = 2, max = 100)
		private String nome;
		
		@Column(unique = true, nullable = false)
		@Size(min = 11, max = 11)
		private String cpf;
		
		@Column(nullable = false)
		@Size(min = 8, max = 32)
		private String senha;
		
		private LocalDate data_criacao;
		
		
		private LocalDate data_atualizacao;
		
		@Column(nullable = false)
		private boolean admin1;
		
		@OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
		@JsonIgnoreProperties("usuarios")
		@JsonIgnore
		private List<Emails> emails;
		
		@JsonIgnore
		@OneToOne(mappedBy = "usuarios", cascade = CascadeType.ALL)
		private Uuid uuid;
		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
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

		public boolean isAdmin1() {
			return admin1;
		}

		public void setAdmin1(boolean admin1) {
			this.admin1 = admin1;
		}

		public List<Emails> getEmails() {
			return emails;
		}

		public void setEmails(List<Emails> emails) {
			this.emails = emails;
		}

		public Uuid getUuid() {
			return uuid;
		}

		public void setUuid(Uuid uuid) {
			this.uuid = uuid;
		}
		
}
