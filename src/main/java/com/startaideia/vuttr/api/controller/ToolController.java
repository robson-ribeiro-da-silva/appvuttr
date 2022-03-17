package com.startaideia.vuttr.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.startaideia.vuttr.domain.model.Tool;
import com.startaideia.vuttr.domain.repository.ToolRepository;
import com.startaideia.vuttr.domain.service.ToolService;

/**
 * Classe reponsável pela disponibilização dos endpoints da API das Ferramenta
 * @author robso
 *
 */
@RestController
@RequestMapping("/api/tools")
public class ToolController {
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private ToolService toolService;
	
	/**
	 * Método que adiciona uma nova Ferramenta
	 * @param tool - Parâmentro do tipo Ferramenta
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tool addTool(@Valid @RequestBody Tool tool){
		
		return toolRepository.save(tool);
	}
	
	/**
	 * Método que retorna uma lista de todas Ferramentas
	 * @return
	 */
	@GetMapping("/")
	public  ResponseEntity<List<Tool>> findAll(){
		
		return ResponseEntity.ok(toolRepository.findAll());
	}
	
	/**
	 * Método que retorna uma Ferramenta específica pelo identificador
	 * @param toolId - um Long referente ao identificador da Ferramenta
	 * @return
	 */
	@GetMapping("/{toolId}")
	public ResponseEntity<Tool> findById(@PathVariable("toolId") Long toolId){
		
		Optional<Tool> tool = toolRepository.findById(toolId);
		if(tool.isPresent()){
			return ResponseEntity.ok(tool.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Método que retorna uma lista de Ferramentas por uma tag
	 * @param tag - uma String referente a uma tag da Ferramenta
	 * @return
	 */
	@GetMapping
	public  ResponseEntity<List<Tool>> findByTag(@RequestParam(value="tag") String tag){

		List<Tool> tools = toolService.filterByTag(tag);
		return ResponseEntity.ok(tools);
	}
	
	/**
	 * Método que retorna uma lista de Ferramentas pelo titulo
	 * @param title - uma String referente ao titulo da Ferramenta
	 * @return
	 */
	@GetMapping("/bytitle")
	public  ResponseEntity<List<Tool>> findByTitle(@RequestParam(value="title") String title){

		List<Tool> tools = toolRepository.findByTitleContaining(title);
		return ResponseEntity.ok(tools);
	}
	
	/**
	 * Método que atualiza uma Ferramenta existente apartir do identificador
	 * @param tool - Parâmentro do tipo Ferramenta
	 * @param toolId - um Long referente ao identificador da Ferramenta 
	 * @return
	 */
	@PutMapping("/{toolId}")
	public ResponseEntity<Tool> updateTool(@Valid @PathVariable("toolId") Long toolId, @RequestBody Tool tool){
		
		if(!toolRepository.existsById(toolId)){
			return ResponseEntity.notFound().build();
		}
		tool.setId(toolId);		
		return ResponseEntity.ok(toolRepository.save(tool));
	}
	
	/**
	 * Método que remove uma Ferramenta existente apartir do identificador
	 * @param toolId - um Long referente ao identificador da Ferramenta 
	 * @return
	 */
	@DeleteMapping("/{toolId}")
	public ResponseEntity<Void> deleteTool(@PathVariable("toolId") Long toolId){
		
		if(!toolRepository.existsById(toolId)){
			return ResponseEntity.notFound().build();
		}
		toolRepository.deleteById(toolId);
		return ResponseEntity.noContent().build();
	}

}
