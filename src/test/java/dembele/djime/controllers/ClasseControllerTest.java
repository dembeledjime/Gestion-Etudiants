//package dembele.djime.controllers;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import com.siglab.controllers.ClasseController;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(ClasseController.class)
//public class ClasseControllerTest {
//	@Autowired
//	MockMvc mockMvc;
//	@Test
//	public void home() throws Exception {
//		mockMvc.perform(get("/"))
//		.andExpect((ResultMatcher) ((ResultActions) status().isOk())
//				.andExpect((ResultMatcher) content().string(containsString("index"))));
//	}
//}
