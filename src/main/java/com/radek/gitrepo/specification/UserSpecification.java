package com.radek.gitrepo.specification;


import com.radek.gitrepo.entity.User;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path = "firstName", spec = Equal.class),
        @Spec(path = "lastName", spec = Equal.class),
        @Spec(path = "username", spec = Equal.class)
})
public interface UserSpecification extends Specification<User> {
}
