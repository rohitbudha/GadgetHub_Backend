	package com.Ecom.models;
	
	import java.util.Collection;
	import java.util.List;
	
//import org.springframework.security.core.GrantedAuthority;
//	import org.springframework.security.core.authority.SimpleGrantedAuthority;
//	import org.springframework.security.core.userdetails.UserDetails;
//	import org.springframework.security.core.userdetails.UserDetailsService;
//
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import lombok.Data;
	import lombok.Getter;
	import lombok.Setter;
	
	
	@Data
	@Entity
	@Getter
	@Setter
	@Table(name="customer_tbl")
	public class Customer  {
	
	
			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			private int id;
			private String fname;
			private String lname;
			private String address;
			private String gender;
			private String email;
			private String number;
			private String role;
			private String password;
			
		    public Customer() {
		    	
		    }
	
			public Customer(String fname, String lname, String address, String gender, String email, String number,
					String role, String password) {
				super();
				this.fname = fname;
				this.lname = lname;
				this.address = address;
				this.gender = gender;
				this.email = email;
				this.number = number;
				this.role = role;
				this.password = password;
			}

			
		    
	
		}
	
