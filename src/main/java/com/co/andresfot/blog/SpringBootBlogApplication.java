package com.co.andresfot.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.co.andresfot.blog.model.service.IUploadFileService;

@SpringBootApplication
public class SpringBootBlogApplication implements CommandLineRunner{

	@Autowired
	IUploadFileService uploadService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadService.deleteAll();
		uploadService.init();
	}

}
