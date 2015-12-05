package control;

import db.ManipuladorBanco;

public class Controller {

	ManipuladorBanco db;
	
	public Controller () {
		this.db = new ManipuladorBanco();
	}
	
	
}
