package com.tomspencerlondon.snackmachine.hexagon;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("architecture")
public class HexagonalArchitectureTest {

  @Test
  void domainMustNotDependOnAdapters() {
    noClasses()
        .that()
        .resideInAPackage("..domain..")
        .should()
        .dependOnClassesThat()
        .resideInAPackage("..adapter..")
        .check(productionAndTestClasses());
  }

  @Test
  public void domainMustNotDependOnApplication() {
    noClasses()
        .that()
        .resideInAPackage("..domain..")
        .should()
        .dependOnClassesThat()
        .resideInAPackage("..application..")
        .check(productionAndTestClasses());
  }

  @Test
  public void applicationMustNotDependOnAdapters() {
    noClasses()
        .that().resideInAPackage("..application..")
        .should().dependOnClassesThat().resideInAPackage("..adapter..")
        .check(productionAndTestClasses());
  }

  @Test
  public void adaptersMustNotDependOnEachOther() {
    slices().matching("..adapter.*.(*)..")
        .should().notDependOnEachOther()
        .as("Adapters must not depend on each other")
        .check(productionAndTestClasses());
  }


  private JavaClasses productionAndTestClasses() {
    return new ClassFileImporter().importPackages("com.tomspencerlondon.snackmachine");
  }
}

