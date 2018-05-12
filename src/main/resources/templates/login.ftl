<#import "parts/common.ftl" as i>
<#import "parts/login.ftl" as l>
<@i.page>
${message?ifExists}
<@l.login "/login" false/>

</@i.page>