package com.prod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prod.entities.Produto;
import com.prod.services.ProdServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Produtos", description = "API REST DE GERENCIAMENTO DE PRODUTOS")
@RestController
@RequestMapping("/produto")
public class ProdController {
	
	private final ProdServico prodServico;
	
	@Autowired
	public ProdController(ProdServico prodServico) {
		this.prodServico = prodServico;
	}
	@GetMapping("/{id}")
	@Operation (summary = "Localiza produto por ID")
	public ResponseEntity<Produto> buscaProdutoControlId(@PathVariable Long id){
		Produto produto = prodServico.buscaProdutoId(id);
		if(produto != null) {
			return ResponseEntity.ok(produto);
		}
		else{
			return ResponseEntity.notFound().build();
	
		}
	}
	@GetMapping("/")
	@Operation (summary = "Apresenta todos os produtos")
	public ResponseEntity<List<Produto>> buscaTodosProdutosControl(){
		List<Produto> produtos = prodServico.buscaTodosProdutos();
		return ResponseEntity.ok(produtos);
	}
	@PostMapping("/")
	@Operation (summary = "Cadastra um produto")
	public ResponseEntity<Produto> salvaProdutosControl (@RequestBody @Valid Produto produto){
		Produto salvaProduto = prodServico.salvaProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaProduto);
	}
	@PutMapping("/{id}")
	@Operation (summary = "Altera produto")
	public ResponseEntity<Produto> alteraProdutoControl (@PathVariable Long id, @RequestBody @Valid Produto produto){
		Produto alteraProduto = prodServico.alterarProduto(id, produto);
		if(alteraProduto != null) {
			return ResponseEntity.ok(produto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Exclui produto")
	public ResponseEntity<Produto> apagaProdutoControl (@PathVariable Long id){
		boolean apagar = prodServico.apagarProduto(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}

