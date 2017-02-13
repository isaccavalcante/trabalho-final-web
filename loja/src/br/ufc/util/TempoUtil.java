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
		
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
		
	}
	
	public String saudacao() {
		Calendar calendario = Calendar.getInstance();
		int hora = calendario.get(Calendar.HOUR_OF_DAY);
		System.out.println(hora+6);
		System.out.println("->" +hora);
		if (hora >= 6 && hora < 12){
			return "Bom dia";
		}else if(hora >= 12 && hora < 18  ){
			return "Boa tarde";
		} else if(hora >= 18 && hora < 24  ){
			return "Boa noite";
		}
		return "Boa madrugada";
		
	}

}
