package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")

public class PostagemController {
	//faz a injeção de dependencia 
	@Autowired
	private PostagemRepository repository;
	// traz toda a informação da lista
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Postagem> inserirPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> atualizaPostagem(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.save(postagem));
	}
	// faz a busca pelo id
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> buscaPorId(@PathVariable long id){
		return repository.findById(id). map(resp -> ResponseEntity.ok(resp)). orElse(ResponseEntity.notFound().build());
	}
	// faz a busca pelo titulo com uma sub rota para não ter duplicidade
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> buscaPeloTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo)); 
	}
	//deletando uma infromação pelo id
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	}


