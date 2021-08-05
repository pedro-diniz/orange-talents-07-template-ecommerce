package br.com.zup.desafioml.config.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

// possui o mesmo princípio de funcionamento do UniqueValue
// a saída, entretanto, é contrária. Queremos que exista um valor (Id) em uso.
public class CheckIdExistenceValidator implements ConstraintValidator<CheckIdExistence, Object> {

    private String domainAttribute;
    private Class<?> klass;

    // não conseguimos utilizar Repository aqui, pois a injeção seria dinâmica
    // acabaríamos precisando passar o Repository por parâmetro também, e
    // queremos simplificar o código
    @PersistenceContext
    private EntityManager entityManager;


    @Override // instancia o objeto de validação
    public void initialize(CheckIdExistence toValidate) {
        this.domainAttribute = toValidate.fieldName();
        this.klass = toValidate.domainClass();

    }

    @Override // faz uma query a partir dos parâmetros recebidos
    public boolean isValid(Object idAValidar, ConstraintValidatorContext validatorContext) {
        if (idAValidar != null) {
            Query query = entityManager.createQuery("select x from " + klass.getName() +
                    " x where " + domainAttribute + " = :idParam");
            query.setParameter("idParam", idAValidar);
            List<?> list = query.getResultList();
            Assert.state(list.size() <= 1, "Foi encontrada mais de uma correspondência " +
                    "para esse ID");
            return !list.isEmpty();
        }
        return true;
    }

}
