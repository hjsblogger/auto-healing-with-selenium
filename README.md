# Auto Healing in Selenium using LambdaTest 

In this 'Auto Healing in Selenium using LambdaTest' repo, we have covered the following use cases:

* <b>Auto Healing of live document</b>
* <b>Auto Healing of document (under development) </b>

The following websites are used for the purpose of demoing the use cases:

* [LambdaTest E-commerce Playground Registration Page](https://ecommerce-playground.lambdatest.io/index.php?route=account/register)
* [LambdaTest E-commerce Playground Product Page](https://ecommerce-playground.lambdatest.io/index.php?route=product/category&path=57)
* [Demo Login Page created by Florin Pop](https://codepen.io/FlorinPop17/pen/vPKWjd)

The [Demo Login Page](https://codepen.io/FlorinPop17/pen/vPKWjd) is hosted locally and run on port number 8080 (or any other port) that is currently free on the system.

## Pre-requisites for test execution

For localhost testing, we need to start the tunnel. To do the same, download [Underpass](https://www.lambdatest.com/support/docs/underpass-tunnel-application/#downloading--installing-the-underpass-tunnel-application) from LambdaTest to establish an SSH tunnel between the OS & LambdaTest cloud server(s).

Before running the tests on LambdaTest Selenium Grid, you need to set the environment variables - *LT_USERNAME* and *LT_ACCESS_KEY*. Here is how you can do the same:

## Configure Environment Variables

Before the tests are run, please set the environment variables <b>LT_USERNAME</b> & <b>LT_ACCESS_KEY</b> from the terminal. The account details are available on your [LambdaTest Profile](https://accounts.lambdatest.com/detail/profile) page.

<img width="970" alt="SecurityPage" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/d2d061d7-a320-4b97-9b23-5663bfa4b721">

For macOS:

```bash
export LT_USERNAME=LT_USERNAME
export LT_ACCESS_KEY=LT_ACCESS_KEY
```

For Linux:

```bash
export LT_USERNAME=LT_USERNAME
export LT_ACCESS_KEY=LT_ACCESS_KEY
```

For Windows:

```bash
set LT_USERNAME=LT_USERNAME
set LT_ACCESS_KEY=LT_ACCESS_KEY
```

Also, add *LT_USERNAME* and *LT_ACCESS_KEY* in the *set-env* section in the [Makefile](https://github.com/hjsblogger/auto-healing-with-selenium/blob/main/Makefile)

![Code1](https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/fe2fec54-9c67-4bd7-a352-81b58bbd50c7)

Run ```make set-env``` to export the desired environment variables (i.e. *LT_USERNAME* and *LT_ACCESS_KEY*).

## Test Execution

### Execution of tests [with unchanged locators]

To start with, we run the tests (with original locators) by invoking ```make org-test``` to execute tests that have locators that have not been modified during the development process. Hence, we will not observe any auto-healing of locators in this test execution cycle.

![Make3](https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/296771b4-5fe4-416e-9c94-2e3d08f6b34f)

Test execution is triggered with all three tests running in parallel on LambdaTest Selenium Grid.

<img width="1403" alt="make-org-test-running" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/266d566e-8840-4eda-b911-d61849fe065a">

<img width="1400" alt="make-org-test-completed" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/8d1f5c87-440c-4da4-b654-6cc1303fd417">

Shown below is the execution snapshot which indicates that the test execution was successful.

<img width="1430" alt="org-test-dashboard-running" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/a2d9d49b-c1a6-458c-bf05-9b8eb4ae1285">

<img width="1430" alt="org-test-dashboard-completed" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/b1386c67-3440-4872-bfa4-868c50c2d359">

### Execution of tests [with healed locators]

The test code with modified locators is present in *AutoHealingTest.java* Run the command ```make auto-heal``` to execute the tests that have locators healed by the auto-healing algorithm.

<img width="1399" alt="Test_auto-healing-terminal-1" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/5cd06ba4-2c1e-48c1-be94-0c35f492d2ac">

<img width="1429" alt="Test_auto-healing-dashboard-1" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/2720df45-90f3-4034-b753-6a2ad4d19512">

As seen below, the test execution was successful. However, there is a small *bandage* icon (for indicating healed tests) next to the respective tests. This indicates that the tests are healed when potential failures are encountered during the course of test execution.

<img width="1409" alt="Auto-Healing-Terminal" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/f7c19526-9509-4924-956e-c98265151d80">

<img width="1427" alt="Auto-Healing-Dashboard-2" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/8905cce9-411a-4a9c-a892-53baf7e8ca66">

<br/> Navigate to the respective test in the [LambdaTest dashboard](https://automation.lambdatest.com/build) and click on the *Bandage* icon to view details of the test.

<img width="1424" alt="Healed-Locator-in-Testing" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/c3c531cc-6d33-41a1-b042-27a238484b53">

<br/> For further reference, please visit [Official Documentation of Auto Healing on LambdaTest](https://www.lambdatest.com/support/docs/auto-healing/)

## Have feedback or need assistance?
Feel free to fork the repo and contribute to make it better! Email to [himanshu[dot]sheth[at]gmail[dot]com](mailto:himanshu.sheth@gmail.com) for any queries or ping me on the following social media sites:

<b>LinkedIn</b>: [@hjsblogger](https://linkedin.com/in/hjsblogger)<br/>
<b>Twitter</b>: [@hjsblogger](https://www.twitter.com/hjsblogger)