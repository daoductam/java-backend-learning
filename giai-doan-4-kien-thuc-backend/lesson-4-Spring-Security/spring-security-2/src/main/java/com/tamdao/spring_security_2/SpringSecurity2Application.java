package com.tamdao.spring_security_2;

import com.tamdao.spring_security_2.entity.Article;
import com.tamdao.spring_security_2.entity.Category;
import com.tamdao.spring_security_2.repository.ArticleRepository;
import com.tamdao.spring_security_2.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SpringSecurity2Application {

	static CategoryRepository categoryRepository;
	static ArticleRepository articleRepository;

	static Category createCategory(String name) {
		Category category = Category.builder().name(name).build();
		categoryRepository.save(category);
		log.info("created category {}={}", category.getId(), category.getName());
		return category;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringSecurity2Application.class, args);

		categoryRepository = context.getBean(CategoryRepository.class);
		articleRepository = context.getBean(ArticleRepository.class);

		try {
			Category entertainmentCategory = createCategory("Entertainment");
			Category sportCategory = createCategory("Sport");

			articleRepository
					.save(Article.builder().url("spider-man-live-action")
							.title("Spider-Man: Live-Action")
							.content("...").category(entertainmentCategory).build());

			articleRepository
					.save(Article.builder().url("superman")
							.title("Superman")
							.content("...").category(entertainmentCategory).build());
		} catch (Exception e) {
			log.error("Cannot create seed data");
		}
	}

}
