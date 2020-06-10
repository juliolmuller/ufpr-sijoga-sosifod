package com.lacussoft.sijoga.validation;

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

    @Override
    public void validate(FacesContext context, UIComponent ui, Object value) throws ValidatorException {

        String fieldId = (String) ui.getAttributes().get("target");
        UIInput textInput = (UIInput) ui.getParent().findComponent(fieldId);

        if (textInput == null) {
            FacesMessage msg = new FacesMessage(null, "Nenhum campo com ID \"" + fieldId + "\" encontrado.");
            throw new ValidatorException(msg);
        }

        String originalValue = Converter.nullable((String) textInput.getValue());
        String repeatedValue = Converter.nullable((String) value);

        if (originalValue != null && repeatedValue != null && !originalValue.equals(repeatedValue)) {
            FacesMessage msg = new FacesMessage(null, "As senhas não conferem.");
            throw new ValidatorException(msg);
        }
    }
}
