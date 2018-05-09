<#import "parts/common.ftl" as i>
<#import "parts/logout.ftl" as e>
<#import "parts/login.ftl" as l>
<@i.page>
<div>
<@e.logout />
<span><a href="/user">User List</a> </span>
</div>
<div>
    <form method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" name="text" placeholder="введите сообщение">
        <input type="text" name="tag" placeholder="введите тег">
        <button type="submit">Отправить</button>
    </form>
</div>
<div>
    <h2>Список сообщений</h2>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter?ifExists}" placeholder="введите сообщение">
        <button type="submit">Найти</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
        <#else>
        No messages
    </#list>

</@i.page>