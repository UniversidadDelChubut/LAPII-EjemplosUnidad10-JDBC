package edu.udc.lapii.jdbc.crud;

import org.apache.log4j.Logger;

public class Principal {
	
	private static final Logger LOGGER = Logger.getLogger(Principal.class);
	
	public static void main(String[] args) {
		
		try {
			
			
			Vendedor v = new Vendedor("FMO");
			System.out.println("Nombre: " + v.getNombre());
			v.setNombre("FUNES MORI, ROGELIO");
			v.update();
			
			
			
			Vendedor manteca = new Vendedor();
			manteca.setNombre("MANTECA MARTINEZ");
			manteca.setCodigo("GOL");
			manteca.setLegajo(14);
			manteca.setComision(0.1);
			
			manteca.insert();
			
			
			
			
			for(Vendedor vendedor: Vendedor.getVendedores()){
				System.out.println(vendedor.getNombre() + " " + vendedor.getComision());
			}
			
			System.out.println(" Es vendedor " + v.getNombre() + "? : " + Vendedor.getVendedores().contains(v));
			
		} catch (ObjetoInexistenteException e) {
			LOGGER.error(e, e);
		} catch (ModelException e) {
			LOGGER.error(e, e);
		}
		
		
	}
}
