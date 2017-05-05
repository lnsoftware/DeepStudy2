package com.zhiyin.search.es.controller;

import org.hibernate.validator.constraints.Range;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * 测试valid
 *
 * @author hg
 */
public class ValidationTest {

    private Validator validator;

    @Before
    public void init() {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();

    }

    @Test
    public void prereqsMet() {
        Workshop validWorkshop = new Workshop();
        validWorkshop.threadNumber = 10;
        Set<ConstraintViolation<Workshop>> violations = this.validator.validate(validWorkshop);
        assertTrue(violations.isEmpty());
    }


    class Workshop {
        @Range(min = 1, max = 100)
        int threadNumber;
    }
}


