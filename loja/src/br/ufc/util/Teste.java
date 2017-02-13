package br.ufc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Teste {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DATE, -1);
		String s = format.format(calendario.getTime());
		System.out.println(s);


	}
}
