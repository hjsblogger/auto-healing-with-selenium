# Define variables
MVN_CLEAN := mvn clean
MVN_TEST := mvn compile test
SUITE_NAME := testng_autohealing.xml
PROJECT_NAME := auto-healing using Selenium on LambdaTest

.PHONY: set-env
set-env:
	@echo "Set env vars LT_USERNAME & LT_ACCESS_KEY"
    # Procure Username and AccessKey from https://accounts.lambdatest.com/security
    export LT_USERNAME=himanshu
    export LT_ACCESS_KEY=Ia1MiqNfci

.PHONY: auto-heal
auto-heal:
	- $(MVN_TEST) -DsuiteXml=$(SUITE_NAME)

.PHONY: clean
clean:
	- mvn clean
    # This helped: https://gist.github.com/hbsdev/a17deea814bc10197285
	- find . | grep -E "(test-output)" | xargs rm -rf
	- find . | grep -E "(target)" | xargs rm -rf
	@echo "Clean Succeded"

.PHONY: help
help:
	@echo ""
	@echo "clean : Clean all project dependencies"
	@echo "set-env : Set the environment variables LT_USERNAME & LT_ACCESS_KEY"
	@echo "auto-heal : Run auto-healing tests on LambdaTest"