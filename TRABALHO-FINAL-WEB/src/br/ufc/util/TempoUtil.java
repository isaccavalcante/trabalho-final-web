package br.ufc.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TempoUtil {
	
	public String obterData() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String s = formatter.format(date);
		return s;
		//return "09/11/2016";
	}
	
	public String obterHora() {
		// TODO
		return "";
	}
	
	public String saudacao() {
		Calendar calendario = Calendar.getInstance();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		System.out.println(hora);
		System.out.println("->" +hora);
		if (hora >= 6 && hora < 12){
			return "Bom dia";
		}else if(hora >= 12 || hora < 18  ){
			return "Boa tarde";
		} else if(hora >= 18 || hora < 24  ){
			return "Boa noite";
		}
		return "Boa madrugada";
		
	}

}
