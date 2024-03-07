package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.entity.SkillType;
import com.hse.skillsevaluation.entity.Tag;
import com.hse.skillsevaluation.entity.UnitType;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class CustomSpecs {
  static Specification<Skill> byUnitType(List<UnitType> unitTypes) {
    return (root, query, builder) -> {
      if (unitTypes == null) {
        return builder.isTrue(builder.literal(true));
      } else {
        Path<UnitType> group = root.get("unitType");
        return group.in(unitTypes);
      }
    };
  }

  static Specification<Skill> bySkillType(List<SkillType> skillTypes) {
    return (root, query, builder) -> {
      if (skillTypes == null) {
        return builder.isTrue(builder.literal(true));
      } else {
        Path<UnitType> group = root.get("skillType");
        return group.in(skillTypes);
      }
    };
  }

  static Specification<Skill> byTags(List<String> tags) {
    return (root, query, builder) -> {
      if (tags == null || tags.isEmpty()) {
        return builder.isTrue(builder.literal(true));
      } else {
        Join<Skill, Tag> join = root.join("tags");
        return join.get("tag").in(tags);
      }
    };
  }
}
