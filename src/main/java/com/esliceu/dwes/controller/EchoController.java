package com.esliceu.dwes.controller;

import Dao.UserDaoImplement;
import Pojo.*;
import com.esliceu.dwes.model.Echo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class EchoController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Persona> hello() throws SQLException, ClassNotFoundException {
		Persona p = new Persona();
		p.setId("1");
		p.setNom("xisquito");
		p.setCognom("moreno");

		Fitxatge f = new Fitxatge();
		f.setId("1");
		f.setHora(new Date());
		f.setTipus(TipusFitxatge.ENTRADA);
		List<Fitxatge> fit = new ArrayList<>();
		fit.add(f);
		p.setFitxatges(fit);

		List<Persona> lp = new ArrayList<>();
		lp.add(p);

		return lp;
	}


	@RequestMapping(value = "/echo", method = RequestMethod.POST)
	public String echo(@RequestBody User request) throws SQLException, ClassNotFoundException {
		UserDaoImplement ud = new UserDaoImplement();
		try {
			ud.insertUser(request.getName(), request.getPassword(), request.getRoles());
		}catch (NullPointerException e){
			e.getMessage();
		}
		return "ok";
	}

}
