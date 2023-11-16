package com.prod.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prod.entities.Produto;
import com.prod.repository.ProdRepository;

@Service
public class ProdServico {
	
	private final ProdRepository prodRepository;
	
	@Autowired
	public ProdServico (ProdRepository prodRepository) {
		this.prodRepository = prodRepository;
	}
	public List<Produto> buscaTodosProdutos(){
		return prodRepository.findAll();
		
	}
	public Produto buscaProdutoId(Long id) {
		Optional<Produto> Produto = prodRepository.findById(id);
		return Produto.orElse(null);
	}
	public Produto alterarProduto(Long id, Produto alterarProduto) {
		Optional <Produto> existeProduto = prodRepository.findById(id);
		if(existeProduto.isPresent()) {
			alterarProduto.setId(id);
			return prodRepository.save(alterarProduto);
		}
		return null;
	}
	public boolean apagarProduto(Long id) {
		Optional <Produto> existeProduto = prodRepository.findById(id);
		if(existeProduto.isPresent()) {
			prodRepository.deleteById(id);
			return true;
		}
		return false;
	}
	public Produto salvaProduto(Produto produto) {
		return prodRepository.save(produto);
	}

}
