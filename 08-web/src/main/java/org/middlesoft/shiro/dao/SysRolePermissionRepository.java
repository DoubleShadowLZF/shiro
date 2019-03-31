package org.middlesoft.shiro.dao;

import org.middlesoft.shiro.entity.dto.SysRolePermissionDto;
import org.middlesoft.shiro.entity.qo.SysRolePermissionQo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
/**
 * the service class of SysRolePermissionDto can do CRUD for the database. 
 * 
 */
@Repository
public interface SysRolePermissionRepository extends PagingAndSortingRepository<SysRolePermissionDto, Long>, JpaSpecificationExecutor<SysRolePermissionDto> {

	default Specification<SysRolePermissionDto> where(SysRolePermissionQo qo) {
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
