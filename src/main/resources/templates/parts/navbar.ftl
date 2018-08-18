<#include "security.ftl">
<#import "login.ftl" as l>
<#import "logout.ftl" as e>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Msg</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/"><span>Home</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main"><span>Messages</span></a>
            </li>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="user-messages/${currentUserId}"><span>My Messages</span></a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user"><span>User list</span></a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile"><span>Profile</span></a>
                </li>
            </#if>
        </ul>
        <div class="navbar-text mr-3">${name}</div>
        <@e.logout />
    </div>
</nav>