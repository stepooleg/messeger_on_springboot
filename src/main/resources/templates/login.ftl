<#import "parts/common.ftl" as i>
<#import "parts/login.ftl" as l>
<@i.page>
Login page
<@l.login "/login" />
<a href="/registration">Add new user</a>
</@i.page>