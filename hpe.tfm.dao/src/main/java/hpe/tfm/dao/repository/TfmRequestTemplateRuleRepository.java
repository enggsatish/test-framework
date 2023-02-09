package hpe.tfm.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmRequestTemplateRule;

@Repository
public interface TfmRequestTemplateRuleRepository extends JpaRepository<TfmRequestTemplateRule, Integer>{

}
