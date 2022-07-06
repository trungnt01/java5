package com.poly.controller.admin.accounts;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.sarxos.webcam.Webcam;
import com.poly.beans.Order_model;
import com.poly.constant.SessionAtrr;
import com.poly.entities.Account;
import com.poly.entities.Order;
import com.poly.entities.OrderDetail;
import com.poly.entities.Product;
import com.poly.mapper.OrderMapper;
import com.poly.repositories.AccountRepository;
import com.poly.repositories.OrderDetailRepository;
import com.poly.repositories.OrderRepository;
import com.poly.repositories.ProductRepository;

import lombok.val;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrderController {
	
	private List<OrderDetail> lstOrderDetail = new ArrayList<OrderDetail>();
	
	private List<OrderDetail> lstOrderDetailUpdate = new ArrayList<OrderDetail>();
	
	private List<Product> lstProduct = null;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private OrderMapper mapper;
	
	
	@GetMapping()
	public String index(
		Model model,
		@RequestParam(name = "page", defaultValue = "0") int page,
		@RequestParam(name = "size", defaultValue = "15") int size
	) {
		Pageable pageable = PageRequest.of(page, size); 
		Page<Order> pageData = this.orderRepo.findAll(pageable);
		model.addAttribute("pageData", pageData);
		
		return "admin/orders/index";
	}
	
	
	 
	@GetMapping("/create/up")
	public String up(
			@ModelAttribute("order") Order_model orderModel 
	) {
		try {
			
			String idStr = this.request.getParameter("productId");
			Integer id = null;
			if(idStr != null) {
				id = Integer.parseInt(idStr);
			}
			String quantityStr = this.request.getParameter("quantity");
			Integer quantity = null;
			if(quantityStr != null) {
				quantity = Integer.parseInt(quantityStr);
			}
			
			if(id != null) {
				Product product = this.productRepo.getById(id);
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setProduct(product);
				orderDetail.setPrice(product.getPrice());
				orderDetail.setQuantity(quantity);
//				orderDetail.setQuantity(1);
				
				lstProduct.clear();
				this.lstOrderDetail.add(orderDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/orders/create";
		}
		return "redirect:/admin/orders/create";
	}
	
	@GetMapping("/create/scan")
	@ResponseBody
	public void scan(
		Model model
	) {
		try {			
			
			String idStr = this.request.getParameter("productId");
			Integer id = null;
			if(idStr != null) {
				id = Integer.parseInt(idStr);
			}
			 			
			Product product = this.productRepo.getById(id);
			if(id != null) {
				
				if(lstOrderDetail.size() > 0) {
					for (int i = 0; i< lstOrderDetail.size(); i++) {
						
						if(lstOrderDetail.get(i).getProduct().getId() == id) {
							lstOrderDetail.get(i).setQuantity(lstOrderDetail.get(i).getQuantity() + 1);
						} else {
							
							OrderDetail newOrderDetail = new OrderDetail();
							newOrderDetail.setProduct(product);
							newOrderDetail.setPrice(product.getPrice());
							newOrderDetail.setQuantity(1);
							this.lstOrderDetail.add(newOrderDetail);
						}
					}
				}else {
					OrderDetail newOrderDetail = new OrderDetail();
					newOrderDetail.setProduct(product);
					newOrderDetail.setPrice(product.getPrice());
					newOrderDetail.setQuantity(1);
					this.lstOrderDetail.add(newOrderDetail);
				}
				
			}
			PrintWriter out = this.response.getWriter();
			out.print(product.getName());
			
			model.addAttribute("lstOrderDetail", lstOrderDetail);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GetMapping("/create/update")
	public String createUpdate(
		Model model
	) {
		try {			
			 
			int index = Integer.parseInt(this.request.getParameter("index")) - 1;			
			String upOrDown = this.request.getParameter("upOrDown");
			if(upOrDown.equals("up")) {
				lstOrderDetail.get(index).setQuantity(lstOrderDetail.get(index).getQuantity() + 1);
			} else if (upOrDown.equals("down")) {
				lstOrderDetail.get(index).setQuantity(lstOrderDetail.get(index).getQuantity() - 1);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/orders/create";
	}
	
	
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct( 
		@PathVariable("id") int index
	) {
		try {
			
			lstOrderDetail.remove(index-1);
		 
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/orders/create";
		}
		return "redirect:/admin/orders/create";
	}
	
	
	@GetMapping("/create")
	public String create( 
			@ModelAttribute("order") Order_model orderModel , 
			Model model
	) {
		try {
			if( lstProduct!= null) {
				lstProduct.clear();
			}
		
			String productName = this.request.getParameter("productName");
			if(productName != null) {
				lstProduct = this.productRepo.findByName(productName.trim());
			}
			
			List<Account> lstAccount = this.accountRepo.findAll();
			
			model.addAttribute("lstAccount", lstAccount);
			model.addAttribute("lstProduct", lstProduct);
			model.addAttribute("lstOrderDetail", lstOrderDetail);
			lstOrderDetailUpdate.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/orders/create";
	}
	
	
	@PostMapping("/store")
	public String store(
			@Valid Order_model orderModel,
			BindingResult result
	) {
		try {
			if(result.hasErrors()) {
				System.out.println("form khong hơp le");
				return "redirect:/admin/orders/create";
			} else {
				
				Order new_order = new Order();
				new_order.setAccount(null);
				new_order.setAddress(orderModel.getAddress());
				new_order.setCustomerName(orderModel.getCustomerName());
				new_order.setCreateDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
				
				Order order = this.orderRepo.save(new_order);
				
				System.out.println(order.getAddress());
				System.out.println(order.getCustomerName());
				System.out.println(order.getAccount());
				System.out.println(order.getCreateDate());
				
				for(int i = 0; i < lstOrderDetail.size(); i++) {
					lstOrderDetail.get(i).setOrder(order);
				}
					
				
				
				this.orderDetailRepo.saveAllAndFlush(lstOrderDetail);
				
				lstProduct.clear();
				lstOrderDetail.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/orders/create";
		}
		return "redirect:/admin/orders";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit( 
			Model model,
			@PathVariable("id") Order order
	) {
		try {
			if(lstOrderDetailUpdate.size() == 0) {
				lstOrderDetailUpdate = order.getLstOrderDetail();
			}
			Order_model orderModel = this.mapper.convertToDTO(order);
			
			
			model.addAttribute("orderModel", orderModel);
			model.addAttribute("lstOrderDetail", lstOrderDetailUpdate);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/orders/edit/" + order.getId();
		}
		return "admin/orders/edit";
	}
	
	
	@GetMapping("/updateList")
	@ResponseBody
	public void updateList( ) {
		try {
			
			String upOrDown = request.getParameter("upOrDown");
			int quantity = Integer.parseInt(this.request.getParameter("quantity"));
			int index = Integer.parseInt(this.request.getParameter("index")) -1;
			
			if(upOrDown.equals("down")) {
				lstOrderDetailUpdate.get(index).setQuantity(lstOrderDetailUpdate.get(index).getQuantity() - 1);
			} 
			if(upOrDown.equals("up")) {
				lstOrderDetailUpdate.get(index).setQuantity(lstOrderDetailUpdate.get(index).getQuantity() + 1);
			}
			if(upOrDown.equals("change")) {
				lstOrderDetailUpdate.get(index).setQuantity(quantity);
			}
			
			PrintWriter out = this.response.getWriter();
			out.print(lstOrderDetailUpdate.get(index).getQuantity());
			System.out.println(lstOrderDetailUpdate.get(index).getQuantity());
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	
	@GetMapping("/update/scan")
	@ResponseBody
	public void updateScan(
		Model model
	) {
		try {			
			
			String idOrder = this.request.getParameter("idOrder");
			String idStr = this.request.getParameter("productId");
			Integer id = null;
			if(idStr != null) {
				id = Integer.parseInt(idStr);
			}
			 			
			Product product = this.productRepo.getById(id);
			if(id != null) {
				
				if(lstOrderDetailUpdate.size() > 0) {
					for (int i = 0; i< lstOrderDetailUpdate.size(); i++) {
						
						if(lstOrderDetailUpdate.get(i).getProduct().getId() == id) {
							lstOrderDetailUpdate.get(i).setQuantity(lstOrderDetailUpdate.get(i).getQuantity() + 1);
						} else {
							
							OrderDetail newOrderDetail = new OrderDetail();
							newOrderDetail.setProduct(product);
							newOrderDetail.setPrice(product.getPrice());
							newOrderDetail.setQuantity(1);
							this.lstOrderDetailUpdate.add(newOrderDetail);
						}
					}
				}else {
					OrderDetail newOrderDetail = new OrderDetail();
					newOrderDetail.setProduct(product);
					newOrderDetail.setPrice(product.getPrice());
					newOrderDetail.setQuantity(1);
					this.lstOrderDetailUpdate.add(newOrderDetail);
				}
				
			}
			PrintWriter out = this.response.getWriter();
			out.print(product.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@PostMapping("/update/{id}")
	public String update(
			@Valid Order_model ordermModel,
			@PathVariable("id") Order order_old
	) {
		try {
			
			Order order = this.mapper.convertToEntity(ordermModel);
			order.setAccount(order_old.getAccount());
			order.setCreateDate(order_old.getCreateDate());
			
			this.orderRepo.save(order);
			
			 
			this.orderDetailRepo.saveAllAndFlush(lstOrderDetailUpdate);
			lstOrderDetailUpdate.clear();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/orders/edit/" + order_old.getId();
		}	
		return "redirect:/admin/orders";
	}
	
	

}
