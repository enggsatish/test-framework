package hpe.tfm.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmParamRuleMap;

@Repository
public interface TfmParamRuleMapRepository extends JpaRepository<TfmParamRuleMap, Integer>{

//	@Query("Select * from TFM_PARAM_RULE_MAP t where t.tfm_param_id = :paramId")
//	Optional<TfmParamRuleMap> findByTfmParamId(int paramId);
	
	List<TfmParamRuleMap> findByTfmParamId(int tfm_param_id);
}
