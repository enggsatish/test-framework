package hpe.tfm.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmTestStepParamMap;

@Repository
public interface TfmTestStepParamMapRepository extends JpaRepository<TfmTestStepParamMap, Integer>{

	public List<TfmTestStepParamMap> findByTfmTsId(int tfmTsId);
}
