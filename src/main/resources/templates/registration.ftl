<#import "parts/common.ftl" as i>
<#import "parts/login.ftl" as l>
<@i.page>
<div class="mb-1">Add new user</div>
 ${message?ifExists}
<@l.login "/registration" true/>
</@i.page>