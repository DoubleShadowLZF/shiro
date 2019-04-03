package org.middlesoft.shiro.dao;

import org.middlesoft.shiro.entity.dto.SysRoleDto;
import org.middlesoft.shiro.entity.qo.SysRoleQo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
/**
 * the service class of SysRoleDto can do CRUD for the database. 
 * 
 */
@Repository
public interface SysRoleRepository extends PagingAndSortingRepository<SysRoleDto, Long>, JpaSpecificationExecutor<SysRoleDto> {

	default Specification<SysRoleDto> where(SysRoleQo qo) {
		return ((root, query, cb) -> {
			Predicate where  = cb.and(cb.equal(root.get("deleteStatus"),"1"));

			if(qo.getId() != null){
				where = cb.and(where,cb.equal(root.get("id"),qo.getId()));
			}
			return query.where(where).getRestriction();
		});
	}

}
