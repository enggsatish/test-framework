package hpe.tfm.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmTestSuiteParamMap;

@Repository
public interface TfmTestSuiteParamMapRepository extends JpaRepository<TfmTestSuiteParamMap, Integer>{

	public List<TfmTestSuiteParamMap> findByTfmSuiteId(int tfmSuiteId);
}
