How to run this exercise
========================================

1. Build the exercise: 
`javac -cp "lib/*" -d build $(find ./src/* | grep .java)`

2. Run the exercise: 
`java -cp build com.xeasony.Main`

3. Run testcases: 
`java -cp "build:lib/*" org.junit.runner.JUnitCore test.AllTest`