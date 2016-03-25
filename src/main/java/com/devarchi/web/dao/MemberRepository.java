package com.devarchi.web.dao;

import com.devarchi.web.dto.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by donghoon on 2016. 2. 25..
 */
@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    List<Member> findByLastName(String lastName);

}
