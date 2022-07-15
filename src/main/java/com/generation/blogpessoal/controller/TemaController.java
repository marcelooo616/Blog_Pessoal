package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TemaController {
	@Autowired
	private TemaRepository temaRepository;
	
	//construindo andpoits
	//@GetMapping ira traze toda a infomação relacionada ao tema buscado no banco de dados
	@GetMapping
	public ResponseEntity<List<Tema>> listaDeTema(){
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	@GetMapping ("/{id}")
    public ResponseEntity<Tema> buscaId(@PathVariable long id){
		return temaRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> buscaNome (@PathVariable String nome){
		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(nome));
	}
	
	
	@PostMapping
	public ResponseEntity<Tema> inserirTema(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
		
	}
	@PutMapping
	public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema) {
					
		return temaRepository.findById(tema.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(temaRepository.save(tema));
				})
				.orElse(ResponseEntity.notFound().build());

	}
	@DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
		temaRepository.deleteById(id);
	}
	
	

}
