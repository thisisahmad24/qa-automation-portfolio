# 🐞 Daraz Login - Bug Report

---

## BUG_01 - Login Button Not Clickable Immediately
Severity: Medium  
Priority: High

Steps:
1. Open daraz.pk
2. Click Login

Expected:
Login page opens instantly

Actual:
Delay or button sometimes not clickable

---

## BUG_02 - Weak Error Message Validation
Severity: Low  
Priority: Medium

Steps:
1. Enter invalid credentials
2. Click login

Expected:
Specific error message

Actual:
Generic message shown

---

## BUG_03 - No Proper Validation for Special Characters
Severity: Medium  
Priority: Medium

Steps:
1. Enter special characters in email
2. Click login

Expected:
Validation error

Actual:
System accepts input but fails later

---

## BUG_04 - SQL Injection Input Not Sanitized (UI level)
Severity: High  
Priority: High

Steps:
1. Enter ' OR '1'='1
2. Click login

Expected:
Blocked input

Actual:
Input accepted (needs backend validation check)

---

## BUG_05 - Session Handling Issue (Potential)
Severity: High  
Priority: High

Steps:
1. Login
2. Logout
3. Press back button

Expected:
Session expired

Actual:
User may navigate back (depends on cache)

---

## BUG_06 - No Clear Validation for Empty Fields
Severity: Medium  
Priority: Medium

Steps:
1. Leave fields empty
2. Click login

Expected:
Field-specific errors

Actual:
Generic error shown