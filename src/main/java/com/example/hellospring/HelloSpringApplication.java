package com.example.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// @service, @repository, @component, @Controller 와 같은 것들은 우리가 실행하는 어플리케이션과 같은 패키지 혹은 하위 패키지에 있는 녀석들만
// spring이 자동으로 container에 등록해준다
// 그러니까 쌩뚱맞게 java 하위폴더에 저런 어노테이션을 써도 안된다.
// 그리고 기본으로 싱글톤으로 등록한다. 내가 궁금한 부분이 여기서 해결된다. 모두 같은 인스턴스인것이다.

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
