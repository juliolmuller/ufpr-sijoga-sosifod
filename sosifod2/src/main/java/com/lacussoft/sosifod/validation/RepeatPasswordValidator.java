package com.lacussoft.sosifod.validation;

import com.lacussoft.utils.Converter;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("repeatPassword")
public class RepeatPasswordValidator implements Validator {

    public final static String INVALID_MESSAGE = "As senhas não conferem.";

    @Override
    public void validate(FacesContext context, UIComponent ui, Object value) throws ValidatorException {

        String fieldId = (String) ui.getAttributes().get("target");
        UIInput textInput = (UIInput) ui.getParent().findComponent(fieldId);

        if (textInput == null) {
            String msg = "Nenhum campo com ID \"" + fieldId + "\" encontrado.";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
        }

        String originalValue = Converter.nullable((String) textInput.getValue());
        String repeatedValue = Converter.nullable((String) value);

        if (originalValue != null && repeatedValue != null && !originalValue.equals(repeatedValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_MESSAGE, INVALID_MESSAGE);
            throw new ValidatorException(msg);
        }
    }
}
