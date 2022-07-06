package com.poly.beans;



import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Account_model {

	private Integer id;
	
	@NotNull
	@NotBlank
	@Length(min = 1, max = 255)
//	@Pattern(regexp="^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$")
	private String username;
	
	@NotNull
	@NotBlank
	@Length(min = 1, max = 255)
	private String fullname;
	
	@NotNull
	@NotBlank
	@Email
//	@Pattern(regexp="^([a-z0-9_\\\\.-]+)@[fpt.edu.vn]{1}")
//	@Pattern(regexp = "^[a-zA-Z0-9]+@[f]{1}[p]{1}[t]{1}\\.[e]{1}[d]{1}[u]{1}//.[v]{1}[n]{1}")
	@Length(min = 1, max = 255)
	private String email;
	
	@NotNull
	@NotBlank
	@Length(min = 6, max = 20)
	private String password;
	
	@NotBlank
	@Length(min = 1, max = 255)
	private String address;
	
	private String photo;

	@NotNull
	@Max(1)
	@Min(0)
	private Integer admin;
	
	@NotNull
	@Max(1)
	@Min(0)
	private Integer activated;
}
