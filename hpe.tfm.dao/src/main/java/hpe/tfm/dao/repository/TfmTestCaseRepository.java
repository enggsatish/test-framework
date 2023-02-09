package hpe.tfm.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmTestCase;

@Repository
public interface TfmTestCaseRepository  extends JpaRepository<TfmTestCase, Integer>{

	public TfmTestCase findByTfmSTcName (String tfmSTcName);
	
	public List<TfmTestCase> findByTfmSuiteId(int tfmSuiteId);
}
