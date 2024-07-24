package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.shared.domain.ErrorMessages;
import br.com.vicente.multitoolsbackend.shared.domain.ValidatorCustom;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator extends ValidatorCustom<Category> {

    private final List<String> errors = new ArrayList<>();
    public CategoryValidator(final Category object) {
        super(object);
    }

    @Override
    public List<String> validate() {
        validateName();
        return errors;
    }

    private void validateName(){
        final String name = this.object.getName();
        if(StringUtils.isEmpty(name)) errors.add(ErrorMessages.NAME_SHOULD_NOT_BE_EMPTY);
        if(StringUtils.isBlank(name)) errors.add(ErrorMessages.NAME_SHOULD_NOT_BE_BLANK);
        if(StringUtils.isNotEmpty(name)){
            if(name.length() > 255) errors.add(ErrorMessages.NAME_SHOULD_NOT_BE_MORE_THAN_255_CHARACTER);
        }

    }
}
