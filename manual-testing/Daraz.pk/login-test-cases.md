# Daraz Login Test Cases

## TC_01 - Valid Login
Steps:
1. Open daraz.pk
2. Click on Login
3. Enter valid email
4. Enter valid password
5. Click login

Expected:
User should be redirected to homepage and profile icon visible


## TC_02 - Invalid Password
Steps:
1. Enter valid email
2. Enter wrong password
3. Click login

Expected:
Error message displayed


## TC_03 - Invalid Email
Steps:
1. Enter wrong email
2. Enter valid password
3. Click login

Expected:
Error message displayed


## TC_04 - Empty Fields
Steps:
1. Leave email empty
2. Leave password empty
3. Click login

Expected:
Validation error displayed


## TC_05 - Empty Password
Steps:
1. Enter email
2. Leave password empty
3. Click login

Expected:
Validation error displayed


## TC_06 - Special Characters
Steps:
1. Enter special characters in email
2. Enter random password
3. Click login

Expected:
Error message displayed


## TC_07 - Long Input
Steps:
1. Enter very long email string
2. Enter password
3. Click login

Expected:
System should handle input properly without crash


## TC_08 - SQL Injection Attempt
Steps:
1. Enter ' OR '1'='1
2. Enter anything
3. Click login

Expected:
Login should fail


## TC_09 - Case Sensitivity
Steps:
1. Enter uppercase email
2. Enter password
3. Click login

Expected:
System should handle correctly (based on system rules)


## TC_10 - Session Handling
Steps:
1. Login successfully
2. Logout
3. Press browser back button

Expected:
User should not access dashboard after logout