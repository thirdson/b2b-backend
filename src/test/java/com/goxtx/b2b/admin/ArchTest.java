package com.goxtx.b2b.admin;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.goxtx.b2b.admin");

        noClasses()
            .that()
                .resideInAnyPackage("com.goxtx.b2b.admin.service..")
            .or()
                .resideInAnyPackage("com.goxtx.b2b.admin.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.goxtx.b2b.admin.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
