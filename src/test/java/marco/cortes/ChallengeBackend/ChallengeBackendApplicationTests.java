package marco.cortes.ChallengeBackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChallengeBackendApplicationTests {

	Calculator c = new Calculator();
	@Test
	void contextLoads() {
		// given
		int a = 20;
		int b = 30;

		// when
		int result = c.add(a, b);

		//then
		assertThat(result).isEqualTo(50);
	}

	class Calculator {
		int add(int a, int b) { return a + b; }
	}

}
