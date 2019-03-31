package org.middlesoft.shiro.dao;

import org.middlesoft.shiro.entity.dto.ArticleDto;
import org.middlesoft.shiro.entity.qo.ArticleQo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
/**
 * the service class of ArticleDto can do CRUD for the database. 
 * 
 */
@Repository
public interface ArticleRepository extends PagingAndSortingRepository<ArticleDto, Long>, JpaSpecificationExecutor<ArticleDto> {

	default Specification<ArticleDto> where(ArticleQo qo) {
		return ((root, query, cb) -> {
			Predicate where = null;

			if(qo.getId() != null){
				where = cb.and(cb.equal(root.get("id"),qo.getId()));
			}
			return query.where(where).getRestriction();
		});
	}

}
