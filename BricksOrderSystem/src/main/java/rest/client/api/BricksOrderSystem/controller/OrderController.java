package rest.client.api.BricksOrderSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import rest.client.api.BricksOrderSystem.model.BrickOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class OrderController {
	private List<BrickOrder> brickOrders = new ArrayList<>();
	private AtomicLong nextId = new AtomicLong();

	@PostMapping("/order")
	@ResponseStatus(HttpStatus.CREATED)
	public BrickOrder createNewOrder(@RequestBody BrickOrder brickOrder) {
		// Set pledge to have next ID:
		brickOrder.setId(nextId.incrementAndGet());
		brickOrders.add(brickOrder);
		return brickOrder;
	}

	@GetMapping("/getAllOrders")
	public List<BrickOrder> getAllOrders() {
		return brickOrders;
	}

	@GetMapping("/getOrder/{id}")
	public BrickOrder getOneOrder(@PathVariable("id") long orderId) {
		for (BrickOrder brickOrder : brickOrders) {
			if (brickOrder.getId() == orderId) {
				return brickOrder;
			}
		}

		throw new IllegalArgumentException();
	}

	@PostMapping("updateOrder/{id}")
	public BrickOrder editOneOrder(@PathVariable("id") long orderId, @RequestBody BrickOrder newOrder) {
		for (BrickOrder brickOrder : brickOrders) {
			if (brickOrder.getId() == orderId) {
				if (brickOrder.isShipped()) {
					throw new RuntimeException();
				}
				brickOrders.remove(brickOrder);
				newOrder.setId(orderId);
				brickOrders.add(newOrder);
				return newOrder;
			}
		}

		throw new IllegalArgumentException();
	}

	@PostMapping("fulfilOrder/{id}")
	public BrickOrder fulfilOrder(@PathVariable("id") long orderId) {
		for (BrickOrder brickOrder : brickOrders) {
			if (brickOrder.getId() == orderId) {
				brickOrder.setShipped(true);
				return brickOrder;
			}
		}

		throw new IllegalArgumentException();
	}

	// IllegalArgument Exception Handler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Order ID not found !")
	@ExceptionHandler(IllegalArgumentException.class)
	public void badIdExceptionHandler() {

	}

	// OrderAlreadyShipped Exception Handler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Can not update order as the order has already been shipped !")
	@ExceptionHandler(RuntimeException.class)
	public void orderAlreadyShippedExceptionHandler() {

	}
}
