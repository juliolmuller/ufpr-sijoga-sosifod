package com.lacussoft.sijoga.validation;

import com.lacussoft.utils.Converter;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("cpf")
public class CpfValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent ui, Object value) throws ValidatorException {

        String cpf = Converter.removeNonDigits((String) value);

        if (!com.lacussoft.utils.Validator.isCpf(cpf)) {
            FacesMessage msg = new FacesMessage("CPF inválido.");
            throw new ValidatorException(msg);
        }
    }
}
