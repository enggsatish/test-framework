package hpe.tfm.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hpe.tfm.dao.model.TfmRequestTemplatePt;

@Repository
public interface TfmRequestTemplateRepository extends JpaRepository<TfmRequestTemplatePt, Integer>{

}
