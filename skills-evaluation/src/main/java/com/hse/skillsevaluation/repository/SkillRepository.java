package com.hse.skillsevaluation.repository;

import com.hse.skillsevaluation.entity.Skill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SkillRepository extends JpaRepository<Skill, Long>,
    JpaSpecificationExecutor<Skill> {

  @Query(value = "SELECT DISTINCT tag.tag FROM tag", nativeQuery = true)
  List<String> getAllDistinctTags();

}
