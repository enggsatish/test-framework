package hpe.tfm.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmTestStepTemplate;

@Repository
public interface TfmTestStepTemplateRepository extends JpaRepository<TfmTestStepTemplate, Integer>{

	public TfmTestStepTemplate findByTfmTestStepTemplateId(String tfmTestStepTemplateId);
	
}
