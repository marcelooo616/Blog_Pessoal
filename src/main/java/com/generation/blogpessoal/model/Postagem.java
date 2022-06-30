package com.generation.blogpessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

//@Entity transforma o objeto de postagem em uma tabela no banco de dados
@Entity

//@Table da o nome para a tabela no banco de dados
@Table(name = "tb_postagem")

public class Postagem {
	//@Id define a chave primaria (PRIMARY KEY)
	@Id
	
	//@GeneratedValue fara o auto_increment no id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;   
	
	//@NotNull indica o titulo como campo abrigatorio
	@NotNull
	
	//@Size indica a quantitade de caractere do titulo minimo de 5 e no maximo 100
	@Size(min = 5 , max = 100)
	private String titulo;  
	
	
	@NotNull
	@Size(min = 10, max = 500)
	private String texto; 
	
	// pega automaticamente hora e data do seu computador
	
	//@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date date =  new java.sql.Date(System.currentTimeMillis());   	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
