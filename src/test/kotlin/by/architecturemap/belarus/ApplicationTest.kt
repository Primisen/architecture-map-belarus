package by.architecturemap.belarus

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [Application::class])
@ActiveProfiles("test")
class ApplicationTest {

    @Test
    fun contextLoads() {
        main(emptyArray())
    }
}
