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

@RestController
@RequestMapping("/api/tools")
public class ToolController {
	
	@Autowired
	private ToolRepository toolRepository;
	
	@Autowired
	private ToolService toolService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tool addTool(@Valid @RequestBody Tool tool){
		
		return toolRepository.save(tool);
	}
	
	@GetMapping("/")
	public  ResponseEntity<List<Tool>> findAll(){
		
		return ResponseEntity.ok(toolRepository.findAll());
	}
	
	@GetMapping("/{toolId}")
	public ResponseEntity<Tool> findById(@PathVariable("toolId") Long toolId){
		
		Optional<Tool> tool = toolRepository.findById(toolId);
		if(tool.isPresent()){
			return ResponseEntity.ok(tool.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public  ResponseEntity<List<Tool>> findByTag(@RequestParam(value="tag") String tag){

		List<Tool> tools = toolService.filterByTag(tag);
		return ResponseEntity.ok(tools);
	}
	
	@GetMapping("/bytitle")
	public  ResponseEntity<List<Tool>> findByTitle(@RequestParam(value="title") String title){

		List<Tool> tools = toolRepository.findByTitleContaining(title);
		return ResponseEntity.ok(tools);
	}
	
	@PutMapping("/{toolId}")
	public ResponseEntity<Tool> updateTool(@Valid @PathVariable("toolId") Long toolId, @RequestBody Tool tool){
		
		if(!toolRepository.existsById(toolId)){
			return ResponseEntity.notFound().build();
		}
		tool.setId(toolId);		
		return ResponseEntity.ok(toolRepository.save(tool));
	}
	
	@DeleteMapping("/{toolId}")
	public ResponseEntity<Void> deleteTool(@PathVariable("toolId") Long toolId){
		
		if(!toolRepository.existsById(toolId)){
			return ResponseEntity.notFound().build();
		}
		toolRepository.deleteById(toolId);
		return ResponseEntity.noContent().build();
	}

}
