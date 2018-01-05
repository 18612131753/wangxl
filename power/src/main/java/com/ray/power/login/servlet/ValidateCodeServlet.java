package com.ray.power.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.base.util.RandomValidateCode;

@WebServlet(urlPatterns="/validateCode")  
public class ValidateCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 3054907896382880547L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RandomValidateCode validateCode = new RandomValidateCode();
		validateCode.generateValidateCode(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
