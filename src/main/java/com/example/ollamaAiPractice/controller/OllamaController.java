package com.example.ollamaAiPractice.controller;

import jakarta.annotation.Resource;


import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {
	
	@Resource
    private OllamaChatModel ollamaChatModel;

    @RequestMapping(value = "/ai/ollama")
    public Object ollama(@RequestParam(value = "msg")String msg){
        ChatResponse chatResponse=ollamaChatModel.call(new Prompt(msg, OllamaOptions.create()
                .withModel("gemma2:latest")//指定使用哪个大模型
                .withTemperature((double) 0.5F)));
        System.out.println(chatResponse.getResult().getOutput().getContent());
        return chatResponse.getResult().getOutput().getContent();
    }
    
    @PostMapping("/query")
    public ResponseEntity<String> query(@RequestBody String prompt) {
        // 处理逻辑
        return ResponseEntity.ok("Ollama response here");
    }

}
