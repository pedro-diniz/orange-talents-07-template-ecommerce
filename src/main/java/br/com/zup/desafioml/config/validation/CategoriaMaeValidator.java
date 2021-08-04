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
public class CategoriaMaeValidator implements ConstraintValidator<CategoriaMae, Object> {

    private String domainAttribute;
    private Class<?> klass;

    // não conseguimos utilizar Repository aqui, pois a injeção seria dinâmica
    // acabaríamos precisando passar o Repository por parâmetro também, e
    // queremos simplificar o código
    @PersistenceContext
    private EntityManager entityManager;


    @Override // instancia o objeto de validação
    public void initialize(CategoriaMae toValidate) {}

    @Override // faz uma query a partir dos parâmetros recebidos
    public boolean isValid(Object value, ConstraintValidatorContext validatorContext) {

        if (value != null) {
            Query query = entityManager.createQuery("select c from Categoria c " +
                    "where id = :categoriaMae");
            query.setParameter("categoriaMae", value);
            List<?> list = query.getResultList();
            Assert.state(list.size() <= 1, "Foi encontrada mais de uma categoria com " +
                    "o ID = :categoriaMae");
            return !list.isEmpty();
        }
        return true;
    }

}
