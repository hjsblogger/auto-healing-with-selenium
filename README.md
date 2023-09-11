# Auto Healing in Selenium using LambdaTest 

In this 'Auto Healing in Selenium using LambdaTest' repo, we have covered the following usecases:

* <b>Auto Healing of live document</b>
* <b>Auto Healing of document (under development) </b>

The following websites are used for the purpose of demoing the usecases:

* [LambdaTest E-commerce Playground](https://ecommerce-playground.lambdatest.io/)
* [Demo Login Page created by Florin Pop](https://codepen.io/FlorinPop17/pen/vPKWjd)

The [Demo Login Page](https://codepen.io/FlorinPop17/pen/vPKWjd) is hosted locally and run on port number 8080 (or any other port) that is currently free on the system.

## Pre-requisites for test execution

Before running the tests on LambdaTest Selenium Grid, you need to set the environment variables - *LT_USERNAME* and *LT_ACCESS_KEY*. Here is how you can do the same:

## Configure Environment Variables

Before the tests are run, please set the environment variables <b>LT_USERNAME</b> & <b>LT_ACCESS_KEY</b> from the terminal. The account details are available on your [LambdaTest Profile](https://accounts.lambdatest.com/detail/profile) page.

<img width="970" alt="Screenshot 2023-08-24 at 10 42 23 PM" src="https://github.com/hjsblogger/auto-healing-with-selenium/assets/1688653/d2d061d7-a320-4b97-9b23-5663bfa4b721">

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

## Test Execution





## Have feedback or need assistance?
Feel free to fork the repo and contribute to make it better! Email to [himanshu[dot]sheth[at]gmail[dot]com](mailto:himanshu.sheth@gmail.com) for any queries or ping me on the following social media sites:

<b>LinkedIn</b>: [@hjsblogger](https://linkedin.com/in/hjsblogger)<br/>
<b>Twitter</b>: [@hjsblogger](https://www.twitter.com/hjsblogger)