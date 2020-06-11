package com.lacussoft.sijoga.validation;

import com.lacussoft.utils.Converter;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("cep")
public class CepVlaidator implements Validator {

    public final static String INVALID_MESSAGE = "CEP inválido.";

    @Override
    public void validate(FacesContext context, UIComponent ui, Object value) throws ValidatorException {

        String cep = Converter.removeNonDigits((String) value);

        if (cep != null && !com.lacussoft.utils.Validator.isCep(cep)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_MESSAGE, INVALID_MESSAGE);
            throw new ValidatorException(msg);
        }
    }
}
