<#import "parts/common.ftl" as i>

<@i.page>
<#if isCurrentUser>
    <#include "parts/messageEdit.ftl" />
</#if>

<#include "parts/messageList.ftl" />
</@i.page>