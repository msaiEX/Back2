package hanaback.hanabackuser.repository;

import hanaback.hanabackuser.dto.UsersDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersTableRepository {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "hanaback.hanabackuser.mapper.UsersTableMapper";

    public UsersDto findByUserId(String userId) {
        return (UsersDto) sqlSession.selectList(NAMESPACE + ".findByUserId", userId).get(0);
    }
}

