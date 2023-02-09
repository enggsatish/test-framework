package hpe.tfm.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmParamPt;

@Repository
public interface TfmParamPtRepository extends JpaRepository<TfmParamPt, Integer>{

	public TfmParamPt findByTfmParamAlias(String tfmParamAlias);
}
