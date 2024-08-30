package com.example.demo.DB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    // 기본 CRUD 작업 및 사용자 정의 쿼리 메서드를 추가할 수 있습니다.
}

