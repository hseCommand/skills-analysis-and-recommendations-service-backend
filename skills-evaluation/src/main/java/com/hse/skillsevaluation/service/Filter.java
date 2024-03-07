package com.hse.skillsevaluation.service;

import com.hse.skillsevaluation.entity.SkillType;
import com.hse.skillsevaluation.entity.Tag;
import com.hse.skillsevaluation.entity.UnitType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filter {
  private List<SkillType> skillTypes;
  private List<UnitType> unitTypes;
  private List<String> tags;
}
