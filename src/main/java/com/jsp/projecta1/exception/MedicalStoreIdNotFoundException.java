package com.jsp.projecta1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MedicalStoreIdNotFoundException extends RuntimeException {
private String message;

}
