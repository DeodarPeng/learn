import cc.learn.controller.UserController;
import cc.learn.service.UserService;
import cc.learn.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;


import cc.learn.controller.PublicController;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PublucControllerTest {
    @Test
    public void testIndexPage() throws Exception {
        PublicController publicController = new PublicController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(publicController).build();
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }

    @Test
    public void listAllUserTest() throws Exception {
        UserService userService = new UserServiceImpl();
        UserService mockService = mock(UserService.class);
        when(mockService.listAllUser()).thenReturn(userService.listAllUser());
        UserController userController = new UserController();
        MockMvc mockMvc = standaloneSetup(userController).build();
        mockMvc.perform(get("/user/getAllUser"))
                .andExpect(view().name("userList"))
                .andExpect(model().attributeExists("userList"));

    }

}
