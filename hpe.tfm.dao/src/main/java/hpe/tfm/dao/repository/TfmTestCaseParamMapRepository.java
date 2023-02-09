package hpe.tfm.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmTestCaseParamMap;

@Repository
public interface TfmTestCaseParamMapRepository extends JpaRepository<TfmTestCaseParamMap, Integer>{

	public List<TfmTestCaseParamMap> findByTfmTcId(int tfmTcId);
}
