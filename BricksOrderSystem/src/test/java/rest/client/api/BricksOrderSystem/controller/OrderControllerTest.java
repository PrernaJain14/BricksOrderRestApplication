package rest.client.api.BricksOrderSystem.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import rest.client.api.BricksOrderSystem.model.BrickOrder;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class, secure = false)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderController orderController;

	BrickOrder mockOrder = new BrickOrder(1,688);

	String exampleOrderJson = "{ \"id\":\"1\",\"noOfBricks\": \"688\",\"shipped\":\"false\"}" ;
	

	String updateOrderJson = "{ \"id\":\"1\",\"noOfBricks\": \"68856\",\"shipped\":\"false\"}" ;

	@Test
	public void createNewOrder() throws Exception {

		Mockito.when(
				orderController.createNewOrder(Mockito.any(BrickOrder.class))).thenReturn(mockOrder);

		RequestBuilder requestBuilder =MockMvcRequestBuilders
				.post("/order")
				.accept(MediaType.APPLICATION_JSON).content(exampleOrderJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,noOfBricks:688,shipped:false}";

		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void getOrderDetails() throws Exception {

		Mockito.when(
				orderController.getOneOrder(Mockito.anyLong())).thenReturn(mockOrder);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getOrder/{id}",1).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,noOfBricks:688,shipped:false}";

		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}

	@Test
	public void updateOrder() throws Exception {

		BrickOrder mockOrder = new BrickOrder(1,68856);
		
		Mockito.when(
				orderController.editOneOrder(Mockito.anyLong(),Mockito.any(BrickOrder.class))).thenReturn(mockOrder);

		RequestBuilder requestBuilder2 =MockMvcRequestBuilders
				.post("/updateOrder/{id}",1)
				.accept(MediaType.APPLICATION_JSON).content(updateOrderJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result2 = mockMvc.perform(requestBuilder2).andReturn();

		System.out.println(result2.getResponse());
		String expected = "{id:1,noOfBricks:68856,shipped:false}";

		
		JSONAssert.assertEquals(expected, result2.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void fulfilOrder() throws Exception {
		BrickOrder mockOrder = new BrickOrder(1,688);
		mockOrder.setShipped(true);
		Mockito.when(
				orderController.fulfilOrder(Mockito.anyLong())).thenReturn(mockOrder);

		RequestBuilder requestBuilder =MockMvcRequestBuilders
				.post("/fulfilOrder/{id}",1)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result2 = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result2.getResponse());
		String expected = "{id:1,noOfBricks:688,shipped:true}";

		
		JSONAssert.assertEquals(expected, result2.getResponse()
				.getContentAsString(), false);
	}

}
