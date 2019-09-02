package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.model.todoModel;
import com.example.demo.repository.todoRepository;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/data")
public class todoController {

	@Autowired
	todoRepository todoRepository;
	
	@GetMapping("/todo")
	public List<todoModel> getAllTodoData(){
		return todoRepository.findAll();
		
	}
	@PostMapping("/todo")
	public todoModel createTask(@RequestBody todoModel todoModel) {
		todoModel.setComplete(false);
		return todoRepository.save(todoModel);
		
	}
	
	@GetMapping(value="/todo/{id}")
	public ResponseEntity<todoModel> getTaskById(@PathVariable("id") int id){
		return todoRepository.findById(id).map(todoModel->ResponseEntity.ok().body(todoModel)).orElse(ResponseEntity.notFound().build());
	}
	 @PutMapping(value="/todo/{id}")
	    public ResponseEntity<todoModel> updateTodo(@PathVariable("id") int id,
	                                           @Valid @RequestBody todoModel todoModel) {
	        return todoRepository.findById(id)
	                .map(todoData -> {
	                    todoData.setTaskTitle(todoModel.getTaskTitle());
	                    todoData.setComplete(todoModel.getComplete());
	                    todoModel updatedTodo = todoRepository.save(todoData);
	                    return ResponseEntity.ok().body(updatedTodo);
	                }).orElse(ResponseEntity.notFound().build());
	    }
	    @DeleteMapping(value="/todos/{id}")
	    public ResponseEntity<?> deleteTodo(@PathVariable("id") int id) {
	        return todoRepository.findById(id)
	                .map(todo -> {
	                    todoRepository.deleteById(id);
	                    return ResponseEntity.ok().build();
	                }).orElse(ResponseEntity.notFound().build());
	    }
}
