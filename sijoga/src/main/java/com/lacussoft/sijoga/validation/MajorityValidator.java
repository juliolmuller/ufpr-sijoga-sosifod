package com.lacussoft.sijoga.validation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("majority")
public class MajorityValidator implements Validator {

    public final static int MAJORITY_AGE = 18;

    public final static String INVALID_MESSAGE = "É necessário ter mais que " + MAJORITY_AGE + " anos.";

    @Override
    public void validate(FacesContext context, UIComponent ui, Object value) throws ValidatorException {

        if (value != null) {
            Date date = (Date) value;
            LocalDate birth = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
            long age = ChronoUnit.YEARS.between(birth, now);

            if (age < MAJORITY_AGE) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_MESSAGE, INVALID_MESSAGE);
                throw new ValidatorException(msg);
            }
        }
    }
}
