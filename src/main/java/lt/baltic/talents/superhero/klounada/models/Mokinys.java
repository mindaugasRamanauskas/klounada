package lt.baltic.talents.superhero.klounada.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_MOKINYS")
public class Mokinys {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "VARDAS")
	private String vardas;
	
}
