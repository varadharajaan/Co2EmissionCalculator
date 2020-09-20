# Co2EmissionCalculator

Project Tools Used:

1. Java 11
2. Maven 
3. Junit 5

1. download project and run chmod 700 on project path
2. excuete the program using below commands

* ./co2-calculator --unit-of-distance=km --distance 15 --transportation-method=medium-diesel-car
* ./co2-calculator --transportation-method train --distance 14500 --unit-of-distance m --output kg
* ./co2-calculator --transportation-method train --distance 14500 --unit-of-distance m

* Instructions given in the pdf has been followed, attached test case as well 

Below is the infrsturucture provsioned by me and the maven depednency peoject is free to be deployed and managed using CI/CD tools. 

`https://gitlab.com/varadharajaan/infra-project`


***TO BE NOTED***

for one of the use case : ./co2-calculator --distance 1800.5 --transportation-method large-petrol-car , 
the excepted output shown in the pdf was 507.7kg but it actually to be 5077.4kg. Kindly cross check the output once (1800.5*282 = 5077.4)

