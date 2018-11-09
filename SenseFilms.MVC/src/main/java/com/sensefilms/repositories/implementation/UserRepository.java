package com.sensefilms.repositories.implementation;

import java.sql.SQLException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sensefilms.core.extensions.CastUtils;
import com.sensefilms.repositories.base.BaseCRUDRepository;
import com.sensefilms.repositories.contracts.IUserRepository;
import com.sensefilms.repositories.entities.User;

@Repository
@Transactional
public class UserRepository extends BaseCRUDRepository<User> implements IUserRepository
{
	public UserRepository()
	{
		super(User.class);
	}

	@Override
	public User getOneByUsername(String username) throws SQLException 
	{
		Query query = getSessionFactory().getCurrentSession().createQuery("from User where upper(username) = :username");
		query.setParameter("username", username.toUpperCase());
		return CastUtils.tryCastAs(User.class, query.uniqueResult());
	}
}
