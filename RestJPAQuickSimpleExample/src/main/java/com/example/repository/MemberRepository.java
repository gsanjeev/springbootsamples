package com.example.repository;

import com.example.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Member findByName(String name);
}
