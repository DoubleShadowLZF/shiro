package org.middlesoft.shiro.dao;

import org.middlesoft.shiro.entity.dto.SysPermissionDto;
import org.middlesoft.shiro.entity.qo.SysPermissionQo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
/**
 * the service class of SysPermissionDto can do CRUD for the database. 
 * 
 */
@Repository
public interface SysPermissionRepository extends PagingAndSortingRepository<SysPermissionDto, Long>, JpaSpecificationExecutor<SysPermissionDto> {

	default Specification<SysPermissionDto> where(SysPermissionQo qo) {
		return ((root, query, cb) -> {
			Predicate where = null;
			where = cb.and(cb.equal(root.get("isDeleted"),0));

			if(qo.getId() != null){
				where = cb.and(where,cb.equal(root.get("id"),qo.getId()));
			}
			return query.where(where).getRestriction();
		});
	}

}
