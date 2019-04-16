package CategoryManagement;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import com.usermanagement.resource.CategoryResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListCategoryManagementApplicationTests {

	@Mock
	CategoryResource categoryResource;

	@Test
	public void listCategories() {
		assertEquals(HttpStatus.OK, categoryResource.listCategories().getStatusCode());
	}

}
