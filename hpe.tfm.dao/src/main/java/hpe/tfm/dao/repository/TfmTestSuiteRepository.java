package hpe.tfm.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmTestSuite;

@Repository
public interface TfmTestSuiteRepository  extends JpaRepository<TfmTestSuite, Integer> {

	public TfmTestSuite findByTfmSuiteName(String tfmSuiteName);
}
