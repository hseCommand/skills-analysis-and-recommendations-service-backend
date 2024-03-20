package com.hse.skillsevaluation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import com.hse.skillsevaluation.entity.Skill;
import com.hse.skillsevaluation.exception_handling.NoSuchSkillException;
import com.hse.skillsevaluation.repository.SkillRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SkillServiceImplTest {

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest")
          .withDatabaseName("skills")
          .withUsername("hse")
          .withPassword("hsepassword")
          .withCreateContainerCmdModifier(
              cmd ->
                  cmd.getHostConfig()
                      .withPortBindings(
                          new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432))));

  @Autowired private SkillRepository skillRepository;

  private SkillServiceImpl skillService;

  @BeforeEach
  public void setUp() {
    skillService = new SkillServiceImpl(skillRepository);
  }

  @Test
  public void testGetAllSkillsFromInitMigrations() {
    List<Skill> result = skillService.getAllSkills();

    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(3);
  }

  @Test
  public void testUpdateSkill_WhenSkillDoesNotExist_ThrowNoSuchSkillException() {
    Skill skill = new Skill();
    skill.setId(6L);

    assertThatThrownBy(() -> skillService.updateSkill(skill))
        .isInstanceOf(NoSuchSkillException.class);
  }

  @Test
  public void testDeleteSkillById_WhenSkillDoesNotExist_ThrowNoSuchSkillException() {
    Long id = 6L;

    assertThatThrownBy(() -> skillService.deleteSkillById(id))
        .isInstanceOf(NoSuchSkillException.class);
  }
}
