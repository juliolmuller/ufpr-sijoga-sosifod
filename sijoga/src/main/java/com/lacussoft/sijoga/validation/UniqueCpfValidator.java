package com.lacussoft.sijoga.validation;

import com.lacussoft.sijoga.model.User;
import com.lacussoft.sijoga.services.Dao;
import com.lacussoft.utils.Converter;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import org.hibernate.Query;

@Named
@RequestScoped
public class UniqueCpfValidator implements Validator {

    public final static String INVALID_MESSAGE = "CPF já cadastrado.";

    @EJB
    private Dao dao;

    @Override
    public void validate(FacesContext context, UIComponent ui, Object value) throws ValidatorException {

        String cpf = Converter.removeNonDigits((String) value);

        if (cpf != null) {
            String hql = "FROM User u WHERE u.cpf = :cpf";
            Query query = dao.query(hql).setString("cpf", cpf);
            User user = (User) query.uniqueResult();

            if (user != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_MESSAGE, INVALID_MESSAGE);
                throw new ValidatorException(msg);
            }
        }
    }
}
