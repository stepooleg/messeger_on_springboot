<#import "parts/common.ftl" as i>
<#import "parts/login.ftl" as l>
<@i.page>
Add new user
 ${message?ifExists}
<@l.login "/registration" />
</@i.page>