# Login Test Cases

## TC_01 - Valid Login
Steps:
1. Enter valid username
2. Enter valid password
3. Click login

Expected:
User should be redirected to dashboard


## TC_02 - Invalid Password
Steps:
1. Enter valid username
2. Enter wrong password
3. Click login

Expected:
Error message displayed


## TC_03 - Invalid Username
Steps:
1. Enter wrong username
2. Enter valid password
3. Click login

Expected:
Error message displayed


## TC_04 - Empty Fields
Steps:
1. Leave username empty
2. Leave password empty
3. Click login

Expected:
Validation error


## TC_05 - Empty Password
Steps:
1. Enter username
2. Leave password empty
3. Click login

Expected:
Validation error


## TC_06 - Special Characters
Steps:
1. Enter special characters
2. Enter random password
3. Click login

Expected:
Error message


## TC_07 - Long Input
Steps:
1. Enter very long username
2. Enter password
3. Click login

Expected:
System should handle input properly


## TC_08 - SQL Injection Attempt
Steps:
1. Enter ' OR '1'='1
2. Enter anything
3. Click login

Expected:
Login should fail


## TC_09 - Case Sensitivity
Steps:
1. Enter correct username in uppercase
2. Enter password
3. Click login

Expected:
System should handle case rules correctly


## TC_10 - Session Handling (conceptual)
Expected:
User session should expire after logout