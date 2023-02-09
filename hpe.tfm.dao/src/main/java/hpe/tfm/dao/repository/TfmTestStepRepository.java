package hpe.tfm.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmTestStep;

@Repository
public interface TfmTestStepRepository  extends JpaRepository<TfmTestStep, Integer>{

	public List<TfmTestStep> findByTfmSTcId(int tfmSTcId);
	
	public TfmTestStep findByTfmTstepName(String name);
}
