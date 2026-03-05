package com.toDoList;

import com.toDoList.entity.ToDoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ToDoListApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {

		var toDoEntity = new ToDoEntity("todo 1", "Desc todo 1", false, 1);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(toDoEntity)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(toDoEntity.getNome())
				.jsonPath("$[0].descricao").isEqualTo(toDoEntity.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(toDoEntity.getRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(toDoEntity.getPrioridade());
	}

	@Test
	void testCreateTodoFail() {

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(new ToDoEntity("", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}
}